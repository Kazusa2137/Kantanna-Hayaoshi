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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
//		NetSample nt = new NetSample();
		Thread t = new Thread(this);
		t.start();
		
		this.my = my;
	}

	public void run() {
	try{
		Log.v("check", "run");
		int port = 50002;
			
		// �f�[�^�O�����\�P�b�g���\�z���A���[�J���z�X�g�}�V����̎w�肳�ꂽ�|�[�g�Ƀo�C���h
		DatagramSocket sock = new DatagramSocket(port);
			
		while (true) {
			// ��M�f�[�^�z��p��
			byte buf[] = new byte[512];
				
			// ������ length �̃p�P�b�g����M���邽�߂� DatagramPacket ���\�z
			// DatagramPacket(byte[] buf, int length) �R���X�g���N�^
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
				
			// ���̃\�P�b�g����̃f�[�^�O�����p�P�b�g����M
			sock.receive(packet);
				
			// �f�[�^�O�����̑��M���ł���}�V���� IP �A�h���X�擾
			InetAddress ia = packet.getAddress();
			String host = ia.toString();

			// �f�[�^�O�����̑��M���́A�����[�g�z�X�g��̃|�[�g�ԍ��擾
			port = packet.getPort();
				
			// Dat ntent = new Intent(getApplicationContext(), MainActivity.class);
			//intent.putExtra("ID", data);
			data = new String(packet.getData(), 0 , packet.getLength());
			
			Message msg = Message.obtain();
			msg.obj = new String(data);
			my.sendMessage(msg);
		}
			// �\�P�b�g�����
			// sock.close();
		}
		catch(Exception e){
			Log.v("check", e.toString());
		}
	}

}
