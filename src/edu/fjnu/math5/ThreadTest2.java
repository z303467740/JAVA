package edu.fjnu.math5;

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
