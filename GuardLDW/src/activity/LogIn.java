package activity;



import com.hero.app.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import db.User;
import util.BaseActivity;
import util.HttpCallBackListener;
import util.HttpUtil;

public class LogIn extends BaseActivity{
	
	private EditText userNameEditText;
	private EditText userPasswordEditText;
	
	private Button loginButton; 
	private Button backButton;
	
	private String userName;
	private String userPassword;


	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        
        userNameEditText = (EditText)findViewById(R.id.edittext_loginname);
        userNameEditText.addTextChangedListener(new EditTextListener());
        
        userPasswordEditText = (EditText)findViewById(R.id.edittext_loginpassword);
        userPasswordEditText.addTextChangedListener(new EditTextListener());
        
        loginButton = (Button)findViewById(R.id.button_login);
        loginButton.setOnClickListener(new Listener());
        
        backButton = (Button)findViewById(R.id.button_loginback);
        backButton.setOnClickListener(new Listener());
        
	}
        

 
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_loginback){
				
				Intent intent = new Intent(LogIn.this, Main.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_login){
				
                //有网络的情况允许登录，无网络状况回到主界面
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "loginbutton");
				sendBroadcast(intent);
				
				userName = userNameEditText.getText().toString();
				userPassword = userPasswordEditText.getText().toString();
				
				User user = new User(userName, userPassword, "");
				
				if (userName.length() > 0 && userName.length() <= 10  && userPassword.length() > 0 && userPassword.length() <= 10){				
					
					//URL能够检测传递的用户名与密码是否匹配，如果都匹配，则返回登录成功，如果不匹配，返回登录失败
					HttpUtil.sendHttpPostRequest("http://GuardLDW/index.php", user, new HttpCallBackListener(){

						@Override
						public void onFinish(String response) {
							
							//如果返回登录成功
							if(response.equals("")){
								
								Toast.makeText(LogIn.this, "登录成功", Toast.LENGTH_SHORT);
								
								//跳转到主界面
								Intent intent = new Intent(LogIn.this, Main.class);
								startActivity(intent);
							
							//如果返回登录失败
							}else{
								
								Toast.makeText(LogIn.this, "登录失败", Toast.LENGTH_SHORT);
								userNameEditText.setText("");
								userPasswordEditText.setText("");
							}
							
						}

						@Override
						public void onError(Exception e) {
							// TODO Auto-generated method stub
							
						}
						
					});
				}
			}
			
			
		}
	}
	
	
	
	private class EditTextListener implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			
			//必须使用监听的Editable对象才能实时得到文本框内容
			if(s.toString().equals("")){
				
				loginButton.setEnabled(false);
				loginButton.setBackgroundColor(android.graphics.Color.parseColor("#D8BFD8"));
				
			}else{
				
				loginButton.setEnabled(true);
				loginButton.setBackgroundColor(android.graphics.Color.parseColor("#70f3ff"));
			}
		}
	}
	
	
	
}

						

						
						
						
						

