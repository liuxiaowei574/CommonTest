package com.liusw.classloader;

public class ClassLoaderTest {

	public static void main(String[] args) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println("loader:" + loader);
		System.out.println("parent loader:" + loader.getParent());
		System.out.println("grandparent loader:" + loader.getParent().getParent());
	}

}
