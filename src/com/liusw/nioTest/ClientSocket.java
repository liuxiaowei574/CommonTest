package com.liusw.nioTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9999);
			OutputStream out = socket.getOutputStream();
			out.write("Hello, server!".getBytes());
			out.flush();
			out.close();
			System.out.println("over");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
