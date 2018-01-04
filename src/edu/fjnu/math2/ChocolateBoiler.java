package edu.fjnu.math2;

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