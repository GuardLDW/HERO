package activity;


import com.hero.app.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import util.BaseActivity;
import util.Fold;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class LoadGameActivity extends BaseActivity{
	
	//��һ��Ӧ��ֻ����һ��
    public static String[] save = {"�浵1          XXXX.XX.XX.XX.XX.XX","�浵2          XXXX.XX.XX.XX.XX.XX","�浵3          XXXX.XX.XX.XX.XX.XX",
                                   "�浵4          XXXX.XX.XX.XX.XX.XX","�浵5          XXXX.XX.XX.XX.XX.XX","�浵6          XXXX.XX.XX.XX.XX.XX",
                                  };
    
    
	private ListView loadList;
	private Button backButton;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		

		super.onCreate(savedInstanceState);
        setContentView(R.layout.loadgame);
   	
       
        backButton = (Button)findViewById(R.id.button_loadgameback);
        backButton.setOnClickListener(new Listener());
 
        //��loadList����������ǰ����save�����ֵ
        Fold.checkSave(LoadGameActivity.this);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoadGameActivity.this, android.R.layout.simple_list_item_1, save);
        loadList = (ListView)findViewById(R.id.listview_loadgame);
        loadList.setAdapter(adapter);
        
        //����listviewset�ļ���
        loadList.setOnItemLongClickListener(new LongListener());

        //ListView�ĵ��������Ӧ�����������
        loadList.setOnItemClickListener(new ItemListener());
        
	}

	
	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			Intent intent = new Intent(LoadGameActivity.this, MainActivity.class);
			startActivity(intent);
			
		}

	}
	
	
	private class LongListener implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			
			MainActivity.i = Integer.parseInt(Fold.load("savei" + position, LoadGameActivity.this));
			if(MainActivity.i >= 0 && MainActivity.i <= 410){
				Toast.makeText(LoadGameActivity.this, "��ǰ�浵λ�ڹ�ͨ��", Toast.LENGTH_SHORT).show();
			}else if(MainActivity.i >= 510 && MainActivity.i <= 551){
				Toast.makeText(LoadGameActivity.this, "��ǰ�浵λ�ڸ�����",Toast.LENGTH_SHORT).show();
			}else if(MainActivity.i >= 1510 && MainActivity.i <= 1532){
				Toast.makeText(LoadGameActivity.this, "��ǰ�浵λ��ʫܰ��",Toast.LENGTH_SHORT).show();
			}else if(MainActivity.i == 411){
				Toast.makeText(LoadGameActivity.this, "��ǰ�浵λ�ڵ�һ��ѡ�",Toast.LENGTH_SHORT).show();
			}
			
	        //�������false��ôclick��Ȼ�ᱻ���á��������ȵ���Long click��Ȼ�����click�� 
	        //�������true��ôclick�ͻᱻ�Ե���click�Ͳ����ٱ�������
			return true;
		}
		
		
	}
	
	
	private class ItemListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
				MainActivity.i = Integer.parseInt(Fold.load("savei" + position, LoadGameActivity.this));//���ַ���ʽ��MainActivity.iת��Ϊ���β���ȡ
				Intent intent = new Intent(LoadGameActivity.this, StartGameActivity.class);
				startActivity(intent);//��ʼ������Ϸ��ͨ��������Ϸ�Ĳ��֣�

		}
		
		
	}
	

	
 
}