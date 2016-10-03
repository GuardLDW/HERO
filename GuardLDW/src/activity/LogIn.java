package activity;

import java.util.ArrayList;
import java.util.List;

import com.hero.app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import db.HeroDB;
import db.User;
import receiver.NetworkReceiver;
import util.ActivityControl;
import util.AnalyzeData;
import util.HttpCallBackListener;
import util.HttpUtil;
import util.Music;

public class LogIn extends Activity{
	
	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button loginButton; 
	private Button backButton;
	private TextView resultTextView;
	private  String username = "";
	private String password = "";
	static public String logInUsername;
	//resultֵΪ1��¼�ɹ���ֵΪ2��¼ʧ��
	private int result = 0;
	List <User> userList = new ArrayList<User>();
	
	public void onCreate(Bundle savedInstanceState){
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        
        
        usernameEditText = (EditText)findViewById(R.id.edittext_loginname);
        passwordEditText = (EditText)findViewById(R.id.edittext_loginpassword);
        loginButton = (Button)findViewById(R.id.button_login);
        backButton = (Button)findViewById(R.id.button_loginback);
        resultTextView = (TextView)findViewById(R.id.textview_loginresult);
        
        resultTextView.setText("��ʾ��¼���");
        
        //���巵�ؼ�
        backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LogIn.this, Main.class);
				startActivity(intent);
			}      	
        });
        

        loginButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
                //����������������¼��������״���ص�������
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "registerbutton");
				sendBroadcast(intent);
				
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();
				
	
				
				HttpUtil.sendHttpGetRequest("http://10.0.1.9:8026/weba/servlet/CustomerServlet", new HttpCallBackListener(){

					@Override
					public void onFinish(String response) {
						userList = AnalyzeData.handleUserResponese(response);
						if(userList != null){
							//�ж��û����������Ƿ�ƥ��
							for (User user : userList){
								if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
									result = 1;
									
									//�������ݿ�
									HeroDB.getInstance(LogIn.this, "User");
									
									//����ǰ��¼��User�����������Ϣ�������ݿ⣬����û�����ֱ���޸����ݿ��еĶ�Ӧֵ�����ϴ���������
									HeroDB.saveUser(user);
									
									//��¼��ǰ��¼���û����˺�
									logInUsername = user.getUsername();

								}else{
									result = 2;
								}
							}
					    //userlistΪ�������û�ע��
						}else{
							result = 2;
						}
							
					}
					@Override
					public void onError(Exception e) {
						// TODO �Զ����ɵķ������						
					}
				});
			}
        });
        
        if(result == 1){
        	resultTextView.setText("��¼�ɹ����ɽ�����Ϸ������������");
        }else if(result == 2){
        	resultTextView.setText("��¼ʧ��");
        }
        
        
	}

	
    @Override
    public void onBackPressed()//��Back���ļ���
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(LogIn.this);
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