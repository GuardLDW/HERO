package util;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityControl {
	

	public static List<Activity>  activities = new ArrayList<Activity>();//建立链表activities，类型为Activity类
	
	public static void addActivity(Activity activity)//把运行的活动添加到链表中，在每个活动的OnCreate方法中都要调用
    {
		activities.add(activity);
	}
	
	public static void removeActivity(Activity activity)//把单个活动从链表中移除
	{
		activities.remove(activity);
	}
	
	public static void finishAll()//关闭所有活动
	{
		for(Activity activity : activities)//遍历activities链表，并把数据记录到activity中
		{
			if(!activity.isFinishing())
			{
				activity.finish();
			}
		}
	}

}
