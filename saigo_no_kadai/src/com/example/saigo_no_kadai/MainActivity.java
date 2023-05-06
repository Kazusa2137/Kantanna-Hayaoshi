package com.example.saigo_no_kadai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private TextView tv;
	private Button bt1;
	private Button bt2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView)findViewById(R.id.textView1);
		
		bt1 = (Button)findViewById(R.id.button1);//syutudaisya
		bt1.setOnClickListener(this);
		
		bt2 = (Button)findViewById(R.id.button2);//kaitousya
		bt2.setOnClickListener(this);
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
	public void onClick(View arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
		if(arg0 == bt1) {
			//出題者画面に遷移
			Intent Intent = new Intent(getApplicationContext(),ServerSample.class);
			startActivity(Intent);
		}else{
			//解答者画面に遷移
			Intent Intent = new Intent(getApplicationContext(),ClientSample.class);
			startActivity(Intent);
		}
	}
}
