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
	static  String username = "";
	private String password = "";
	static public String logInUsername;
	private Boolean result = false;
	List <User> userList = new ArrayList<User>();
	
	public void onCreate(Bundle savedInstanceState){
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        
        
        usernameEditText = (EditText)findViewById(R.id.edittext_loginname);
        passwordEditText = (EditText)findViewById(R.id.edittext_loginpassword);
        //定义返回键
        backButton = (Button)findViewById(R.id.button_loginback);
        backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LogIn.this, Main.class);
				startActivity(intent);
			}      	
        });
        
        loginButton = (Button)findViewById(R.id.button_login);
        loginButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();
				
				if (NetworkReceiver.network == 1){
					HttpUtil.sendHttpGetRequest("http://10.0.2.2/user.json", new HttpCallBackListener(){

						@Override
						public void onFinish(String response) {
							userList = AnalyzeData.handleUserResponese(response);
							if(userList != null){
								//判断用户名与密码是否匹配
								for (User user : userList){
									if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
										result = true;
										
										//建立数据库
										HeroDB.getInstance(LogIn.this, "User");
										//将当前登录的User对象的所有信息存入数据库，如果用户评论直接修改数据库中的对应值，再上传到服务器
										HeroDB.saveUser(user);
										//记录当前登录的用户的账号
										logInUsername = user.getUsername();
		
									}else{
										result = false;
									}
								}
							}else{
								result = false;
							}
							
						}

						@Override
						public void onError(Exception e) {
							// TODO 自动生成的方法存根
							
						}
						
					});
				}else if(NetworkReceiver.network == 0){
					Toast.makeText(LogIn.this, "network is unavailable", Toast.LENGTH_SHORT).show();
				}
			}
        });
        
        
	}

	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(LogIn.this);
		dialog.setMessage("是否要退出游戏");
		dialog.setCancelable(false);//只能点击对话框
		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Music.stopBackgroundMusic();//如果音乐正在播放，停止音乐
				ActivityControl.finishAll();

			}
		});  
		dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
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
