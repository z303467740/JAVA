package edu.fjnu.math2;

public class Test5 extends test1 {
	public void test1() {
		test1 test = new test1();
		// test.num1 = 1;// 同一包下该类的子类不能访问该类的私有成员
		test.num2 = 2;
		test.num3 = 3;
		test.num4 = 4;

	};
}
