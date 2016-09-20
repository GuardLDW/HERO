package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/*
 * 判断当前网络是否可用,网络状态发生改变时自动发送系统广播，网络状态没变时手动发送广播
 */


public class NetworkReceiver extends BroadcastReceiver{
	
	public static int network = 2;//广播发送与接受需要经历时间
	
	@Override
	public void onReceive(Context context, Intent intent) {//需要的参数放置在intent中的额外值中
		
		ConnectivityManager connectionManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);//getSystemService只有通过活动才能调用，得到系统服务类		
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();//得到NetworkInfo的实例
		
		//如果有网络，network值为1，否则为0
		if(networkInfo != null && networkInfo.isAvailable()){
			network = 1;
		}
		else{
		    network = 0;
		}
	}

}

