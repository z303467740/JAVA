package edu.fjnu.math2;

public class PowerTest02 {

	public void test1() {
		test1 test = new test1();
		// test.num1 = 1;// 同一包下的不同类不能访问该类私有成员
		test.num2 = 2;
		test.num3 = 3;
		test.num4 = 4;
	};
}
