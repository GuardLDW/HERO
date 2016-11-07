package activity;

import java.util.ArrayList;
import java.util.List;

import com.hero.app.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import db.HeroDB;
import db.User;
import util.ActivityControl;

public class TestDB extends Activity{
	
	private Button creatDBButton;
	private Button creatTableButton;
	private Button getDataButton;
	private Button deleteButton;
	private Button updateButton;
	private HeroDB db;
	
	protected void onCreate(Bundle savedInstanceState) {
    	
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.db);
        
        creatDBButton = (Button)findViewById(R.id.button_creatDB);
        creatDBButton.setOnClickListener(new Listener());
        
        creatTableButton = (Button)findViewById(R.id.button_creatTable);
        creatTableButton.setOnClickListener(new Listener());
        
        getDataButton = (Button)findViewById(R.id.button_getData);
        getDataButton.setOnClickListener(new Listener());
        
	}
	
	//内部类实现监听
	class Listener implements android.view.View.OnClickListener{


		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_creatDB){
				
				db = new HeroDB(TestDB.this, "user.db");
				
			}else if(v.getId() == R.id.button_creatTable){
				
				db = new HeroDB(TestDB.this, "user.db");
				db.saveUser(new User("14081206", "666666", "nice"));
				db.saveUser(new User("14081205", "222222", "stupid"));
				
			}else if(v.getId() == R.id.button_getData){
				
				db = new HeroDB(TestDB.this, "user.db");
				List<User> userList = new ArrayList<User>();
				userList = db.loadUser();
				
				if(!(userList == null)){
					
					for(int i = 0; i < userList.size(); i++){
						
						Log.d("what", userList.get(i).getId() + "");
						Log.d("what", userList.get(i).getUsername());
						Log.d("what", userList.get(i).getPassword());
						Log.d("what", userList.get(i).getComment());
					}
				}
				
			}
		
			
			
		}
		
	}


}
