package com.liusw.nioTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

	public static void main(String[] args) {
		RandomAccessFile aFile;
		try {
			aFile = new RandomAccessFile("d:/hcg_dbg.txt", "rw");

			FileChannel inChannel = aFile.getChannel();

			ByteBuffer buf = ByteBuffer.allocate(48);

			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {

				System.out.println("Read " + bytesRead);
				buf.flip();

				while (buf.hasRemaining()) {
					System.out.print((char) buf.get());
				}

				buf.clear();
				bytesRead = inChannel.read(buf);
			}
			aFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
