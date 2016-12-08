package com.liusw.list;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("zhangsan", "zhang");
		map.put("lisi", "li");
		System.out.println(map.containsKey("zhangsan"));
		System.out.println(map.containsValue("li"));
	}
}
