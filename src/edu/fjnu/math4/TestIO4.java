package edu.fjnu.math4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class TestIO4 {

	static public ArrayList<Student> studentQueue = new ArrayList<Student>();

	public static void main(String[] args) {
		// inflattenStudent(Student.studentQueue);
		readStudent();
		SerializableStudent();
		inSerializableStudent();

	}

	private static void inSerializableStudent() {
		// TODO Auto-generated method stub
		File inFile = new File("Student.obj");
		FileInputStream finS = null;
		ObjectInputStream oin = null;

		try {
			finS = new FileInputStream(inFile);
			oin = new ObjectInputStream(finS);
			int cout = 0;
			Student get = null;
			while ((get = (Student) oin.readObject()) != null) {
				System.out.print(++cout + ":  ");
				System.out.println(get);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("输出结束");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	private static void readStudent() {
		// TODO Auto-generated method stub
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("src\\edu\\fjnu\\math4\\list.txt");
			br = new BufferedReader(fr);
			String line = "";
			String[] infos = null;
			while ((line = br.readLine()) != null) {
				Student temp = new Student();
				infos = line.split(" ");
				temp.setStudentID(infos[0]);
				temp.setName(infos[1]);
				temp.setSex(infos[2]);
				studentQueue.add(temp);
			}
			br.close();
			fr.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			Collections.sort(studentQueue, new StudentComparator());

		}

	}

	private static void SerializableStudent() {
		// TODO Auto-generated method stub
		File outFile = new File("src\\edu\\fjnu\\math4\\Student.bin");
		FileOutputStream foutS = null;
		ObjectOutputStream oout = null;
		try {
			foutS = new FileOutputStream(outFile);
			oout = new ObjectOutputStream(foutS);
			for (Student temp : studentQueue) {
				oout.writeObject(temp);
				oout.flush();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}