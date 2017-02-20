package com.liusw.sort;

import java.util.ArrayList;
import java.util.List;

public class ListSort {

	public static void main(String[] args) {
		String[] ids = { "eeff", "ccdd", "aabb" };
		
		List<User> list = new ArrayList<>();
		list.add(new User("aabb", "zhangsan"));
		list.add(new User("ccdd", "lisi"));
		list.add(new User("eeff", "kongzi"));
		System.out.println(list);
		
		
	}

}

class User {
	private String id;
	private String name;

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
