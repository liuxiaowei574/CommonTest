package com.liusw.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		strings.add("zhangsan");
		strings.add("lisi");
		System.out.println(strings);
		
		String[] array = strings.toArray(new String[]{});
		for(String s: array) {
			System.out.println(s);
		}
		System.out.println(Arrays.asList(array));
	}

}
