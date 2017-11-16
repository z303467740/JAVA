package edu.fjnu.math2.test2;

public class PepperoniPizza extends Pizza {

	@Override
	public void prepare() {

		System.out.println("prepare PepperoniPizza");

	}

	@Override
	public void bake() {

		System.out.println("back PepperoniPizza");
	}

	@Override
	public void cut() {

		System.out.println("cut PepperoniPizza");
	}

	@Override
	public void box() {

		System.out.println("box PepperoniPizza");
	}

}
