package com.liusw.nioTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Java NIO的通道类似流，但又有些不同：<br>
 * 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。<br>
 * 通道可以异步地读写。<br>
 * 通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。<br>
 * 正如上面所说，从通道读取数据到缓冲区，从缓冲区写入数据到通道。
 *
 */
public class ProcessJob implements Runnable {
	private Selector clientSelector;

	public ProcessJob(Selector clientSelector) {
		super();
		this.clientSelector = clientSelector;
	}

	@Override
	public void run() {
		int count = 0;
		ByteBuffer bb = ByteBuffer.allocate(1024);
		try {
			while (true) {
				int n = clientSelector.selectNow();
				if (n <= 0) {
					continue;
				}
				Iterator<SelectionKey> it = clientSelector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					if (key.isReadable()) {
						SocketChannel client = (SocketChannel) key.channel();
						while ((count = client.read(bb)) > 0) {
							System.out.write("[Client]: ".getBytes());
							System.out.write(bb.array(), 0, count);
							System.out.write("\n".getBytes());
							bb.clear();
						}
						client.close();
					}
					it.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Selector clientSelector = Selector.open();
			Selector serverSelector = Selector.open();

			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			ServerSocket serverSocket = serverChannel.socket();
			serverSocket.bind(new InetSocketAddress(9999));
			serverChannel.configureBlocking(false);
			serverChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

			AcceptJob acceptJob = new AcceptJob(clientSelector, serverSelector);
			ProcessJob processJob = new ProcessJob(clientSelector);
			Thread acceptThread = new Thread(acceptJob);
			Thread processThread = new Thread(processJob);
			acceptThread.start();
			processThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
