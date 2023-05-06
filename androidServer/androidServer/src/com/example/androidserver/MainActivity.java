package com.example.androidserver;
//5.1

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
	
	private TextView tv3;//緯度
	private TextView tv1;//経度
	//private Button bt;
	private NetSample ns;
	private MyHandler my;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("check", "oncreat");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv3 = (TextView)findViewById(R.id.textView3);
		tv1 = (TextView)findViewById(R.id.textView1);
		//bt = (Button)findViewById(R.id.button1);
		
		//bt.setOnClickListener(this);
		ns = new NetSample();
		 my = new MyHandler();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStart() {
	super.onStart();
		// TODO 自動生成されたメソッド・スタブ
		Log.v("check", "onstart");
		// TODO 自動生成されたメソッド・スタブ
		//Intent intent = getIntent();
		//String s = intent.getStringExtra("ID");
		//tv3.setText(s);
		//Log.v("check", "finish");
		ns.pre(my);
			
	}
	
	class MyHandler extends Handler {
		public void handleMessage(Message msg) {
			Log.v("check", "handler");
			Object obj = msg.obj;
			
			String str = obj.toString();
			tv3.setText(str);
		}		
   }
}
