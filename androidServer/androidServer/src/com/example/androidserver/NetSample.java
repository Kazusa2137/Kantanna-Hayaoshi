package com.example.androidserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.example.androidserver.MainActivity.MyHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class NetSample extends Activity implements Runnable{
	private String data;
	private MyHandler my;
	
	public void pre(MyHandler my) {
		// TODO 自動生成されたメソッド・スタブ
//		NetSample nt = new NetSample();
		Thread t = new Thread(this);
		t.start();
		
		this.my = my;
	}

	public void run() {
	try{
		Log.v("check", "run");
		int port = 50002;
			
		// データグラムソケットを構築し、ローカルホストマシン上の指定されたポートにバインド
		DatagramSocket sock = new DatagramSocket(port);
			
		while (true) {
			// 受信データ配列用意
			byte buf[] = new byte[512];
				
			// 長さが length のパケットを受信するための DatagramPacket を構築
			// DatagramPacket(byte[] buf, int length) コンストラクタ
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
				
			// このソケットからのデータグラムパケットを受信
			sock.receive(packet);
				
			// データグラムの送信元であるマシンの IP アドレス取得
			InetAddress ia = packet.getAddress();
			String host = ia.toString();

			// データグラムの送信元の、リモートホスト上のポート番号取得
			port = packet.getPort();
				
			// Dat ntent = new Intent(getApplicationContext(), MainActivity.class);
			//intent.putExtra("ID", data);
			data = new String(packet.getData(), 0 , packet.getLength());
			
			Message msg = Message.obtain();
			msg.obj = new String(data);
			my.sendMessage(msg);
		}
			// ソケットを閉じる
			// sock.close();
		}
		catch(Exception e){
			Log.v("check", e.toString());
		}
	}

}
