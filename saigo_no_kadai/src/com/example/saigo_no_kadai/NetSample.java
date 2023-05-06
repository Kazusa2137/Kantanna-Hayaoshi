package com.example.saigo_no_kadai;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.example.saigo_no_kadai.ServerSample.MyHandler;

import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class NetSample extends Thread{
	
	private int port;
	private String data;
	private MyHandler my;
	private NetIF nif;
	
	public NetSample(int port, NetIF nif) {
		this.port = port;
		this.nif = nif;
		new Thread(this).start();
	}

	 public void run() {
	 try{
//		 int port = Integer.parseInt(args[0]);

	  DatagramSocket sock = new DatagramSocket(port);
	   
	  while (true) {

	   byte buf[] = new byte[512];

	   DatagramPacket packet = new DatagramPacket(buf, buf.length);
	    
	   sock.receive(packet);

	   InetAddress ia = packet.getAddress();
	   String host = ia.toString();

	   port = packet.getPort();
	   String port1 = String.valueOf(port);

	   String data = new String(packet.getData(), 0 , packet.getLength());
	   
	   Log.v("check", "recive");
	
		nif.recv(host);
	   
	  }

	  }
	  catch(Exception e){
	   Log.v("check", e.toString());
	  }
	 }

	}