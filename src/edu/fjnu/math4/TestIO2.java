package edu.fjnu.math4;

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
