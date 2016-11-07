package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class HeroOpenHelper extends SQLiteOpenHelper{ 
	
    //User表
	public static String CREATE_USER = "create table User(user_id integer primary key autoincrement, user_username text, user_password text, user_comment text)"; 
	
	private Context mContext;
	
	public HeroOpenHelper(Context context, String name, CursorFactory factory,int version) {
		
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);//建用户表
		Toast.makeText(mContext, "create success", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
