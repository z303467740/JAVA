package edu.fjnu.math1;

import java.util.Scanner;

public class homework1 {
	public static void main(String[] args) {
		homework1 text = new homework1();
		text.IsRuanNian();
		text.ToGradeScore();
		text.Print(4);
		text.TheNumber();
	}

	public void IsRuanNian() {
		System.out.println("�ж��Ƿ�Ϊ��������0�˳�;");
		while (true) {
			Scanner sc = new Scanner(System.in);
			int year = sc.nextInt();
			if (year == 0) {
				break;
			}
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					System.out.println("yes!");

				} else {
					System.out.println("no!");
				}
			} else if (year % 4 == 0) {
				System.out.println("yes!");

			} else {
				System.out.println("no!");
			}
		}
		return;
	}

	public void ToGradeScore() {

		System.out.println("������0-100���������� �����������˳�");
		Scanner sc = new Scanner(System.in);
		int score = sc.nextInt();
		while (score >= 0 || score <= 100) {
			if (score >= 0 && score < 60) {
				System.out.println("������" + score);
			} else if (score >= 60 && score < 69) {
				System.out.println("����" + score);
			} else if (score >= 70 && score < 79) {
				System.out.println("�У�" + score);
			} else if (score >= 80 && score < 89) {
				System.out.println("����" + score);
			} else if (score >= 90 && score <= 100) {
				System.out.println("�ţ�" + score);
			} else {
				break;
			}
			sc = new Scanner(System.in);
			score = sc.nextInt();

		}

	}

	public void Print(int ls) {
		for (int i = 0; i <= 2 * ls - 1; i++) {
			if (i <= ls) {
				for (int j = 0; j < ls - i; j++) {
					System.out.print(' ');
				}
				for (int j = 0; j < 2 * i - 1; j++) {
					System.out.print('*');
				}
			} else {
				for (int j = 0; j < i - ls; j++) {
					System.out.print(' ');
				}
				for (int j = 0; j < 4 * ls - 2 * i - 1; j++) {
					System.out.print('*');
				}
			}

			System.out.println();
		}

	}

	public void TheNumber() {
		System.out.println("ˮ�ɻ�����");
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (i * 100 + j * 10 + k == i * i * i + j * j * j + k * k
							* k) {
						System.out.println(i * 100 + j * 10 + k);
					}
				}
			}
		}
	}
}
