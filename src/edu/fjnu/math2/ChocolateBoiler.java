package edu.fjnu.math2;

public class ChocolateBoiler {

	public static void main(String[] args) {
		ChocolateBoiler chocolateBoiler = new ChocolateBoiler();
		chocolateBoiler.fill();
		chocolateBoiler.boil();
		chocolateBoiler.drain();
	}

	private boolean empty;
	private boolean boiled;
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

	public void fill() {
		if (this.isEmpty()) {
			System.out.println("填充中……填充完成！");
			this.empty = false;
		} else {
			System.out.println("只有空的锅炉才能填充巧克力和牛奶");
		}
	}

	public void boil() {
		if (!this.isEmpty()) {
			if (this.isBoiled()) {
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
		if (this.isEmpty()) {
			System.out.println("锅炉内没水！");
		} else {
			if (this.isBoiled()) {
				System.out.println("倒出中……倒出完成！");
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