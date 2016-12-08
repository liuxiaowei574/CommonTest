package com.liusw.sort;

import java.util.Arrays;

public class SortTest {

	public static void main(String[] args) {
		int[] a = new int[] { 29, 12, 48, 3, 854, 23, 8, 83, 69 };
		int[] b = insertSort(a);
		System.out.println(Arrays.toString(b));

		int[] c = bubbleSort(a);
		System.out.println(Arrays.toString(c));
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
		for (int i = a.length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (a[j + 1] < a[j]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
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
		return arr;
	}
}
