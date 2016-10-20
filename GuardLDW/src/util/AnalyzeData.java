package util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.User;

public class AnalyzeData {
	
	
	//对返回的json数据进行解析,返回User对象的链表,在向服务器发送请求的参数HttpCallBackListenenr的onfinish方法中调用
	public static List<User> handleUserResponese(String response){
		List <User> userList = new ArrayList<User>();//存储解析的User对象
		User user = new User();
		try {
			JSONArray jsonArray = new JSONArray(response);//建立JSON数组
			//遍历json数组，得到每个元素数据
			for(int i = 0; i<jsonArray.length(); i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				user.setUsername(jsonObject.getString("username"));
				user.setPassword(jsonObject.getString("password"));
				user.setComment(jsonObject.getString("comment"));				
				userList.add(user);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	
	//返回登录或注册结果
	public static int handleResultResponse(String response){
		
		
		
		return 0;
		
	}

}