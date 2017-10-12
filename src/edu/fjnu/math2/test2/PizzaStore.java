package edu.fjnu.math2.test2;

public class PizzaStore {

	public Pizza orderPizza(Pizza pizza) {
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	public PizzaStore(String type) {
		SimplePizzaFactory testFactory = new SimplePizzaFactory();
		Pizza pizza = testFactory.SimplePizzaFactory(type);
		pizza = this.orderPizza(pizza);
	}
}
