package receiver;

import activity.House;
import activity.LogIn;
import activity.Main;
import activity.Register;
import activity.Speical;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


/*
 * �жϵ�ǰ�����Ƿ����
 */


public class NetworkReceiver extends BroadcastReceiver{
	
	
	@Override
	public void onReceive(Context context, Intent intent) {//��Ҫ�Ĳ���������intent�еĶ���ֵ��
		
		ConnectivityManager connectionManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);//getSystemServiceֻ��ͨ������ܵ��ã��õ�ϵͳ������		
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();//�õ�NetworkInfo��ʵ��
		
		String key = intent.getStringExtra("key");
		if(networkInfo != null && networkInfo.isAvailable()){
			if (key.equals("house")){
				
				Intent intent1 = new Intent(context, House.class);
				intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent1);
			}else if(key.equals("register")){
				
				Intent intent2 = new Intent(context, Register.class);
				intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent2);
			}else if(key.equals("login")){
				
				Intent intent3 = new Intent(context, LogIn.class);
				intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent3);		
			//�����ע����ǵ�¼��ťʱ�����磬�������ڴ˽��в���
			}else if(key.equals("registerbutton")){
				
			}else if(key.equals("loginbutton")){
				
			}else if(key.equals("commentbutton")){
				
			}
		}
		else{
			Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
			//������״̬�µ��ע�ᰴť�ص�������
			if(key.equals("registerbutton")){
				
				Intent intent4 = new Intent(context, Main.class);
				intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent4);		
			}else if(key.equals("loginbutton")){
				
				Intent intent5 = new Intent(context, Main.class);
				intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent5);		
			}else if(key.equals("commentbutton")){
				
				Intent intent6 = new Intent(context, Main.class);
				intent6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent6);	
			}
		}
	}

}
