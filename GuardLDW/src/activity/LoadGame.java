package activity;



import java.io.File;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import util.ActivityControl;
import util.Fold;
import util.Music;
import android.widget.AdapterView.OnItemClickListener;

public class LoadGame extends Activity{
	
	//打开一次应用只运行一次
    public static String[] save = {"存档1          XXXX.XX.XX.XX.XX.XX","存档2          XXXX.XX.XX.XX.XX.XX","存档3          XXXX.XX.XX.XX.XX.XX",
                             "存档4          XXXX.XX.XX.XX.XX.XX","存档5          XXXX.XX.XX.XX.XX.XX","存档6          XXXX.XX.XX.XX.XX.XX",
                            };
    
    
	private ListView loadList;
	private Button backButton;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loadgame);
   	
        //定义返回键
        backButton = (Button)findViewById(R.id.button_loadgameback);
        backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoadGame.this, Main.class);
				startActivity(intent);
			}      	
        });
        
        
        //在loadList设置适配器前更改save数组的值
        Fold.checkSave(LoadGame.this);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoadGame.this, android.R.layout.simple_list_item_1, save);
        loadList = (ListView)findViewById(R.id.listview_loadgame);
        loadList.setAdapter(adapter);
      	loadList.setOnItemClickListener(new OnItemClickListener(){//ListView的点击子项响应方法
		
       		@Override
       		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   			
       			
       				Main.i = Integer.parseInt(Fold.load("savei" + position, LoadGame.this));//将字符形式的MainActivity.i转化为整形并读取
       				Intent intent = new Intent(LoadGame.this, StartGame.class);
       				startActivity(intent);//开始加载游戏（通过进入游戏的布局）
       				}     		 
       		});
      	}
	

	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(LoadGame.this);
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
