package activity;

import com.hero.app.R;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import util.ActivityControl;

public class Speical extends Activity{
	
	
	private Button specialButton;
	private TextView contentTextView;
	
	protected void onCreate(Bundle savedInstanceState) {
    	
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.special);
        
        specialButton = (Button)findViewById(R.id.button_specialcontent);
        contentTextView = (TextView)findViewById(R.id.textview_special);
        
        specialButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				contentTextView.setText("其实并没有什么特别篇...[欠揍脸]");				
			}
        	
        });
	}

}
