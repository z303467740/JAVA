## JAVA网络编程
### 改造教材19.2和19.3的例子，创建简单的聊天程序
1、服务器也具有类似客户端的UI界面
2、服务器也能够向客户端发送信息
3、客户端和服务器端UI界面要区分各自发送和接收到的信息，比如可以使用颜色区分彼此的聊天记录
MyTcp.java
```
package edu.fjnu.math6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class MyTcp extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedReader reader; // 创建BufferedReader对象
	private ServerSocket server; // 创建ServerSocket对象
	private Socket socket; // 创建Socket对象socket
	private JTextPane ta = new JTextPane(); // 创建JtextArea对象
	private JTextField tf = new JTextField(); // 创建JtextField对象
	Container cc; // 声明Container对象
	private PrintWriter writer; // 声明PrintWriter类对象

	public MyTcp(String title) { // 构造方法
		super(title); // 调用父类的构造方法
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane(); // 实例化对象
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf, "South"); // 将文本框放在窗体的下部

		Style def = ta.getStyledDocument().addStyle(null, null);
		StyleConstants.setFontFamily(def, "verdana");
		StyleConstants.setFontSize(def, 12);
		Style normal = ta.addStyle("normal", def);
		Style s = ta.addStyle("red", normal);
		StyleConstants.setForeground(s, Color.RED);
		ta.setParagraphAttributes(normal, true);

		tf.addActionListener(new ActionListener() {
			// 绑定事件
			public void actionPerformed(ActionEvent e) {
				// 将文本框中信息写入流
				writer.println(tf.getText());
				// 将文本框中信息显示在文本域中
				ta.setForeground(Color.red);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n服务器:" + tf.getText() + '\n', ta.getStyle("normal"));
				} catch (BadLocationException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				ta.setSelectionEnd(ta.getText().length());
				tf.setText(""); // 将文本框清空
			}
		});
	}

	void getserver() {
		try {
			server = new ServerSocket(8998); // 实例化Socket对象
			// ta.append("服务器套接字已经创建成功" + '\n'); // 输出信息
			ta.getDocument().insertString(ta.getDocument().getLength(), "服务器套接字已经创建成功" + '\n', ta.getStyle("normal"));

			while (true) { // 如果套接字是连接状态
				// ta.append("等待客户机的连接" + '\n'); // 输出信息
				ta.getDocument().insertString(ta.getDocument().getLength(), "等待客户机的连接" + '\n', ta.getStyle("normal"));
				socket = server.accept();
				connect();// 实例化Socket对象
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 实例化BufferedReader对象
				getClientMessage(); // 调用getClientMessage()方法
				writer = new PrintWriter(socket.getOutputStream(), true);
			}
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
	}

	private void getClientMessage() {
		try {
			while (true) { // 如果套接字是连接状态
				if (reader.ready()) {
					// 获得客户

					ta.setForeground(Color.blue);
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n客户机:" + reader.readLine() + '\n', ta.getStyle("red"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
		try {
			if (reader != null) {
				reader.close(); // 关闭流
			}
			if (socket != null) {
				socket.close(); // 关闭套接字
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connect() { // 连接套接字方法
		try { // 捕捉异常
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
	}

	public static void main(String[] args) { // 主方法
		MyTcp tcp = new MyTcp("服务器"); // 创建本类对象
		tcp.setSize(400, 400); // 设置窗体大小
		tcp.setVisible(true); // 将窗体显示
		tcp.getserver(); // 调用方法
	}

}

```
MyClient.java
```
package edu.fjnu.math6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class MyClient extends JFrame { // 创建类继承JFrame类
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedReader reader; // 创建BufferedReader对象
	private PrintWriter writer; // 声明PrintWriter类对象
	Socket socket; // 声明Socket对象
	private JTextPane ta = new JTextPane(); // 创建JtextArea对象
	private JTextField tf = new JTextField(); // 创建JtextField对象
	Container cc; // 声明Container对象

	public MyClient(String title) { // 构造方法
		super(title); // 调用父类的构造方法
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane(); // 实例化对象

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf, "South"); // 将文本框放在窗体的下部

		Style def = ta.getStyledDocument().addStyle(null, null);
		StyleConstants.setFontFamily(def, "verdana");
		StyleConstants.setFontSize(def, 12);
		Style normal = ta.addStyle("normal", def);
		Style s = ta.addStyle("red", normal);
		StyleConstants.setForeground(s, Color.RED);
		ta.setParagraphAttributes(normal, true);

		tf.addActionListener(new ActionListener() {
			// 绑定事件
			public void actionPerformed(ActionEvent e) {
				// 将文本框中信息写入流
				writer.println(tf.getText());
				// 将文本框中信息显示在文本域中
				// ta.setForeground(Color.red);
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n客户机:" + tf.getText() + '\n', ta.getStyle("normal"));
				} catch (BadLocationException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				ta.setSelectionEnd(ta.getText().length());
				tf.setText(""); // 将文本框清空
			}
		});
	}

	private void connect() throws BadLocationException { // 连接套接字方法
		// ta.append("尝试连接...\n"); // 文本域中提示信息
		ta.getDocument().insertString(ta.getDocument().getLength(), "尝试连接...\n", ta.getStyle("normal"));
		try { // 捕捉异常
			socket = new Socket("127.0.0.1", 8998); // 实例化Socket对象
			writer = new PrintWriter(socket.getOutputStream(), true);
			// ta.append("完成连接\n"); // 文本域中提示信息
			ta.getDocument().insertString(ta.getDocument().getLength(), "完成连接\n", ta.getStyle("normal"));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 实例化BufferedReader对象
			getTcpMessage(); // 调用getClientMessage()方法
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
	}

	private void getTcpMessage() {
		// TODO 自动生成的方法存根
		try {
			while (true) { // 如果套接字是连接状态
				if (reader.ready()) {
					// 获得客户端信息
					ta.setForeground(Color.blue);
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					ta.getDocument().insertString(ta.getDocument().getLength(),
							dateFormat.format(now) + "\n服务器:" + reader.readLine() + '\n', ta.getStyle("red"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // 输出异常信息
		}
		try {
			if (reader != null) {
				reader.close(); // 关闭流
			}
			if (socket != null) {
				socket.close(); // 关闭套接字
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { // 主方法
		MyClient clien = new MyClient("客户端"); // 创建本例对象
		clien.setSize(400, 400); // 设置窗体大小
		clien.setVisible(true); // 将窗体显示
		try {
			clien.connect();
		} catch (BadLocationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} // 调用连接方法
	}
}

```
### 实验截图

![enter description here][1]


  [1]: ./1.png "1"