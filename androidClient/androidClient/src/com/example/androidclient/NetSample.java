package com.example.androidclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class NetSample {
	private DatagramSocket sock = null;
	private InetAddress ia;
	private int port;
	
	// ソケット作成
	public void connect(String host, int port) {
		try {
			// データグラムソケットを構築
			sock = new DatagramSocket();
			
			// IPアドレスを取得（InetAddress型）
			ia = InetAddress.getByName(host);
			
			// ポート
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

	// ソケットクローズ
	public void close() {
		if (sock != null) {
			// ソケットを閉じる
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
				
				// 送信パケット生成
				DatagramPacket packet = new DatagramPacket(
														data.getBytes(),				// String クラス getBytesメソッド利用
														data.getBytes().length,			// 配列の特徴 length利用
														ia,
														port);
				
				// パケット送信
				sock.send(packet);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}
