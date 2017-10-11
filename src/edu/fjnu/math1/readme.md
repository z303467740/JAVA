---
title: JAVA控制语句
tags: JAVA,控制语句
---

注：通过几个程序掌握JAVA控制语句的编写。



## 1.判断闰年
编写Java程序，输出从公元1990年到2007年所有闰年的年号，每输出两个年号换一行。判断年号是否为闰年的条件是：
（1）若年号能被4整除，而不能被100整除，则是闰年；
（2）若年号能被400整除也是闰年。

#### 代码
```java
public void IsLeapYear() {
		System.out.println("判断是否为闰年输入0退出;");
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
```
## 2.百分制成绩转化为等级成绩
实现方法ToGradeScore，将百分制成绩转化为等级成绩。要求对一组数据，实现批量转化。等级与百分制对照.
优：[90,100]
良：[89,80]
中：[79,70]
及格：[69,60]
不及格：[0,59]

#### 代码
```java
	public void ToGradeScore() {

		System.out.println("请输入0-100的整型数据 输入其他即退出");
		Scanner sc = new Scanner(System.in);
		int score = sc.nextInt();
		while (score >= 0 || score <= 100) {
			if (score >= 0 && score < 60) {
				System.out.println("不及格：" + score);
			} else if (score >= 60 && score < 69) {
				System.out.println("及格：" + score);
			} else if (score >= 70 && score < 79) {
				System.out.println("中：" + score);
			} else if (score >= 80 && score < 89) {
				System.out.println("良：" + score);
			} else if (score >= 90 && score <= 100) {
				System.out.println("优：" + score);
			} else {
				break;
			}
			sc = new Scanner(System.in);
			score = sc.nextInt();

		}

	}
```

## 3.打印图案
利用for循环编写一个程序，将如下图案分别打印输出。
 ```
     *
    ***
   *****
  *******
   *****
    ***
     *
```
#### 代码
```java
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
```

## 4.水仙花数
编写程序找出所有的水仙花数；水仙花数是三位数，它的各位数字的立方和等于这个三位数本身。

#### 代码
```java
public void TheNumber() {
		System.out.println("水仙花数有");
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
```
