package edu.fjnu.math2.test2;

public class SimplePizzaFactory {
	public Pizza SimplePizzaFactory(String type) {
		if (type.equals("CheesePizza")) {
			Pizza pizza = new CheesePizza();
			return pizza;
		} else if (type.equals("PepperoniPizza")) {
			Pizza pizza = new PepperoniPizza();
			return pizza;
		} else if (type.equals("ClamPizza")) {
			Pizza pizza = new ClamPizza();
			return pizza;
		} else {
			return null;
		}
	}
}
