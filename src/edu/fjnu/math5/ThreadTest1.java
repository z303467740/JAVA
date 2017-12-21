package edu.fjnu.math5;

import edu.fjnu.math2.ChocolateBoiler;

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
