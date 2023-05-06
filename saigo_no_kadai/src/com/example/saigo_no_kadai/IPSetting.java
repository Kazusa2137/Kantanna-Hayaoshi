package com.example.saigo_no_kadai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IPSetting extends Activity implements OnClickListener {
	
	private TextView tv;
	private EditText et;
	private NetSample2 ns2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ip);
		
		tv = (TextView)findViewById(R.id.textView1);
		et = (EditText)findViewById(R.id.editText1);
		
		Button bt = (Button)findViewById(R.id.button1);
		bt.setOnClickListener(this);
		
		// �C���X�^���X�쐬
		ns2 = new NetSample2();
	}

	@Override
	public void onClick(View arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
//		
//		// ���M
//		ns2.send(str);
		
		Intent intent = new Intent(getApplicationContext(), ClientSample.class);
		String str = et.getText().toString();
		Common.x = str;
		startActivity(intent);
	}
	
	protected void onResume() {
		// ��ʂ��\������钼�O
		super.onResume();
		
//		String str = et.getText().toString();
		
		Log.v("check", "onResume()");
	}
	
	
	protected void onPause() {
		// ��ʂ��Ȃ��Ȃ�������
		super.onPause();
		
		Log.v("check", "onPause()");
		
		// �\�P�b�g�N���[�Y
		ns2.close();
	}

	
}
