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
