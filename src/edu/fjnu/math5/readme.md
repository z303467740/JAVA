---
title: JAVA多线程
tags: JAVA,面向对象,基础
---

## 实验一
#### 对实验三中的单例模式进行改造，使其支持多线程，并且是线程安全的。

#### 多线程代码


```java

public class ThreadTest1 implements Runnable {
	private String name;

	public ThreadTest1(String name) {
		this.name = name;

	}

	public static void main(String[] args) {
		Thread test1 = new Thread(new ThreadTest1("test1"));
		Thread test2 = new Thread(new ThreadTest1("test2"));
		Thread test3 = new Thread(new ThreadTest1("test3"));
		Thread test4 = new Thread(new ThreadTest1("test4"));
		test1.start();
		test2.start();
		test3.start();
		test4.start();
	}

	public void run() {
		ChocolateBoiler.getInstance().doChocolate(name);

	}
}

``` 

#### 实验三锅炉代码
```java
public class ChocolateBoiler {

	public static void main(String[] args) {

	}

	public synchronized void doChocolate(String name) {
		ChocolateBoiler.getInstance().fill(name);
		ChocolateBoiler.getInstance().boil(name);
		ChocolateBoiler.getInstance().drain(name);

	}

	private static boolean empty;
	private static boolean boiled;
	private static ChocolateBoiler chocolateBoiler;

	public ChocolateBoiler() {
		empty = true;
		boiled = false;
	}

	public static synchronized ChocolateBoiler getInstance() {
		if (chocolateBoiler == null) {
			chocolateBoiler = new ChocolateBoiler();
		}

		return chocolateBoiler;
	}

	public void fill(String name) {
		if (isEmpty()) {
			System.out.println(name + ":填充中……填充完成！");
			empty = false;
		} else {
			System.out.println(name + ":只有空的锅炉才能填充巧克力和牛奶");
		}
	}

	public void boil(String name) {
		if (!isEmpty()) {
			if (isBoiled()) {
				System.out.println(name + ":已被煮过");
			} else {
				System.out.println(name + ":煮开中……煮好了！");
				boiled = true;
			}

		} else {
			System.out.println("锅炉内没水！");
		}
	}

	public void drain(String name) {
		if (isEmpty()) {
			System.out.println(name + ":锅炉内没水！");
		} else {
			if (isBoiled()) {
				System.out.println(name + ":倒出中……倒出完成！");
				empty = true;
				boiled = false;
			} else {
				System.out.println(name + ":未烧开！");
			}
		}

	}

	public void fill() {
		if (isEmpty()) {
			System.out.println("填充中……填充完成！");
			empty = false;
		} else {
			System.out.println("只有空的锅炉才能填充巧克力和牛奶");
		}
	}

	public void boil() {
		if (!isEmpty()) {
			if (isBoiled()) {
				System.out.println("已被煮过");
			} else {
				System.out.println("煮开中……煮好了！");
				boiled = true;
			}

		} else {
			System.out.println("锅炉内没水！");
		}
	}

	public void drain() {
		if (isEmpty()) {
			System.out.println("锅炉内没水！");
		} else {
			if (isBoiled()) {
				System.out.println("倒出中……倒出完成！");
				empty = true;
				boiled = false;
			} else {
				System.out.println("未烧开！");
			}
		}

	}

	public boolean isEmpty() {
		return empty;
	}

	public boolean isBoiled() {
		return boiled;
	}

}
```

#### 实验截图

![enter description here][1]

## 实验二
#### 利用4个线程分段求和1~100
线程1计算1-25之和；线程2计算26-50之和；以此类推

要求线程1完成之后执行线程2，之后执行线程3，最后执行线程4
打印每段求和结果，以及最后的总结果。即分别打印第一段求和结果，第二段求和结果，第三段求和结果，第四段求和结果，最终的求和结果

#### 代码


```java

public class ThreadTest2 extends Thread {
	private int start;
	private int end;
	private int sum = 0;
	public static int count = 1;

	public ThreadTest2(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void run() {
		try {
			for (int i = this.start; i <= this.end; i++) {
				this.sum += i;
				sleep(100);
			}
			System.out.println("第" + count + "次结果：" + this.sum);
			count++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ThreadTest2 test1 = new ThreadTest2(1, 25);
		ThreadTest2 test2 = new ThreadTest2(26, 50);
		ThreadTest2 test3 = new ThreadTest2(51, 75);
		ThreadTest2 test4 = new ThreadTest2(76, 100);
		ThreadTest2[] thr = { test1, test2, test3, test4 };
		try {
			for (int i = 0; i < thr.length; i++) {
				thr[i].start();
				thr[i].join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("最终求和结果：" + (test1.sum + test2.sum + test3.sum + test4.sum));
	}

}


``` 

#### 实验截图

![enter description here][2]


  [1]: ./1.png "1"
  [2]: ./2.png "2"