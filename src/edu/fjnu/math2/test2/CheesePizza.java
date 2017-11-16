package edu.fjnu.math2.test2;

public class CheesePizza extends Pizza {

	@Override
	public void prepare() {

		System.out.println("prepare CheesePizza");
	}

	@Override
	public void bake() {

		System.out.println("bake CheesePizza");
	}

	@Override
	public void cut() {

		System.out.println("cut CheesePizza");
	}

	@Override
	public void box() {

		System.out.println("box CheesePizza");
	}

}
