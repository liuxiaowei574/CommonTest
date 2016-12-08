package com.liusw.arraycopy;

import java.util.Arrays;

public class CopyTest {

	public static void main(String[] args) {
		systemCopy();
		arraysCopy();
		cloneCopy();
	}

	private static void cloneCopy() {
		String[] array = new String[] { "zhangsan", "lisi", "wangwu" };
		String[] dest = array.clone();
		array[0] = "zhangsanfeng";
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(dest));
		System.out.println("****************************");
	}

	private static void arraysCopy() {
		String[] array = new String[] { "zhangsan", "lisi", "wangwu" };
		String[] dest = Arrays.copyOf(array, array.length);
		array[0] = "zhangsanfeng";
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(dest));
		System.out.println("****************************");
	}

	private static void systemCopy() {
		String[] array = new String[] { "zhangsan", "lisi", "wangwu" };
		String[] dest = new String[array.length];
		System.arraycopy(array, 0, dest, 0, array.length);
		array[0] = "zhangsanfeng";
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(dest));
		System.out.println("****************************");
	}
}
