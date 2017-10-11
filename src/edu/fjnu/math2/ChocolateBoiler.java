package edu.fjnu.math2;

public class ChocolateBoiler {

	public static void main(String[] args) {
		ChocolateBoiler test = new ChocolateBoiler();
		test.fill();
		test.boil();
		test.drain();
	}

	private boolean empty;
	private boolean boiled;
	private static ChocolateBoiler chocolateBoiler;

	private ChocolateBoiler() {
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
			System.out.println("����С��������ɣ�");
			this.empty = false;
		} else {
			System.out.println("ֻ�пյĹ�¯��������ɿ�����ţ��");
		}
	}

	public void boil() {
		if (!this.isEmpty()) {
			if (this.isBoiled()) {
				System.out.println("���С�������ˣ�");
			} else {
				System.out.println("�ѱ����");
			}

		} else {
			System.out.println("��¯��ûˮ��");
		}
	}

	public void drain() {
		if (this.isEmpty()) {
			System.out.println("��¯�գ�");
		} else {
			if (this.isBoiled()) {
				System.out.println("�����С���������ɣ�");
			} else {
				System.out.println("δ�տ���");
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
