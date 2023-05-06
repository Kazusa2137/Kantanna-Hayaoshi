package com.example.saigo_no_kadai;

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
import android.widget.EditText;
import android.widget.TextView;

public class ClientSample extends Activity implements OnClickListener {
	
private TextView tv;
private TextView tv2;
private NetSample2 ns2;
//private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client);
		
		tv= (TextView)findViewById(R.id.textView1);
		tv2= (TextView)findViewById(R.id.textView2);
//		et = (EditText)findViewById(R.id.editText1);
		
		Button bt = (Button)findViewById(R.id.button1);
		bt.setOnClickListener(this);

		tv2.setText("出題者は" + Common.x + "さんです！");
		
		ns2 = new NetSample2();
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
			Intent Intent = new Intent(getApplicationContext(),IPSetting.class);
			startActivity(Intent);
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
		// TODO 自動生成されたメソッド・スタブ
		String str = new String("clicked button");
		// 送信
		ns2.send(str);
	}
	
	protected void onResume() {
		// 画面が表示される直前
		super.onResume();
		
		Log.v("check", "onResume()");
		
		// ソケットオープン
		ns2.connect(Common.x, 50000);
	}
	
	
	protected void onPause() {
		// 画面がなくなった直後
		super.onPause();
		
		Log.v("check", "onPause()");
		
		// ソケットクローズ
		ns2.close();
	}
	
}


