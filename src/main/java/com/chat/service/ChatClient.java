package com.chat.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	MainChat2 mainChat2;
	Socket socket;
	
	DataInputStream in;
	DataOutputStream out;
	String msg;
	String nick;
	
	public void setGui(MainChat2 mainChat2){
		this.mainChat2=mainChat2;
	}
	
	public void connect(){
		try {
			socket=new Socket("127.0.0.1", 8080);
			System.out.println("서버연결");
			
			out=new DataOutputStream(socket.getOutputStream());
			in=new DataInputStream(socket.getInputStream());
			
			out.writeUTF(nick);
			/*out.flush();*/
			
			while(in!=null){
				msg=in.readUTF();
				mainChat2.appendMsg(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void send(Scanner sc){
		System.out.println("내용을 입력하세요 : ");
		String msg=sc.nextLine();
		sendMessage(nick+": "+msg);
	}
	
	public void sendMessage(String msgs){
		try {
			out.writeUTF(msgs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setNick(String nickName){
		this.nick=nickName;
	}
	
	
	
	
}
