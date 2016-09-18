package activity;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import util.ActivityControl;
import util.Fold;
import util.Music;

public class Set extends Activity{
	
	private Button backButton;
	private RadioButton haveMusicRadioButton;
	private RadioButton noMusicRadioButton;
	private RadioGroup musicRadioGroup;
	

	
	protected void onCreate(Bundle savedInstanceState) {
    	
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.set);
        
        //定义返回键
        backButton = (Button)findViewById(R.id.button_setback);
        backButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Set.this, Main.class);
				startActivity(intent);
				
			}
        	
        });
        
        haveMusicRadioButton = (RadioButton)findViewById(R.id.radiobutton_havemusic);
        noMusicRadioButton = (RadioButton)findViewById(R.id.radiobutton_nomusic);
        musicRadioGroup = (RadioGroup)findViewById(R.id.radiogroup_backgroundmusic);
        
        
        musicRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()//设置音乐单选框的点击事件
        { 
            @Override 
            public void onCheckedChanged(RadioGroup group, int checkedId) //再开启时可记录上次的选项，在GameStart中设置音乐
            { 
                // TODO Auto-generated method stub 
                if(checkedId == haveMusicRadioButton.getId())
                { 
                	Fold.save("music", "1", Set.this);//改变music文件中的值，将初始值0覆盖
                	Music.startBackgroundMusic(Set.this);
                }      
                else if(checkedId == noMusicRadioButton.getId())
                {           
                	Fold.save("music", "0", Set.this);
                	Music.stopBackgroundMusic();
                } 
            }
        
        });
        
        
	}

	
	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(Set.this);
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