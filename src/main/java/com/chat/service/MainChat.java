package com.chat.service;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainChat extends JFrame implements ActionListener{
	
	JTextArea jta=new JTextArea(40,25);
	JTextField jtf=new JTextField(25);
	ChatServer server=new ChatServer();
	
	public MainChat() {
		add(jta,BorderLayout.CENTER);
		add(jtf,BorderLayout.SOUTH);
		jtf.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(800,100,400,600);
		setTitle("서버");
		server.setGui(this);
		server.setting();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg="쥔장: "+jtf.getText()+"\n";
		jta.append(msg);
		jtf.setText("");
		server.sendMessage(msg);
		
	}
	
	public static void main(String[] args) {
		new MainChat();
		
		
		
	}
	public void appendMsg(String msg) {
		jta.append(msg);
		
	}
}
