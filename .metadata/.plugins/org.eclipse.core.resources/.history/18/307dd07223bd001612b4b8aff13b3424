package activity;


import com.hero.app.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import util.ActivityControl;
import util.BaseActivity;
import util.Fold;
import util.Music;

public class Main extends BaseActivity{

	
	private Button enterButton;
	private Button loadButton;
	private Button setButton;
	private Button houseButton;
	private Button exitButton;
	private Button registerButton;
	private Button logInButton;
	private Button specialButton;
	
	//记录语句进行的位置
	public static int i = 0;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        enterButton = (Button)findViewById(R.id.button_entergame);
        enterButton.setOnClickListener(new Listener());
        
        loadButton = (Button)findViewById(R.id.button_loadgame);
        loadButton.setOnClickListener(new Listener());
        
        setButton = (Button)findViewById(R.id.button_setgame);
        setButton.setOnClickListener(new Listener());
        
        houseButton = (Button)findViewById(R.id.button_housegame);
        houseButton.setOnClickListener(new Listener());
        
        exitButton = (Button)findViewById(R.id.button_exitgame);
        exitButton.setOnClickListener(new Listener());
        
        registerButton = (Button)findViewById(R.id.button_mainregister);
        registerButton.setOnClickListener(new Listener());
        
        logInButton = (Button)findViewById(R.id.button_mainlogin);
        logInButton.setOnClickListener(new Listener());
        
        specialButton = (Button)findViewById(R.id.button_special);
        specialButton.setOnClickListener(new Listener());
        
        
        //建立初始文件,只会运行一次（函数中控制）
        Fold.build(Main.this);
        
        //初始化背景音乐
        if(Fold.load("music", Main.this).equals("1")){
        	
        	Music.startBackgroundMusic(Main.this);
        	
        }else{
        	
        	Music.stopBackgroundMusic();
        }
        
        //加载脚本，只会运行一次（函数中控制）
        Fold.saveScript(Main.this);

	}
	
	
	public boolean onCreateOptionsMenu(Menu menu){
		
        //MenuInflater class is used to instantiate menu XML files into Menu objects  
        MenuInflater inflater = getMenuInflater();  
        //将xml当做瘪的气球，inflate充气
        inflater.inflate(R.menu.main, menu);  
        return true;  
    }  
	
	
	public boolean onOptionsItemSelected(MenuItem item){  
		 
        int item_id = item.getItemId();  
  
        switch (item_id){
        
           case R.id.menu_license:
        	   
        	   Intent intent = new Intent(Main.this, License.class);
        	   startActivity(intent);
        	   break;   
        }  
        return true;  
    } 
	
	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_entergame){
				
				i = 0;
				Intent intent = new Intent(Main.this, StartGame.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_loadgame){
				
				Intent intent = new Intent(Main.this, LoadGame.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_setgame){
				
				Intent intent = new Intent(Main.this, Set.class);
				startActivity(intent);	

				
			}else if(v.getId() == R.id.button_housegame){
				
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "house");
				sendBroadcast(intent);
				
			}else if(v.getId() == R.id.button_mainregister){
				
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				intent.putExtra("key", "register");
				sendBroadcast(intent);
				
			}else if(v.getId() == R.id.button_mainlogin){
				
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				intent.putExtra("key", "login");
				sendBroadcast(intent);
				
			}else if(v.getId() == R.id.button_special){
				
				if(Fold.load("gaonai", Main.this).equals("1") && Fold.load("shixin", Main.this).equals("1")){        	
					Intent intent = new Intent(Main.this, Special.class);
					startActivity(intent);
				}else{
					Toast.makeText(Main.this, "请先通关所有线路", Toast.LENGTH_SHORT).show();
				}
				
			}else if(v.getId() == R.id.button_exitgame){
				
				//如果音乐正在播放，停止音乐
				Music.stopBackgroundMusic();
				ActivityControl.finishAll();	
			}
			
		}

	}

}
