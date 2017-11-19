---
title: JAVA的IO操作
tags: JAVA,面向对象,基础
---

## 实验一 基本IO操作
### FCFS算法按照任务到达的顺序进行服务，先来先服务
### 每个Task对象可以描述为至少包含下列属性：
从键盘接收字节流
写入到当前目录下的src.txt文件中
将src.txt文件内容复制到当前目录下dest.txt文件中
将dest.txt文件内容显示到屏幕上

#### 部分代码
```java
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class TestIO1 {
	public static void main(String[] args) throws IOException {
		File srcFile = new File("src\\edu\\fjnu\\math4\\src.txt");
		File destFile = new File("src\\edu\\fjnu\\math4\\dest.txt");
		if (!srcFile.exists()) {
			srcFile.createNewFile();
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		Writer srcw = new FileWriter(srcFile);

		int ch;
		System.out.println("input the content:");
		while ((ch = System.in.read()) != -1) {
			srcw.write((char) ch);
		}
		srcw.close();
		Reader srcr = new FileReader(srcFile);
		Writer destw = new FileWriter(destFile);
		int b;
		while ((b = srcr.read()) != -1) {
			destw.write(b);
		}
		srcr.close();
		destw.close();
	}
}

```
## 结果截图
![enter description here][1]


## 实验二 获取系统的文件树
获取某个目录下的目录信息，目录从控制台输入。
目录信息包括该目录下的所有文件和文件夹的列表。对于每个文件夹显示其名称，修改日期。对于每个文件显示其名称，修改日期和大小。
目录的信息写出到文件中。注意，文件信息首先按类型分类，文件夹在前，文件在后，并且他们各自按照文件名字符串的顺序排序。


#### 部分代码
```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;

public class TestIO2 {

	public static void main(String[] args) throws IOException {
		String Path = "src\\edu\\fjnu\\math4";
		File directoryinfo = new File("src\\edu\\fjnu\\math4\\directoryinfo.txt");
		if (!directoryinfo.exists()) {
			directoryinfo.createNewFile();
		}
		Writer info = new FileWriter(directoryinfo);
		File afile = new File(Path);
		String[] list;
		list = afile.list();
		Arrays.sort(list);
		String[] temps = new String[list.length];
		int j = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i].lastIndexOf('.') == -1) {
				File file = new File(Path + "\\" + list[i]);
				Long lastModified = file.lastModified();
				Date date = new Date(lastModified);
				info.write("directoryname:" + list[i] + "  lastchangedate:" + date + "\r\n");

			} else {
				temps[j++] = list[i];
			}
		}
		for (int k = 0; k < j; k++) {
			File file = new File(Path + "\\" + temps[k]);
			Long lastModified = file.lastModified();
			Date date = new Date(lastModified);
			info.write("file:" + temps[k] + "  lastchangedate:" + date + "  filesize:" + file.length() + "\r\n");
		}
		info.close();

	}
}

```
## 结果截图
![enter description here][2]

## 实验三 带缓冲区的IO
用带缓冲和不带缓冲的字符流实现文件复制，并比较耗时情况。


#### 部分代码
```java
package edu.fjnu.math4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class TestIO3 {
	public static void main(String[] args) {
		try {

			String pathname = "D:\\code\\javatest\\javawork\\src\\edu\\fjnu\\math4\\";
			File oriFile = new File(pathname + "orifile.txt");
			File copFile = new File(pathname + "copfile.txt");
			if (!oriFile.exists()) {
				oriFile.createNewFile();
			}
			if (!copFile.exists()) {
				copFile.createNewFile();
			}
			int b;

			Reader orir = new FileReader(oriFile);
			Writer copyw = new FileWriter(copFile);
			long sysDate1 = System.currentTimeMillis();
			while ((b = orir.read()) != -1) {
				copyw.write(b);
			}
			long sysDate2 = System.currentTimeMillis();
			orir.close();
			copyw.close();

			System.out.println("不带缓冲的字符流：" + (sysDate2 - sysDate1) + "毫秒");

			orir = new FileReader(oriFile);
			copyw = new FileWriter(copFile);
			BufferedReader oriBufferedReader = new BufferedReader(orir);
			BufferedWriter copBufferedWriter = new BufferedWriter(copyw);
			sysDate1 = System.currentTimeMillis();
			while ((b = oriBufferedReader.read()) != -1) {
				copBufferedWriter.write(b);
			}

			sysDate2 = System.currentTimeMillis();
			System.out.println("带缓冲的字符流：" + (sysDate2 - sysDate1) + "毫秒");

			oriBufferedReader.close();
			copBufferedWriter.close();
			orir.close();
			copyw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}

```
## 结果截图
![enter description here][3]

## 实验四 对象序列化
类Student表示学生，属性包括{studentID, name, sex} (学号，姓名，性别)，使用序列化技术定义Student。学生信息从文件list.txt读入，并按照学号升序排列。注意，这里姓名和性别可以组织成String类型，而学号可以是String也可以是Long。
使用ObjectOutputStream将已经排序的学生信息写出到文件“student.bin”中。
使用ObjectInputStream将“student.bin”中的对象信息读入程序，显示在控制台中

#### 部分代码
```java
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
		File outFile = new File("Student.obj");
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

```
```java
package edu.fjnu.math4;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return (o1.getStudentID().compareTo(o2.getStudentID()));
	}

}
```
```java
package edu.fjnu.math4;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1234L;
	private String studentID;
	private String name;
	private String sex;

	public String getStudentID() {
		return studentID;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + "	 name=" + name + "	 sex=" + sex + "]";
	}

}

```
## 结果截图
![enter description here][4]


  [1]: ./1.png "1"
  [2]: ./2.png "2"
  [3]: ./3.png "3"
  [4]: ./4.png "4"