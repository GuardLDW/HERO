package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import db.User;


/*
 *提供向服务器发送数据与从服务器获取数据的方法 
 */

public class HttpUtil {

	//发送请求从服务器获取数据，在onfinish中队数据进行处理
	public static void sendHttpGetRequest(final String address, final HttpCallBackListener listener){
		new Thread(new Runnable(){//耗时操作开启子线程
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try{
					URL url = new URL(address);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(8000);
					connection.setConnectTimeout(8000);
										
					//处理响应数据
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line = null;
					while((line = reader.readLine())!= null){
						response.append(line);
					}
					//调用sendHttpReaquest方法时传入的new HttpCallbackListener参数中重写onFinish与onError方法
					if(listener != null){
						//回调onFinish方法
						listener.onFinish(response.toString());
					}
				}catch(Exception e){
					if(listener != null){
						//回调onError方法
						listener.onError(e);
					}					
				}finally{
					//finally 结构使代码总会执行，而不管有无异常发生。使用 finally 可以维护对象的内部状态，并可以清理非内存资源
					if(connection != null){
						connection.disconnect();
					}
				}							
			}			
		}).start();
	}
	
	
	//向服务器的文件中提交json数据，传入一个User对象作为参数
	public static void sendHttpPostRequest(final String address, final User successUser, final HttpCallBackListener listener){
		new Thread(new Runnable(){
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try{
					URL url = new URL(address);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("POST");
		            connection.setConnectTimeout(3000);//设置连接超时时间
		            connection.setDoInput(true);//打开输入流，以便从服务器获取数据
		            connection.setDoOutput(true);//打开输出流，以便向服务器提交数据
		            connection.setUseCaches(false);//使用Post方式不能使用缓存
					DataOutputStream out = new DataOutputStream(connection.getOutputStream());
					//Post提交的数据以键值对的形式存在，数据之间用&分隔
					out.writeBytes("username=" + successUser.getUsername() + "&password=" + successUser.getPassword() +
							"&comment=" + successUser.getComment());
					
					//处理响应数据
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line = null;
					while((line = reader.readLine())!= null){
						response.append(line);
					}
					//调用sendHttpReaquest方法时传入的new HttpCallbackListener参数中重写onFinish与onError方法
					if(listener != null){
						//回调onFinish方法
						listener.onFinish(response.toString());
					}
				}catch(Exception e){
					if(listener != null){
						//回调onError方法
						listener.onError(e);
					}					
				}finally{
					//finally 结构使代码总会执行，而不管有无异常发生。使用 finally 可以维护对象的内部状态，并可以清理非内存资源
					if(connection != null){
						connection.disconnect();
					}
				}							
			}					
			}).start();
		}
}
