package com.example.saigo_no_kadai;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class NetSample2 {
	private DatagramSocket sock = null;
	private InetAddress ia;
	private int port;

	public void connect(String host, int port) {
		try {

			sock = new DatagramSocket();

			ia = InetAddress.getByName(host);

			this.port = port;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(String data) {
		new SendThread(data).start();
	}

	public void close() {
		if (sock != null) {

			sock.close();
			sock = null;
		}
	}

	private class SendThread extends Thread {
		private String data;
		
		private SendThread(String data) {
			this.data = data;
			
			
		}
		public void run() {
			try{

				DatagramPacket packet = new DatagramPacket(
														data.getBytes(),				
														data.getBytes().length,			
														ia,
														port);

				sock.send(packet);
				

			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}