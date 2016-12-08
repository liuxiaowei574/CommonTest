package com.liusw.serialize;

import java.io.Serializable;

class Student implements Serializable {
	private static final long serialVersionUID = 851963652926112748L;
	private String name;
	private transient int age;
	private Course course;

	public void setCourse(Course course) {
		this.course = course;
	}

	public Course getCourse() {
		return course;
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", course=" + course + "]";
	}

}