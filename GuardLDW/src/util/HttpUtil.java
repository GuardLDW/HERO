package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import db.User;


/*
 *�ṩ�����������������ӷ�������ȡ���ݵķ��� 
 */

public class HttpUtil {

	//��������ӷ�������ȡ���ݣ���onfinish�ж����ݽ��д���
	public static void sendHttpGetRequest(final String address, final HttpCallBackListener listener){
		new Thread(new Runnable(){//��ʱ�����������߳�
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try{
					URL url = new URL(address);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(8000);
					connection.setConnectTimeout(8000);
										
					//������Ӧ����
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line = null;
					while((line = reader.readLine())!= null){
						response.append(line);
					}
					//����sendHttpReaquest����ʱ�����new HttpCallbackListener��������дonFinish��onError����
					if(listener != null){
						//�ص�onFinish����
						listener.onFinish(response.toString());
					}
				}catch(Exception e){
					if(listener != null){
						//�ص�onError����
						listener.onError(e);
					}					
				}finally{
					//finally �ṹʹ�����ܻ�ִ�У������������쳣������ʹ�� finally ����ά��������ڲ�״̬���������������ڴ���Դ
					if(connection != null){
						connection.disconnect();
					}
				}							
			}			
		}).start();
	}
	
	
	//����������ļ����ύjson���ݣ�����һ��User������Ϊ����
	public static void sendHttpPostRequest(final String address, final User successUser, final HttpCallBackListener listener){
		new Thread(new Runnable(){
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try{
					URL url = new URL(address);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("POST");
		            connection.setConnectTimeout(3000);//�������ӳ�ʱʱ��
		            connection.setDoInput(true);//�����������Ա�ӷ�������ȡ����
		            connection.setDoOutput(true);//����������Ա���������ύ����
		            connection.setUseCaches(false);//ʹ��Post��ʽ����ʹ�û���
					DataOutputStream out = new DataOutputStream(connection.getOutputStream());
					//Post�ύ�������Լ�ֵ�Ե���ʽ���ڣ�����֮����&�ָ�
					out.writeBytes("username=" + successUser.getUsername() + "&password=" + successUser.getPassword() +
							"&comment=" + successUser.getComment());
					
					//������Ӧ����
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line = null;
					while((line = reader.readLine())!= null){
						response.append(line);
					}
					//����sendHttpReaquest����ʱ�����new HttpCallbackListener��������дonFinish��onError����
					if(listener != null){
						//�ص�onFinish����
						listener.onFinish(response.toString());
					}
				}catch(Exception e){
					if(listener != null){
						//�ص�onError����
						listener.onError(e);
					}					
				}finally{
					//finally �ṹʹ�����ܻ�ִ�У������������쳣������ʹ�� finally ����ά��������ڲ�״̬���������������ڴ���Դ
					if(connection != null){
						connection.disconnect();
					}
				}							
			}					
			}).start();
		}
}