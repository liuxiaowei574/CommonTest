package com.liusw.nioTest;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class AcceptJob implements Runnable {
	private Selector clientSelector;
	private Selector serverSelector;

	public AcceptJob(Selector clientSelector, Selector serverSelector) {
		super();
		this.clientSelector = clientSelector;
		this.serverSelector = serverSelector;
	}

	@Override
	public void run() {
		try {
			while (true) {
				int n = serverSelector.selectNow();
				if (n <= 0) {
					continue;
				}
				Iterator<SelectionKey> it = serverSelector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel channel = server.accept();
						channel.configureBlocking(false);
						channel.register(clientSelector, SelectionKey.OP_READ);
					}
					it.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
