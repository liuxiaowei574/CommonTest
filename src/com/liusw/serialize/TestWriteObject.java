package com.liusw.serialize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestWriteObject {

	public static void main(String[] args) {

		String filePath = "C://obj.txt";
		ObjectOutputStream objOutput = null;
		Course c1 = new Course("C language", 3);
		Course c2 = new Course("OS", 4);

		Student s1 = new Student("king", 25);
		s1.setCourse(c1);

		Student s2 = new Student("jason", 23);
		s2.setCourse(c2);

		try {
			objOutput = new ObjectOutputStream(new FileOutputStream(filePath));
			objOutput.writeObject(s1);
			objOutput.writeObject(s2);
			objOutput.writeInt(123);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				objOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Info:对象被写入" + filePath);
	}
}