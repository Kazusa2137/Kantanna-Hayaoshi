package com.example.androidclient;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, LocationListener{
	
	private NetSample ns;
	private LocationManager locationManager;
	private TextView tv1;//�ܓx
	private TextView tv2;//�o�x
	private String id;//�ܓx
	private String kd;//�o�x
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bt = (Button)findViewById(R.id.button1);
		bt.setOnClickListener(this);//�{�^���������Ĉʒu����\��
		
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		
		ns = new NetSample();
	}
	
	protected void onResume() {
		// ��ʂ��\������钼�O
		super.onResume();
		
		Log.v("check", "onResume()");
		
		// �\�P�b�g�I�[�v��
		ns.connect("192.168.11.60", 50000);
	}
	
	
	protected void onPause() {
		// ��ʂ��Ȃ��Ȃ�������
		super.onPause();
		
		Log.v("check", "onPause()");
		
		// �\�P�b�g�N���[�Y
		ns.close();
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
	public void onClick(View v) {
		Log.v("check", "onClick()");
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		ns.send(id);//str�͈ʒu���ɕς���
		
	}
	
	@Override
	 
	public void onStart() {
	 
	 super.onStart();
	 
	 // ���P�[�V�����}�l�[�W���̃C���X�^���X���擾����
	 locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 
	 // �ʒu���̍X�V���󂯎��悤�ɐݒ�
	 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, // �v���o�C�_
	 0, // �ʒm�̂��߂̍ŏ����ԊԊu
	 0, // �ʒm�̂��߂̍ŏ������Ԋu
	 this); // �ʒu��񃊃X�i�[
	 
	 locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, this);
	 
	}
	 
	@Override
	 
	public void onStop() {	 
	super.onStop();
	 
	// �ʒu���̍X�V���~�߂�
	 locationManager.removeUpdates(this);
	 
	}
//locationlistener�ɕK�v�ȃ��\�b�h�S��
	@Override
	public void onLocationChanged(Location location) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		// ��Ƃ��ă��x���Ɏ擾�����ʒu��\��
		 
		tv1.setText(Double.toString(location.getLatitude()));//�ܓx���擾
		id = Double.toString(location.getLatitude());
		tv2.setText(Double.toString(location.getLongitude()));//�o�x�擾
		kd = Double.toString(location.getLongitude());
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u	
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u	
	}
}
