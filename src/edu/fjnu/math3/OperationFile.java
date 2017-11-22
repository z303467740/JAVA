package edu.fjnu.math3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class OperationFile {

	public static void inputFiles() {
		File dest = new File("src\\edu\\fjnu\\math3\\file.txt");
		Writer er = null;
		try {
			er = new FileWriter(dest);
			int TaskID;
			int arrivalTime;
			int serviceTime;
			for (int i = 0; i < 100; i++) {
				TaskID = i + 1;
				arrivalTime = i;
				serviceTime = creatServiceTime();
				er.append(TaskID + " " + arrivalTime + " " + serviceTime + "\r\n");
			}
			er.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != er)
			try {
				er.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private static int creatServiceTime() {
		Random random = new Random();
		int s = random.nextInt(5);
		switch (s) {
		case 0:
			return 6;
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 3;
		default:
			return 9;
		}

	}
}
