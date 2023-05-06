package com.example.saigo_no_kadai;

import java.net.DatagramPacket;
import java.net.InetAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Handler;

public class ServerSample extends Activity implements NetIF, OnClickListener {
	
	private TextView tv1;
	private TextView tv2;
	private NetSample ns;
	private MyHandler my;
	private String buf;
	private InetAddress ia;
	private int port;
	private DatagramPacket packet;
	boolean flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_server);
		
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		
		Button bt = (Button)findViewById(R.id.button1);
		bt.setOnClickListener(this);
		
		new NetSample(50000, this);
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
		}else if(id == R.id.home_Menu) {
			Intent Intent = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(Intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View arg0) {
		String str = ("");
		tv2.setText(str);
		flag = false;
	}

	class MyHandler extends Handler {
		
		
		// TODO 自動生成されたメソッド・スタブ
		public void handleMessage(Message msg) {
			int what = msg.what;
			Object obj = msg.obj;
			
			if(flag == false) {
				String str = obj.toString();
				tv2.setText(str + "さんがボタンを押しました！");
				
				flag = true;
			}
			
			
		}
	}
	
	protected void onResume() {
		// 画面が表示される直前
		super.onResume();
		
		Log.v("check", "onResume()");

	}
	
	
	protected void onPause() {
		// 画面がなくなった直後
		super.onPause();
		
		Log.v("check", "onPause()");

	}

	@Override
	public void recv(String host) {
		// TODO 自動生成されたメソッド・スタブ
		Message msg = Message.obtain();
		msg.what = 1;
		msg.obj = new String(host);
		my.sendMessage(msg);
	}
	
}
