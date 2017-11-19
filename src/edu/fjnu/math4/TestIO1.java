package edu.fjnu.math4;

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
		Reader destr = new FileReader(destFile);
		while ((b = destr.read()) != -1) {
			System.out.print((char) b);
		}
		System.out.println();
		destr.close();
	}
}
