package activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hero.app.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import util.ActivityControl;
import util.Fold;
import util.Music;
import android.widget.AdapterView.OnItemClickListener;

public class StartGame extends Activity{

	private ImageView gameImage;
	private Button dialogButton;
	private Button gamestartsetButton;
	private boolean gameset;
	private Button saveButton;
	private Button backButton;
	private Button fastButton;
	private Button clearButton;
	
	protected void onCreate(Bundle savedInstanceState) {	
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game);
        
        //初始状态下点击(颜文字)按钮，显示4个按钮
        gameset = true;
        
        gameImage = (ImageView)findViewById(R.id.image_game);
        dialogButton = (Button)findViewById(R.id.button_dialog);
        gamestartsetButton = (Button)findViewById(R.id.button_startgameset);
        saveButton = (Button)findViewById(R.id.button_save);
        backButton = (Button)findViewById(R.id.button_backtotitle);
        fastButton = (Button)findViewById(R.id.button_fast);
        clearButton = (Button)findViewById(R.id.button_remove);
        

        
        backButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartGame.this, Main.class);
				startActivity(intent);					
			}        	
        });
        
        
        fastButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Main.i = 391;
				Intent intent = new Intent(StartGame.this, StartGame.class);
				startActivity(intent);		
			}     	
        });
        
        
        clearButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {				
				dialogButton.setBackgroundColor(android.graphics.Color.parseColor("#00FFFFFF"));
				dialogButton.setText("");
			}        	
        });
        
        gamestartsetButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				if(gameset){
					saveButton.setVisibility(View.VISIBLE);
					backButton.setVisibility(View.VISIBLE);
					fastButton.setVisibility(View.VISIBLE);
					clearButton.setVisibility(View.VISIBLE);
					gameset = false;
				}else{
					saveButton.setVisibility(View.GONE);
					backButton.setVisibility(View.GONE);
					fastButton.setVisibility(View.GONE);
					clearButton.setVisibility(View.GONE);
					gameset = true;
				}
			}
        	
        });
        
        
        
        saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				setContentView(R.layout.loadgame);
				
				//在loadList设置适配器前更改save数组的值
		        Fold.checkSave(StartGame.this);			
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(StartGame.this, android.R.layout.simple_list_item_1, LoadGame.save);//适配器中储存着存档格式
		       	final ListView loadList = (ListView)findViewById(R.id.listview_loadgame);//列表组件
		       	loadList.setAdapter(adapter);//使用适配器，加载ListView的值
		       	loadList.setOnItemClickListener(new OnItemClickListener(){//定义了ListView的点击子项响应方法

		    	@Override
		    	public void onItemClick(AdapterView<?> parent, View view, final int position,long id) 
		    	{	 
		    		long l = System.currentTimeMillis();
		    		Date date = new Date(l);
		    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		final String str = dateFormat.format(date);
		    		
		    		AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    		dialog.setMessage("进度保存在存档" + (position + 1) + "，确认？");
		    		//只能点击对话框
		    		dialog.setCancelable(false);
		    		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener(){
		    			@Override
		    			public void onClick(DialogInterface dialog, int which){
		    					//将当前i值与存档时间保存到相应文件
		    					Fold.save("save" + position, "存档" + (position + 1) + "          " + str, StartGame.this);
		    					Fold.save("savei" + position, String.valueOf(Main.i), StartGame.this);
		    					//能够即时显示存档时间
		    					LoadGame.save[position] = "存档" + (position + 1) + "          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据 
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
		    		}  			
		       	});
		       	
		       	Button backtogame = (Button)findViewById(R.id.button_loadgameback);//定义菜单保存存档界面的返回键
		       	backtogame.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						
						Intent intent = new Intent(StartGame.this, StartGame.class);
					    startActivity(intent);
					}    		
		       	});
				
			}
        	
        });
        
        //进入该活动时，首先根据i值加载对话内容与背景图片
        dialogButton.setText(Fold.loadScript(StartGame.this, String.valueOf(Main.i)));
        if(Main.i >= 0 && Main.i < 5){
            gameImage.setImageResource(R.drawable.game1);
        }else if(Main.i >= 5 && Main.i < 62){
        	gameImage.setImageResource(R.drawable.game2);
        }else if(Main.i >= 62 && Main.i < 102){
        	gameImage.setImageResource(R.drawable.game3);
        }else if(Main.i >= 102 && Main.i < 144){
        	gameImage.setImageResource(R.drawable.game4);
        }else if(Main.i >= 144 && Main.i < 166){
        	gameImage.setImageResource(R.drawable.game5);
        }else if(Main.i >= 166 && Main.i < 221){
        	gameImage.setImageResource(R.drawable.game6);
        }else if(Main.i >= 221 && Main.i < 247){
        	gameImage.setImageResource(R.drawable.game7);
        }else if(Main.i >= 247 && Main.i < 264){
        	gameImage.setImageResource(R.drawable.game8);
        }else if(Main.i >= 264 && Main.i < 279){
        	gameImage.setImageResource(R.drawable.game9);
        }else if(Main.i >= 279 && Main.i < 297){
        	gameImage.setImageResource(R.drawable.game7);
        }else if(Main.i >= 297 && Main.i < 331){
        	gameImage.setImageResource(R.drawable.game10);
        }else if(Main.i >= 331 && Main.i < 362){
        	gameImage.setImageResource(R.drawable.game11);
        }else if(Main.i >= 362 && Main.i < 377){
        	gameImage.setImageResource(R.drawable.game8);
        }else if(Main.i >= 377 && Main.i < 397){
        	gameImage.setImageResource(R.drawable.game12);
        }else if(Main.i >= 397 ){
        	gameImage.setImageResource(R.drawable.game13);
        	if(Main.i == 411){
        		
        	}
        }
        
        
 
        
        dialogButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				dialogButton.setBackgroundColor(android.graphics.Color.parseColor("#afDA70D6"));
				//点击一次按钮i值加1
				Boolean once = true;
				if(once){
					Main.i = Main.i + 1;
					dialogButton.setText(Fold.loadScript(StartGame.this, String.valueOf(Main.i)));					
					once = false;
					}
				if(Main.i == 5){
					gameImage.setImageResource(R.drawable.game2);
					}else if(Main.i == 62){
						gameImage.setImageResource(R.drawable.game3);
					}else if(Main.i == 102){
						gameImage.setImageResource(R.drawable.game4);
					}else if(Main.i == 144){
						gameImage.setImageResource(R.drawable.game5);
					}else if(Main.i == 166){
						gameImage.setImageResource(R.drawable.game6);
					}else if(Main.i == 221 || Main.i == 279){
						gameImage.setImageResource(R.drawable.game7);
					}else if(Main.i == 247 || Main.i == 362){
						gameImage.setImageResource(R.drawable.game8);
					}else if(Main.i == 264){
						gameImage.setImageResource(R.drawable.game9);
					}else if(Main.i == 297){
						gameImage.setImageResource(R.drawable.game10);
					}else if(Main.i == 331){
						gameImage.setImageResource(R.drawable.game11);
					}else if(Main.i == 377){
						gameImage.setImageResource(R.drawable.game12);
					}else if(Main.i == 397){
						gameImage.setImageResource(R.drawable.game13);
					}else if(Main.i == 411){
						dialogButton.setVisibility(View.GONE);
					}
				}	
			});
        
  
        }
	

	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
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
