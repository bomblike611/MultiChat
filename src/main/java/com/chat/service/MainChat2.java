package com.chat.service;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainChat2 extends JFrame implements ActionListener{
	
	static String nickName;
	JTextArea jta=new JTextArea(40,25);
	JTextField jtf=new JTextField(25);
	
	public MainChat2(){
		add(jta,BorderLayout.CENTER);
		add(jtf,BorderLayout.SOUTH);
		jtf.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(800,100,400,600);
		setTitle("클라이언트");
		
		client.setGui(this);
		client.setNick(nickName);
		client.connect();
	}
	
	ChatClient client=new ChatClient();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg=nickName+": "+jtf.getText()+"\n";
		jtf.setText("");
		client.sendMessage(msg);
		
	}
	public static void main(String[] args) {
		
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("-----서버에 접속하기 위한 아이디를 입력해주세요-----");
		nickName=sc.next();
		
		new MainChat2();
		
	}
	public void appendMsg(String msg) {
		jta.append(msg);
		
	}
}
