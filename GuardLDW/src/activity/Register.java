package activity;

import java.util.ArrayList;
import java.util.List;

import com.hero.app.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import db.User;
import receiver.NetworkReceiver;
import util.ActivityControl;
import util.AnalyzeData;
import util.HttpCallBackListener;
import util.HttpUtil;
import util.Music;

public class Register extends Activity{
	
	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button registerButton;
	private Button backButton;
	private TextView registerResultTextView;
	//�洢����json���ݺ󷵻ص�User��list
	private List <User> userList = new ArrayList<User>();
	private String username = "";
	private String password = "";
	//��ʾע��Ľ��,1Ϊ�ɹ���2Ϊʧ��
	private int result = 0;
	//�ɹ�ע����˻�
	private User successUser;
	
	
	protected void onCreate(Bundle savedInstanceState) {
    	
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register);
       
        //���巵�ؼ�
        backButton = (Button)findViewById(R.id.button_registerback);
        backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Register.this, Main.class);
				startActivity(intent);
			}      	
        });
        
        usernameEditText = (EditText)findViewById(R.id.edittext_registername);
        passwordEditText = (EditText)findViewById(R.id.edittext_registerpassword);
        registerResultTextView = (TextView)findViewById(R.id.textview_registerresult);
        registerButton = (Button)findViewById(R.id.button_register);
        
        registerResultTextView.setText("��ʾע����");
        
        
        //ע�ᰴť  
        registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {	
							
                //��������������ע�ᣬ������״���ص�������
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "registerbutton");
				sendBroadcast(intent);	
				
				//���ע��ʱ����ȡ��ǰEditText�ϵ�username��password
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();
                
	
		
			   //�û��������볤�ȶ���1-10�������û������ظ�
				if (username.length() > 0 && username.length() <= 10  && password.length() > 0 && password.length() <= 10){				
					HttpUtil.sendHttpGetRequest("http://10.0.1.9:8026/weba/servlet/CustomerServlet", new HttpCallBackListener(){
						
						@Override		
						public void onFinish(String response) {	
							
							Log.d("what", "have response");	
							
							userList = AnalyzeData.handleUserResponese(response);//�Է��ص����ݽ��н���,����User���������							
							//����ע���û�ʱ����ѯ�Ƿ����û�����ͬ�����
							if (userList != null){
									
								for(User user : userList){	
										
									if (username.equals(user.getUsername())){
											
										result = 2;	
									}else{
												
										result = 1;
										}
									}	
								//��ע���û�ʱ
								}else{
									result = 1;
									}
							}
					@Override
					public void onError(Exception e) {	
						//û����ȷ��������
						Log.d("what", "getInfo fail");
					}	
				});
				//�û��������볤�Ȳ�����Ҫ��
				}else{
					result = 2;
					}	
				}

			});
        
        
		   //ע��ɹ��󣬽����û����û���������,���ۣ���ʼ״̬���ύ��������
	       if(result == 1){
	        	registerResultTextView.setText("ע��ɹ�");
	        	//ΪsuccessUser����ռ�
	        	successUser = new User();
	        	successUser.setUsername(username);
	        	successUser.setPassword(password);
	        	successUser.setComment("");
	        	HttpUtil.sendHttpPostRequest("http://10.0.1.9:8026/weba/servlet/CustomerServlet", successUser, new HttpCallBackListener(){

					@Override
					public void onFinish(String response) {
						Log.d("what", "register success");				
					}
					@Override
					public void onError(Exception e) {
						// TODO Auto-generated method stub						
					}	        	
	        	});
	        }else if(result == 2){
	        	registerResultTextView.setText("ע��ʧ�ܣ�������ע��");
	        }
	}
        

	
	
	
    @Override
    public void onBackPressed()//��Back���ļ���
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
		dialog.setMessage("�Ƿ�Ҫ�˳���Ϸ");
		dialog.setCancelable(false);//ֻ�ܵ���Ի���
		dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Music.stopBackgroundMusic();//����������ڲ��ţ�ֹͣ����
				ActivityControl.finishAll();

			}
		});  
		dialog.setNegativeButton("ȡ��",new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{ 
			}
		});  
		dialog.show();
		return;		
    }

}