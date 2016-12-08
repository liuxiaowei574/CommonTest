package com.liusw.sort;

import java.util.Date;
import java.util.Random;

/**
 * 
 * 13000组随机数[插入排序]耗时：94ms 13000组随机数[冒泡排序]耗时：218ms 13000组随机数[选择排序]耗时：78ms
 * 13000组随机数[希尔排序]耗时：16ms 13000组随机数[堆排序]耗时：0ms 13000组随机数[基数排序]耗时：16ms
 * 13000组随机数[归并排序]耗时：0ms 13000组随机数[快速排序]耗时：46ms <br>
 * <br>
 * <br>
 * 100000组随机数[插入排序]耗时：5023ms 100000组随机数[冒泡排序]耗时：13994ms
 * 100000组随机数[选择排序]耗时：4120ms 100000组随机数[希尔排序]耗时：31ms 100000组随机数[堆排序]耗时：17ms
 * 100000组随机数[基数排序]耗时：15ms 100000组随机数[归并排序]耗时：16ms<br>
 * <br>
 * <br>
 * 200000组随机数[插入排序]耗时：20688ms 200000组随机数[冒泡排序]耗时：55691ms
 * 200000组随机数[选择排序]耗时：16396ms 200000组随机数[希尔排序]耗时：32ms 200000组随机数[堆排序]耗时：47ms
 * 200000组随机数[基数排序]耗时：16ms 200000组随机数[归并排序]耗时：62ms
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class SortTest {

	public static void main(String[] args) {
		int[] a = initData(10000);

		insertSort(a.clone());
		bubbleSort(a.clone());
		selectSort(a.clone());
		shellSort(a.clone());
		heapSort(a);
		radixSort(a.clone(), 0, a.length - 1, 1);

		long beginTime = new Date().getTime();
		mergeSort(a.clone(), 0, a.length - 1);
		long endTime = new Date().getTime();
		System.out.println(a.length + "组随机数[归并排序]耗时：" + (endTime - beginTime) + "ms");

		// beginTime = new Date().getTime();
		// quickSort(a.clone(), 0, a.length - 1);
		// endTime = new Date().getTime();
		// System.out.println(a.length + "组随机数[快速排序]耗时：" + (endTime - beginTime)
		// + "ms");

	}

	/**
	 * 基数排序
	 * 
	 * @param a
	 * @param begin
	 * @param end
	 * @param digit
	 */
	public static void radixSort(int[] a, int begin, int end, int digit) {
		long beginTime = new Date().getTime();
		// 基数
		final int radix = 10;
		// 桶中的数据统计
		int[] count = new int[radix];
		int[] bucket = new int[end - begin + 1];

		// 按照从低位到高位的顺序执行排序过程
		for (int i = 1; i <= digit; i++) {
			// 清空桶中的数据统计
			for (int j = 0; j < radix; j++) {
				count[j] = 0;
			}

			// 统计各个桶将要装入的数据个数
			for (int j = begin; j <= end; j++) {
				int index = getDigit(a[j], i);
				count[index]++;
			}

			// count[i]表示第i个桶的右边界索引
			for (int j = 1; j < radix; j++) {
				count[j] = count[j] + count[j - 1];
			}

			// 将数据依次装入桶中
			// 这里要从右向左扫描，保证排序稳定性
			for (int j = end; j >= begin; j--) {
				int index = getDigit(a[j], i);
				bucket[count[index] - 1] = a[j];
				count[index]--;
			}

			// 取出，此时已是对应当前位数有序的表
			for (int j = 0; j < bucket.length; j++) {
				a[j] = bucket[j];
			}
		}
		long endTime = new Date().getTime();
		System.out.println(a.length + "组随机数[归并排序]耗时：" + (endTime - beginTime) + "ms");
		// System.out.println(Arrays.toString(a));
	}

	/**
	 * 获取x的第d位的数字，其中最低位d=1
	 * 
	 * @param x
	 * @param d
	 * @return
	 */
	private static int getDigit(int x, int d) {
		String div = "1";
		while (d >= 2) {
			div += "0";
			d--;
		}
		return x / Integer.parseInt(div) % 10;
	}

	/**
	 * 堆排序
	 * 
	 * @param a
	 */
	public static void heapSort(int[] a) {
		if (null == a || a.length < 2) {
			return;
		}

		long beginTime = new Date().getTime();
		buildMaxHeap(a);

		for (int i = a.length - 1; i >= 0; i--) {
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;

			adjustHeap(a, i, 0);
		}
		long endTime = new Date().getTime();
		System.out.println(a.length + "组随机数[堆排序]耗时：" + (endTime - beginTime) + "ms");
		// System.out.println(Arrays.toString(a));
	}

	/**
	 * 建堆
	 * 
	 * @param a
	 */
	private static void buildMaxHeap(int[] a) {
		int mid = a.length / 2;
		for (int i = mid; i >= 0; i--) {
			adjustHeap(a, a.length, i);
		}
	}

	/**
	 * 递归调整堆
	 * 
	 * @param a
	 * @param size
	 * @param parent
	 */
	private static void adjustHeap(int[] a, int size, int parent) {
		int left = 2 * parent + 1;
		int right = 2 * parent + 2;

		int largest = parent;
		if (left < size && a[left] > a[parent]) {
			largest = left;
		}

		if (right < size && a[right] > a[largest]) {
			largest = right;
		}

		if (parent != largest) {
			int temp = a[parent];
			a[parent] = a[largest];
			a[largest] = temp;
			adjustHeap(a, size, largest);
		}
	}

	/**
	 * 快速排序，元素过多时，栈溢出
	 * 
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void quickSort(int[] a, int low, int high) {
		if (null == a || a.length < 2) {
			return;
		}
		if (low < high) {
			int mid = partition(a, low, high);
			quickSort(a, low, mid - 1);
			quickSort(a, mid + 1, high);
		}
	}

	private static int partition(int[] a, int low, int high) {
		int pivot = a[low];

		while (low < high) {
			// 注意等于，否则死循环
			while (low < high && a[high] >= pivot) {
				high--;
			}
			a[low] = a[high];
			// 注意等于，否则死循环
			while (low < high && a[low] <= pivot) {
				low++;
			}
			a[high] = a[low];
		}
		a[low] = pivot;

		return low;
	}

	/**
	 * 希尔排序
	 * 
	 * @param a
	 */
	public static void shellSort(int[] a) {
		if (null == a || a.length < 2) {
			return;
		}
		long beginTime = new Date().getTime();
		for (int d = a.length / 2; d > 0; d /= 2) {
			// 从1B开始先和1A比较 然后2A与2B...然后再1C向前与同组的比较
			for (int i = d; i < a.length; i++) {
				// 内部直接插入
				int temp = a[i];
				int j = i - d;
				while (j >= 0 && temp < a[j]) {
					a[j + d] = a[j];
					j -= d;
				}
				a[j + d] = temp;
			}
		}
		long endTime = new Date().getTime();
		System.out.println(a.length + "组随机数[希尔排序]耗时：" + (endTime - beginTime) + "ms");
		// System.out.println(Arrays.toString(a));
	}

	/**
	 * 归并排序
	 * 
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void mergeSort(int[] a, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			// 左边排序
			mergeSort(a, low, mid);
			// 右边排序
			mergeSort(a, mid + 1, high);
			// 有序序列合并
			merge(a, low, mid, high);
		}
	}

	// 合并
	private static void merge(int a[], int low, int mid, int high) {
		// 临时数组
		int[] temp = new int[high - low + 1];
		// 左指针
		int i = low;
		// 右指针
		int j = mid + 1;
		// 临时数组索引
		int k = 0;

		while (i <= mid && j <= high) {
			if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}

		// 把左边剩余的数移入数组
		while (i <= mid) {
			temp[k++] = a[i++];
		}

		// 把右边剩余的数移入数组
		while (j <= high) {
			temp[k++] = a[j++];
		}

		// 注意这里是low + t
		for (int t = 0; t < temp.length; t++) {
			a[low + t] = temp[t];
		}
	}

	/**
	 * 选择排序
	 * 
	 * @param a
	 */
	private static void selectSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}
		int length = a.length;
		long beginTime = new Date().getTime();
		for (int i = 0; i < length - 1; i++) {// 一共循环的次数
			// 里面的循环，是从i起到最后，查找最小值的索引
			int k = i;
			for (int j = k + 1; j < length; j++) {
				if (a[j] < a[k]) {
					k = j;
				}
			}
			if (i != k) {
				int temp = a[i];
				a[i] = a[k];
				a[k] = temp;
			}
		}
		long endTime = new Date().getTime();
		System.out.println(a.length + "组随机数[选择排序]耗时：" + (endTime - beginTime) + "ms");
		// System.out.println(Arrays.toString(a));
	}

	/**
	 * 冒泡排序
	 * 
	 * @param a
	 * @return
	 */
	private static void bubbleSort(int[] a) {
		if (a == null || a.length < 2) {
			return;
		}
		long beginTime = new Date().getTime();
		for (int i = a.length - 1; i > 0; --i) {
			int temp;
			for (int j = 0; j < i; ++j) {
				if (a[j + 1] < a[j]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		long endTime = new Date().getTime();
		System.out.println(a.length + "组随机数[冒泡排序]耗时：" + (endTime - beginTime) + "ms");
		// System.out.println(Arrays.toString(a));
	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 * @return
	 */
	private static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		long beginTime = new Date().getTime();
		for (int i = 1; i < arr.length; i++) {
			int temp;
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					temp = arr[j];
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
		// System.out.println(Arrays.toString(arr));
	}

	private static int[] initData(int size) {
		int[] a = new int[size];
		int range = size + 1;
		for (int i = 0; i < size; i++) {
			a[i] = new Random().nextInt(range);
		}
		return a;
	}
}
