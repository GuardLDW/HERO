package activity;

import java.util.ArrayList;
import java.util.List;
import com.hero.app.R;
import android.app.Activity;
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
import util.HttpUtil;

public class House extends Activity{
	
	
	private ListView commentListView;	
	private EditText commentEditText;
	private Button commentButton;
	private String comment;
	private List <User> list = new ArrayList<User>();
	

	
	static public List<String> notifyList = new ArrayList<String>(); 
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
				
				comment = commentEditText.getText().toString();
				
				HeroDB.getInstance(House.this, "User");
				list = HeroDB.loadUser();
				if(list != null){
					for(User user : list){
						if(LogIn.logInUsername.equals(user.getUsername())){
							
							//�������ݿ��е�ǰ��¼�û���user_comment
							HeroDB.updateUserComment(comment, LogIn.logInUsername);
							
							//��������ĵ�ǰ�û���Ϣ�ύ��������
							//HttpUtil.sendHttpPostRequest("http://10.0.0.2", user);
							
							//����ǰcomment��ʱ��ʾ
                            commentList.add(comment);//�ı�������adpter1
                            commentListView.setAdapter(adapter1);
						}
					}
				}

				
				
			}
        	
        });

	}

}
