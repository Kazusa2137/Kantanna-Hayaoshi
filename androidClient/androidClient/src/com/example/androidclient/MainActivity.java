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
	private TextView tv1;//緯度
	private TextView tv2;//経度
	private String id;//緯度
	private String kd;//経度
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bt = (Button)findViewById(R.id.button1);
		bt.setOnClickListener(this);//ボタンを押して位置情報を表示
		
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		
		ns = new NetSample();
	}
	
	protected void onResume() {
		// 画面が表示される直前
		super.onResume();
		
		Log.v("check", "onResume()");
		
		// ソケットオープン
		ns.connect("192.168.11.60", 50000);
	}
	
	
	protected void onPause() {
		// 画面がなくなった直後
		super.onPause();
		
		Log.v("check", "onPause()");
		
		// ソケットクローズ
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
		// TODO 自動生成されたメソッド・スタブ
		ns.send(id);//strは位置情報に変える
		
	}
	
	@Override
	 
	public void onStart() {
	 
	 super.onStart();
	 
	 // ロケーションマネージャのインスタンスを取得する
	 locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 
	 // 位置情報の更新を受け取るように設定
	 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, // プロバイダ
	 0, // 通知のための最小時間間隔
	 0, // 通知のための最小距離間隔
	 this); // 位置情報リスナー
	 
	 locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, this);
	 
	}
	 
	@Override
	 
	public void onStop() {	 
	super.onStop();
	 
	// 位置情報の更新を止める
	 locationManager.removeUpdates(this);
	 
	}
//locationlistenerに必要なメソッド４つ
	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		// 例としてラベルに取得した位置を表示
		 
		tv1.setText(Double.toString(location.getLatitude()));//緯度を取得
		id = Double.toString(location.getLatitude());
		tv2.setText(Double.toString(location.getLongitude()));//経度取得
		kd = Double.toString(location.getLongitude());
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO 自動生成されたメソッド・スタブ	
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ	
	}
}
