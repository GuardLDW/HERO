package activity;


import com.hero.app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import receiver.NetworkReceiver;
import util.ActivityControl;
import util.Fold;
import util.Music;

public class Main extends Activity{

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
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        ActivityControl.addActivity(this);
        
        enterButton = (Button)findViewById(R.id.button_entergame);
        loadButton = (Button)findViewById(R.id.button_loadgame);
        setButton = (Button)findViewById(R.id.button_setgame);
        houseButton = (Button)findViewById(R.id.button_housegame);
        exitButton = (Button)findViewById(R.id.button_exitgame);
        registerButton = (Button)findViewById(R.id.button_mainregister);
        logInButton = (Button)findViewById(R.id.button_mainlogin);
        specialButton = (Button)findViewById(R.id.button_special);
        
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
        
        
        enterButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				i = 0;
				Intent intent = new Intent(Main.this, StartGame.class);
				startActivity(intent);
				
			}       	
        });
        
        loadButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Main.this, LoadGame.class);
				startActivity(intent);
			}
        });
        
        setButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Main.this, Set.class);
				startActivity(intent);				
			}       	
        });
        
        houseButton.setOnClickListener(new OnClickListener(){
      	
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "house");
				sendBroadcast(intent);
				Log.d("what", "house");
			}       	
        });
        
        exitButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {	
				//如果音乐正在播放，停止音乐
				Music.stopBackgroundMusic();
				ActivityControl.finishAll();				
			}       	
        });
        
        registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				intent.putExtra("key", "register");
				sendBroadcast(intent);		
			}       	
        });
        
        
        logInButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				intent.putExtra("key", "login");
				sendBroadcast(intent);				
			}       	
        });
                
        specialButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(Fold.load("gaonai", Main.this).equals("1") && Fold.load("shixin", Main.this).equals("1")){        	
					Intent intent = new Intent(Main.this, Special.class);
					startActivity(intent);
				}else{
					Toast.makeText(Main.this, "请先通关所有线路", Toast.LENGTH_SHORT).show();
				}
			}       	
        });
        
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu)  
    {  
        //MenuInflater class is used to instantiate menu XML files into Menu objects  
        MenuInflater inflater = getMenuInflater();  
        //将xml当做瘪的气球，inflate充气
        inflater.inflate(R.menu.main, menu);  
        return true;  
    }  
	
	
	public boolean onOptionsItemSelected(MenuItem item){  
		
        //得到当前选中的MenuItem的ID,  
        int item_id = item.getItemId();  
  
        switch (item_id){
        
           case R.id.menu_license:
        	   
        	   Intent intent = new Intent(Main.this, License.class);
        	   startActivity(intent);
        	   break;
        	   
        	  
        }  
        return true;  
    }  
	
	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(Main.this);
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
