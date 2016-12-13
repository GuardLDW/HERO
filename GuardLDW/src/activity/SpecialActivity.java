package activity;

import com.hero.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import util.BaseActivity;

public class SpecialActivity extends BaseActivity{
		
	private Button specialButton;
	private TextView contentTextView;
	private Button backButton;
	
	
	protected void onCreate(Bundle savedInstanceState) {
    	
		super.onCreate(savedInstanceState);
        setContentView(R.layout.special);
        
        backButton = (Button)findViewById(R.id.button_specialback);
        backButton.setOnClickListener(new Listener());
        
        specialButton = (Button)findViewById(R.id.button_specialcontent);
        specialButton.setOnClickListener(new Listener());
             
        contentTextView = (TextView)findViewById(R.id.textview_special);
        
	}
	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_specialcontent){
				
				contentTextView.setText("其实并没有什么特别篇...[欠揍脸]");	
				specialButton.setText("");	
				
			}else if(v.getId() == R.id.button_specialback){
				
				Intent intent = new Intent(SpecialActivity.this, MainActivity.class);
				startActivity(intent);
			}
			

		}

	}

}
