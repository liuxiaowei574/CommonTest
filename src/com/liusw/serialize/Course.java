package com.liusw.serialize;

import java.io.Serializable;

class Course implements Serializable {
	private static final long serialVersionUID = -4915636908782542044L;
	private static String courseName;
	private int credit;

	@SuppressWarnings("static-access")
	public Course(String courseName, int credit) {
		this.courseName = courseName;
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Course [credit=" + credit + "]";
	}

}