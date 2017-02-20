package com.liusw.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author wison
 */
public class TestWeakReference {

	public static void main(String[] args) {

		Car car = new Car(22000, "silver");
		ReferenceQueue<Car> queue = new ReferenceQueue<>();
		WeakReference<Car> weakCar = new WeakReference<Car>(car, queue);

		int i = 0;

		while (true) {
			if (weakCar.get() != null) {
				i++;
				System.out.println("Object is alive for " + i + " loops - " + weakCar);
			} else {
				System.out.println("Object has been collected.");
				break;
			}
		}
		try {
			Reference wr = queue.remove();
			if (wr != null) {
				System.out.println(wr.get()); // null
				System.out.println(wr == weakCar);// true
				System.out.println(wr.equals(weakCar));// true
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}