---
title: JAVA的面向对象基础
tags: JAVA,面向对象,基础
---

## JAVA访问权限修饰符的掌握
#### **一、Java中有四种访问权限， 其中三种有访问权限修饰符，分别为private、public、protected，还有一种不带任何修饰符（default）。**
1. private: Java语言中对访问权限限制的最窄的修饰符，一般称之为“私有的”。被其修饰的属性以及方法只能被该类的对象 访问，其子类不能访问，更不能允许跨包访问。
2. default：即不加任何访问修饰符，通常称为“默认访问权限“或者“包访问权限”。该模式下，只允许在同一个包中进行访问。
3. protected: 介于public 和 private 之间的一种访问修饰符，一般称之为“保护访问权限”。被其修饰的属性以及方法只能被类本     身的方法及子类访问，即使子类在不同的包中也可以访问。
4. public： Java语言中访问限制最宽的修饰符，一般称之为“公共的”。被其修饰的类、属性以及方法不仅可以跨类访问，而且     允许跨包访问。 
#### **二、下面用表格来展示四种修饰符的访问权限范围：** 

|                        | 同一个类 | 同一个包 | 不同包的子类 | 不同包的非子类 |
| -------------       | -------- | -------- | ------------ | -------------- |
| public             | √       | √       | √           | √             |
| protected       | √       | √       | √           |                |
| 默认(default)  | √       | √       |              |                |
| private            |   √         |          |              |                |

## 单例模式——使用单例模式完成下面描述的类

Choc-O-Holic公司有一个巧克力锅炉，用来把巧克力和牛奶融合在一起生产巧克力棒。定义这个巧克力锅炉类为ChocolateBoiler
ChocolateBoiler有两个私有的成员变量，empty和boiled，用来判断锅炉是否为空，以及锅炉内混合物是否已煮沸。注意两个成员变量恰当的初始值。
private boolean empty;
private boolean boiled;
ChocolateBoiler有三个方法来控制锅炉生产巧克力棒。
public void fill() {…} 向锅炉填满巧克力和牛奶的混合物。首先要判断锅炉是否为空，只有空的锅炉才能填充巧克力和牛奶（填充过程打印一条语句即可）。填充之后empty为false
public void boil() {…} 将炉内煮沸。首先判断标志位，只有锅炉是满的，并且没有煮过，才能进行该操作（煮沸操作打印一条语句即可）。煮沸后boiled标志位设置为true。
public void drain() {…} 排出煮沸的巧克力和牛奶。首先要进行标志位判断，只有锅炉是满的，并且锅炉已经煮沸之后，才能排出混合物（排出混合物的动作打印一条语句即可），排出混合物之后设置empty为true。
isEmpty和isBoiled方法来获取empty和boiled标志位的值

#### 代码
```java
public class ChocolateBoiler {

	public static void main(String[] args) {

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
			System.out.println("填充中……填充完成！");
			this.empty = false;
		} else {
			System.out.println("只有空的锅炉才能填充巧克力和牛奶");
		}
	}

	public void boil() {
		if (!this.isEmpty()) {
			if (this.isBoiled()) {
				System.out.println("煮开中……煮好了！");
			} else {
				System.out.println("已被煮过");
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
```
注： 确保一个类只有一个实例,并提供一个全局的访问点