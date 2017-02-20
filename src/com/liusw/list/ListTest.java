package com.liusw.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListTest {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		strings.add("zhangsan");
		strings.add("lisi");
		System.out.println(strings);

		String[] array = strings.toArray(new String[] {});
		strings.remove("zhangsan");
		for (String s : array) {
			System.out.println(s);
		}

		List<String> names = Arrays.asList(array);
		System.out.println(names);
		array[0] = "wangwu";
		names.add("zhangsanfeng");
		System.out.println(names);
		
		Set<Integer> set = new HashSet<>(100);
		set.toArray(new Integer[]{});
	}

}
