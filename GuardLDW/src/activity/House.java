package activity;

import java.util.ArrayList;
import java.util.List;
import com.hero.app.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import db.HeroDB;
import db.User;
import util.ActivityControl;
import util.HttpCallBackListener;
import util.HttpUtil;

public class House extends Activity{
	
	
	private ListView commentListView;	
	private EditText commentEditText;
	private Button commentButton;
	private String comment;
	//�ύ���ۺ������ݿ��и��ĵ�ǰ�����comment����
	private User user;
	

	
	static public List<String> commentList = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState); 
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.house);
        
        commentListView = (ListView)findViewById(R.id.listview_comment);
        //commentListView��������
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(House.this, android.R.layout.simple_list_item_1, commentList);
        
        commentEditText = (EditText)findViewById(R.id.edittext_comment);
        commentButton = (Button)findViewById(R.id.button_comment);
        commentButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
								
                //��������������ע�ᣬ������״���ص�������
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "commentbutton");
				sendBroadcast(intent);	
				
				comment = commentEditText.getText().toString();
				
				//����User���ݿ�������Ϊ��ǰ��¼�û������û���comment����
				HeroDB.updateUserComment(comment, LogIn.logInUsername, "User");
				
				//����ǰ����������ύ��������
				HttpUtil.sendHttpPostRequest("http://10.0.1.9:8026/weba/servlet/CustomerServlet", user, new HttpCallBackListener(){

					@Override
					public void onFinish(String response) {
			
					}

					@Override
					public void onError(Exception e) {
						
					}					
				});
				
		
			}
        	
        });

	}

}