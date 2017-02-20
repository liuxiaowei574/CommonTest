package com.liusw.producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {

	private int maxSize;

	private List<String> storage;

	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<String>();
	}

	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		storage.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		System.out.printf("Set: %-2d\n", storage.size());
		notifyAll();
	}

	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.printf("Get: %-2d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
		notifyAll();
	}
}