package com.liusw.sort;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class SortTest {

	public static void main(String[] args) {
		int[] a = initData(1000);

		insertSort(a.clone());
		bubbleSort(a.clone());
	}

	private static int[] initData(int size) {
		int[] a = new int[size];
		int range = size + 1;
		for (int i = 0; i < size; i++) {
			a[i] = new Random().nextInt(range);
		}
		return a;
	}

	/**
	 * 冒泡排序
	 * 
	 * @param a
	 * @return
	 */
	private static int[] bubbleSort(int[] a) {
		if (a == null || a.length < 2) {
			return a;
		}
		long beginTime = new Date().getTime();
		for (int i = a.length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (a[j + 1] < a[j]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		long endTime = new Date().getTime();
		System.out.println(a.length + "组随机数[插入排序]耗时：" + (endTime - beginTime) + "ms");
		System.out.println(Arrays.toString(a));
		return a;
	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 * @return
	 */
	private static int[] insertSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return arr;
		}
		long beginTime = new Date().getTime();
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				} else {
					// 接下来是无用功
					break;
				}
			}
		}
		long endTime = new Date().getTime();
		System.out.println(arr.length + "组随机数[插入排序]耗时：" + (endTime - beginTime) + "ms");
		System.out.println(Arrays.toString(arr));
		return arr;
	}
}
