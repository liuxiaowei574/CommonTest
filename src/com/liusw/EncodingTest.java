package com.liusw;

public class EncodingTest {

	public static void main(String[] args) throws Exception {
		test1();
	}

	private static void test1() throws Exception {
		String a = "2013Äê´«¼Ç¾çÇé¡¶´÷°²ÄÈ¡·";
		System.out.println(new String(a.getBytes("ISO8859-1"), "gb2312"));
	}

}
