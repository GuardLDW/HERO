package db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * 封装常用数据库的操作
 */


public class HeroDB {

	public int VERSION = 1;//数据库的版本
	
	private static SQLiteDatabase db;
	
	private static HeroDB heroDB;
	
	
	//构造方法私有化
	private HeroDB(Context context, String DB_name){
		HeroOpenHelper dbHelper = new HeroOpenHelper(context, DB_name, null, VERSION);
		db = dbHelper.getWritableDatabase();//getWritableDatabase方法：如果创建相同的数据库则直接打开，不再执行XXXXOpenHelpler的OnCreate方法
	}	
	
	//获取HeroDB实例
	public synchronized static HeroDB getInstance(Context context, String DB_name){
		if(heroDB != null){
			heroDB = new HeroDB(context, DB_name);//调用构造方法，创建数据库文件
		}
		return heroDB;
	}
	
	//将User实例存储到数据库
	public static void saveUser(User user){
		if(user != null){
			ContentValues values = new ContentValues();//使用ContentValues对要添加的数据进行组装
			values.put("user_username", user.getUsername());
			values.put("user_password", user.getPassword());
			values.put("user_comment", user.getComment());
			db.insert("User", null, values);//将组装完的数据vales插入到User表中
		}
	}
	
	//从数据库中读取数据，返回User类型的链表
	public static List <User> loadUser(){
			List <User> list = new ArrayList<User>(); 
			Cursor cursor = db.query("User", null, null, null, null, null, null);
			if(cursor.moveToFirst()){//将指针移动到第一行
				do{
					User user = new User();
					
					user.setUsername(cursor.getString(cursor.getColumnIndex("user_username")));//getColumnIndex用来读取对应属性的值
					user.setPassword(cursor.getString(cursor.getColumnIndex("user_password")));
					user.setComment(cursor.getString(cursor.getColumnIndex("user_comment")));
	                list.add(user);
				}while(cursor.moveToNext());
			}
			//将数据添加到List后，释放cursor空间
			if(cursor != null){
				cursor.close();
			}
			return list;			
	}
	
	//更新数据库中的用户评论
	public static void updateUserComment(String comment, String username,String DB_name){
		ContentValues values = new ContentValues();
		values.put("user_comment", comment);
		db.update(DB_name, values, "user_name = ?", new String[]{comment});
	}

	
}
