package com.chat.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatServer{

	ServerSocket server;
	Socket socket;
	
	String msg;
	MainChat mainChat;
	
	
	Map<String, DataOutputStream> map= new HashMap<>();
	
	
	public void setting(){
		
		try {
			Collections.synchronizedMap(map);
			server=new ServerSocket(8080);
		
		while(true){
			System.out.println("대기중");
			socket= server.accept();
			System.out.println(socket.getInetAddress()+"에서 접속");
			
			Reciever reciever=new Reciever(socket);
			reciever.start();
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public void setGui(MainChat mainChat){
		this.mainChat=mainChat;
	}
	
	public void addC(String nick,DataOutputStream out){
		System.out.println(nick+"님이 접속하였씁니다.");
		map.put(nick, out);
	}
	
	
	public void remove(String nick){
		map.remove(nick);
	}
	
	public void sendEar(String msg,String nick){
		
	}
	
	
	public void sendMessage(String msg){
		Iterator<String> it=map.keySet().iterator();
		String key="";
		while (it.hasNext()) {
			try {
				key=it.next();
				map.get(key).writeUTF(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	class Reciever extends Thread{
		DataInputStream in;
		DataOutputStream out;
		String nick;
		
		public Reciever(Socket socket){
			try {
				
			out=new DataOutputStream(socket.getOutputStream());
			in=new DataInputStream(socket.getInputStream());
			
			nick=in.readUTF();
			addC(nick, out);
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		@Override
		public void run() {
			try {
				while(in !=null){
					msg=in.readUTF();
					sendMessage(msg);
					mainChat.appendMsg(msg);
				}
			} catch (Exception e) {
				remove(nick);
			}
		}
		
		
	}
	
	
	
}
