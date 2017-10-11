package edu.fjnu.math2.powertest;

import edu.fjnu.math2.test1;

public class Test4 extends test1 {
	public void test1() {
		test1 test = new test1();
		// test.num1 = 1;// 不同包下的该类的子类不能访问该类的私有成员
		test.num2 = 2;
		// test.num3 = 3;// 不同包下的该类的子类不能访问该类的保护成员
		// test.num4 = 4;// 不同包下的该类的子类不能访问该类的默认成员
	};
}
