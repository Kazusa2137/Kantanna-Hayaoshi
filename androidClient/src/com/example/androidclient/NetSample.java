package com.example.androidclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class NetSample {
	private DatagramSocket sock = null;
	private InetAddress ia;
	private int port;
	
	// �\�P�b�g�쐬
	public void connect(String host, int port) {
		try {
			// �f�[�^�O�����\�P�b�g���\�z
			sock = new DatagramSocket();
			
			// IP�A�h���X���擾�iInetAddress�^�j
			ia = InetAddress.getByName(host);
			
			// �|�[�g
			this.port = port;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(String data) {
		new SendThread(data).start();
		Log.v("check", "send()");
	}

	// �\�P�b�g�N���[�Y
	public void close() {
		if (sock != null) {
			// �\�P�b�g�����
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
				
				// ���M�p�P�b�g����
				DatagramPacket packet = new DatagramPacket(
														data.getBytes(),				// String �N���X getBytes���\�b�h���p
														data.getBytes().length,			// �z��̓��� length���p
														ia,
														port);
				
				// �p�P�b�g���M
				sock.send(packet);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}
