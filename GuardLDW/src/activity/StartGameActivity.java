package activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.hero.app.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import util.BaseActivity;
import util.Fold;
import android.widget.AdapterView.OnItemClickListener;

public class StartGameActivity extends BaseActivity{

	private ImageView gameImage;
	private Button dialogButton;
	private Button gamestartsetButton;
	private boolean showSet;
	private Button saveButton;
	private Button backButton;
	private Button fastButton;
	private Button clearButton;
	private Button stayButton;
	private Button doButton;
	
	protected void onCreate(Bundle savedInstanceState) {	
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
              
        gameImage = (ImageView)findViewById(R.id.image_game);
        
        dialogButton = (Button)findViewById(R.id.button_dialog);
        dialogButton.setOnClickListener(new Listener());
        
        gamestartsetButton = (Button)findViewById(R.id.button_startgameset);
        gamestartsetButton.setOnClickListener(new Listener());
        
        saveButton = (Button)findViewById(R.id.button_save);
        saveButton.setOnClickListener(new Listener());
        
        backButton = (Button)findViewById(R.id.button_backtotitle);
        backButton.setOnClickListener(new Listener());
        
        fastButton = (Button)findViewById(R.id.button_fast);
        fastButton.setOnClickListener(new Listener());
        
        clearButton = (Button)findViewById(R.id.button_remove);
        clearButton.setOnClickListener(new Listener());
        
        stayButton = (Button)findViewById(R.id.button_staychoice);
        stayButton.setOnClickListener(new Listener());
        
        doButton = (Button)findViewById(R.id.button_dochoice);
        doButton.setOnClickListener(new Listener());     
        
        
        //初始状态下点击(颜文字)按钮，显示4个按钮
        showSet = true;
        gamestartsetButton.setText("(｀･ω･´)");

        //设置隐藏button
        stayButton.setVisibility(View.GONE);
        doButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        fastButton.setVisibility(View.GONE);
        clearButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);

        
        //进入该活动时，首先根据i值加载对话内容与背景图片
        dialogButton.setText(Fold.loadScript(StartGameActivity.this, String.valueOf(MainActivity.i)));
        if(MainActivity.i >= 0 && MainActivity.i < 5){
        	
            gameImage.setImageResource(R.drawable.game1);
        }else if(MainActivity.i >= 5 && MainActivity.i < 62){
        	
        	gameImage.setImageResource(R.drawable.game2);
        }else if(MainActivity.i >= 62 && MainActivity.i < 102){
        	
        	gameImage.setImageResource(R.drawable.game3);
        }else if(MainActivity.i >= 102 && MainActivity.i < 144){
        	
        	gameImage.setImageResource(R.drawable.game4);
        }else if(MainActivity.i >= 144 && MainActivity.i < 166){
        	
        	gameImage.setImageResource(R.drawable.game5);
        }else if(MainActivity.i >= 166 && MainActivity.i < 221){
        	
        	gameImage.setImageResource(R.drawable.game6);
        }else if(MainActivity.i >= 221 && MainActivity.i < 247){
        	
        	gameImage.setImageResource(R.drawable.game7);
        }else if(MainActivity.i >= 247 && MainActivity.i < 264){
        	
        	gameImage.setImageResource(R.drawable.game8);
        }else if(MainActivity.i >= 264 && MainActivity.i < 279){
        	
        	gameImage.setImageResource(R.drawable.game9);
        }else if(MainActivity.i >= 279 && MainActivity.i < 297){
        	
        	gameImage.setImageResource(R.drawable.game7);
        }else if(MainActivity.i >= 297 && MainActivity.i < 331){
        	
        	gameImage.setImageResource(R.drawable.game10);
        }else if(MainActivity.i >= 331 && MainActivity.i < 362){
        	
        	gameImage.setImageResource(R.drawable.game11);
        }else if(MainActivity.i >= 362 && MainActivity.i < 377){
        	
        	gameImage.setImageResource(R.drawable.game8);
        }else if(MainActivity.i >= 377 && MainActivity.i < 397){
        	
        	gameImage.setImageResource(R.drawable.game12);
        }else if((MainActivity.i >= 397 && MainActivity.i < 521) || (MainActivity.i >= 1510 && MainActivity.i < 1524)){
        	
        	gameImage.setImageResource(R.drawable.game13);
        	if(MainActivity.i == 411 ){
        		
        		dialogButton.setVisibility(View.INVISIBLE);
        		stayButton.setVisibility(View.VISIBLE);
        		doButton.setVisibility(View.VISIBLE);
        	}
        }else if(MainActivity.i >= 521 && MainActivity.i < 533){
        	
        	gameImage.setImageResource(R.drawable.game14);
        }else if(MainActivity.i >= 533 && MainActivity.i < 551){
        	
        	gameImage.setImageResource(R.drawable.game15);
        }else if(MainActivity.i >= 1524 && MainActivity.i < 1532){
        	
        	gameImage.setImageResource(R.drawable.game16);
        }
        
	}
        
        

	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_backtotitle){
				
				Intent intent = new Intent(StartGameActivity.this, MainActivity.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_fast){
				
				MainActivity.i = 411;
				Intent intent = new Intent(StartGameActivity.this, StartGameActivity.class);
				startActivity(intent);	
				
			}else if(v.getId() == R.id.button_remove){
				
				dialogButton.setBackgroundColor(android.graphics.Color.parseColor("#00FFFFFF"));
				dialogButton.setText("");
				
			}else if(v.getId() == R.id.button_startgameset){
				
				if(showSet){

					saveButton.setVisibility(View.VISIBLE);
					backButton.setVisibility(View.VISIBLE);
					fastButton.setVisibility(View.VISIBLE);
					clearButton.setVisibility(View.VISIBLE);
			        gamestartsetButton.setText("(´･ω･｀)");
					showSet = false;
					
				}else{
					
					saveButton.setVisibility(View.GONE);
					backButton.setVisibility(View.GONE);
					fastButton.setVisibility(View.GONE);
					clearButton.setVisibility(View.GONE);
			        gamestartsetButton.setText("(｀･ω･´)");
					showSet = true;
				}
				
			}else if(v.getId() == R.id.button_staychoice){
				
				MainActivity.i = MainActivity.i + 100;
				dialogButton.setText(Fold.loadScript(StartGameActivity.this, String.valueOf(MainActivity.i)));
				dialogButton.setVisibility(View.VISIBLE);
				doButton.setVisibility(View.GONE);
				stayButton.setVisibility(View.GONE);
				
			}else if(v.getId() == R.id.button_dochoice){
				
				MainActivity.i = MainActivity.i + 1100;				
				dialogButton.setText(Fold.loadScript(StartGameActivity.this, String.valueOf(MainActivity.i)));
				dialogButton.setVisibility(View.VISIBLE);
				doButton.setVisibility(View.GONE);
				stayButton.setVisibility(View.GONE);
				
			}else if(v.getId() == R.id.button_save){
				
			    Log.d("", "savebutton");
				
				setContentView(R.layout.loadgame);
				
				//在loadList设置适配器前更改save数组的值
		        Fold.checkSave(StartGameActivity.this);			
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(StartGameActivity.this, android.R.layout.simple_list_item_1, LoadGameActivity.save);//适配器中储存着存档格式
		       	final ListView loadList = (ListView)findViewById(R.id.listview_loadgame);//列表组件
		       	loadList.setAdapter(adapter);//使用适配器，加载ListView的值
		       	loadList.setOnItemClickListener(new OnItemClickListener(){//定义了ListView的点击子项响应方法

		    	@Override
		    	public void onItemClick(AdapterView<?> parent, View view, final int position,long id) {	 
		    		long l = System.currentTimeMillis();
		    		Date date = new Date(l);
		    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		final String str = dateFormat.format(date);
		    		
		    		AlertDialog.Builder dialog = new AlertDialog.Builder(StartGameActivity.this);
		    		dialog.setMessage("进度保存在存档" + (position + 1) + "，确认？");
		    		dialog.setCancelable(false);
		    		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener(){
		    			@Override
		    			public void onClick(DialogInterface dialog, int which){
		    					//将当前i值与存档时间保存到相应文件
		    					Fold.save("save" + position, "存档" + (position + 1) + "          " + str, StartGameActivity.this);
		    					Fold.save("savei" + position, String.valueOf(MainActivity.i), StartGameActivity.this);
		    					//能够即时显示存档时间
		    					LoadGameActivity.save[position] = "存档" + (position + 1) + "          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据 
		    				}
		    			});  
		    		dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
		    		   @Override
		    		   public void onClick(DialogInterface dialog, int which) {		   		    			   
		    		        }
		    		   });  
		    	   dialog.show();
		    	   }  			
		    	});
		       	
		        //定义菜单保存存档界面的返回键
		       	Button backtogame = (Button)findViewById(R.id.button_loadgameback);
		       	backtogame.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {	
						
						Intent intent = new Intent(StartGameActivity.this, StartGameActivity.class);
					    startActivity(intent);
					}    		
		       	});	
		       	
			}else if(v.getId() == R.id.button_dialog){
				
				dialogButton.setBackgroundColor(android.graphics.Color.parseColor("#afDA70D6"));
				//点击一次按钮i值加1
				Boolean once = true;
				if(once){
					MainActivity.i = MainActivity.i + 1;
					dialogButton.setText(Fold.loadScript(StartGameActivity.this, String.valueOf(MainActivity.i)));					
					once = false;
					}
				if(MainActivity.i == 5){
					gameImage.setImageResource(R.drawable.game2);
					}else if(MainActivity.i == 62){
						gameImage.setImageResource(R.drawable.game3);
					}else if(MainActivity.i == 102){
						gameImage.setImageResource(R.drawable.game4);
					}else if(MainActivity.i == 144){
						gameImage.setImageResource(R.drawable.game5);
					}else if(MainActivity.i == 166){
						gameImage.setImageResource(R.drawable.game6);
					}else if(MainActivity.i == 221 || MainActivity.i == 279){
						gameImage.setImageResource(R.drawable.game7);
					}else if(MainActivity.i == 247 || MainActivity.i == 362){
						gameImage.setImageResource(R.drawable.game8);
					}else if(MainActivity.i == 264){
						gameImage.setImageResource(R.drawable.game9);
					}else if(MainActivity.i == 297){
						gameImage.setImageResource(R.drawable.game10);
					}else if(MainActivity.i == 331){
						gameImage.setImageResource(R.drawable.game11);
					}else if(MainActivity.i == 377){
						gameImage.setImageResource(R.drawable.game12);
					}else if(MainActivity.i == 397){
						gameImage.setImageResource(R.drawable.game13);
					}else if(MainActivity.i == 411){
						//隐藏对话框并显示选项
						dialogButton.setVisibility(View.GONE);
						stayButton.setVisibility(View.VISIBLE);
						doButton.setVisibility(View.VISIBLE);
					}else if(MainActivity.i == 521){
						gameImage.setImageResource(R.drawable.game14);
					}else if(MainActivity.i == 533){
						gameImage.setImageResource(R.drawable.game15);
						
						//高乃线通关
					}else if(MainActivity.i == 551){
						Fold.save("gaonai", "1", StartGameActivity.this);
						Log.d("what", Fold.load("gaonai", StartGameActivity.this));
						MainActivity.i --;
					}else if(MainActivity.i == 1524){
						gameImage.setImageResource(R.drawable.game16);
						
						//诗馨线通关
					}else if(MainActivity.i == 1532){
						Fold.save("shixin", "1", StartGameActivity.this);
						Log.d("what", Fold.load("shixin", StartGameActivity.this));
						MainActivity.i --;
					}
				}	
	
		}
	
		
	}

}
