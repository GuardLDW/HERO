package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.hero.app.R;

import activity.LoadGame;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


/*
 * 文件中数据的保存，读取与建立
 */



public class Fold {

	//建立初始记录数据的文件,只需要运行一次
    public static void build(Context context)
    {
   	 File file_gaonai = new File("/data/data/com.hero.app/files/gaonai");//存放高乃线
   	 File file_shixin = new File("/data/data/com.hero.app/files/shixin");//存放诗馨线
   	 File file_newpage = new File("/data/data/com.hero.app/files/newpage");//存放新篇章
   	 
   	 File file_music = new File("/data/data/com.hero.app/files/music");//存放背景音乐
   	 
   	 File file_userInfo = new File("/data/data/com.hero.app/file/userInfo");//存放模拟服务器的用户数据
   	 
   	 File file_save0 = new File("/data/data/com.hero.app/files/save0");//存放存档1
   	 File file_save1 = new File("/data/data/com.hero.app/files/save1");//存放存档2
   	 File file_save2 = new File("/data/data/com.hero.app/files/save2");//存放存档3
   	 File file_save3 = new File("/data/data/com.hero.app/files/save3");//存放存档4
   	 File file_save4 = new File("/data/data/com.hero.app/files/save4");//存放存档5
   	 File file_save5 = new File("/data/data/com.hero.app/files/save5");//存放存档6

   	
   	 File file_savei0 = new File("/data/data/com.hero.app/files/savei0");//存放存档i0的值
   	 File file_savei1 = new File("/data/data/com.hero.app/files/savei1");//存放存档i1的值
   	 File file_savei2 = new File("/data/data/com.hero.app/files/savei2");//存放存档i2的值
   	 File file_savei3 = new File("/data/data/com.hero.app/files/savei3");//存放存档i3的值
   	 File file_savei4 = new File("/data/data/com.hero.app/files/savei4");//存放存档i4的值
   	 File file_savei5 = new File("/data/data/com.hero.app/files/savei5");//存放存档i5的值

   	 
   	 if(!file_gaonai.exists())//运行一次后文件存在则不再运行
   	 {
   		 save("gaonai", "0", context);//建立初始文件，初值为0
   	 }
   	 
   	 if(!file_shixin.exists())
   	 {
   		 save("shixin", "0", context);
   	 }
   	 
   	 if(!file_newpage.exists())
   	 {
   		 save("newpage", "0", context);
   	 }
   	 
   	 if(!file_music.exists())
   	 {
   		 save("music", "0", context);
   	 }
   	 
   	 if(!file_save0.exists())
   	 {
   		 save("save0", "0", context);
   	 }
   	 
   	 if(!file_save1.exists())
   	 {
   		 save("save1", "0", context);
   	 }
   	 
   	 if(!file_save2.exists())
   	 {
   		 save("save2", "0", context);
   	 }
   	 
   	 if(!file_save3.exists())
   	 {
   		 save("save3", "0", context);
   	 }
   	
   	 if(!file_save4.exists())
   	 {
   		 save("save4", "0", context);
   	 }
   	 
   	 if(!file_save5.exists())
   	 {
   		 save("save5", "0", context);
   	 }
   	 

   	 if(!file_savei0.exists())
   	 {
   		 save("savei0", "0", context);
   	 }
   	 
   	 if(!file_savei1.exists())
   	 {
   		 save("savei1", "0", context);
   	 }
   	 
   	 if(!file_savei2.exists())
   	 {
   		 save("savei2", "0", context);
   	 }
   	 
   	 if(!file_savei3.exists())
   	 {
   		 save("savei3", "0", context);
   	 }
   	
   	 if(!file_savei4.exists())
   	 {
   		 save("savei4", "0", context);
   	 }
   	 
   	 if(!file_savei5.exists())
   	 {
   		 save("savei5", "0", context);
   	 }
   	 
   	 if(!file_userInfo.exists())
   	 {
   		 save("userInfo", "", context);
   	 }
   	 

    }
	
	
	
	//保存到文件    
    public static void save(String site, String text, Context context){
   	FileOutputStream out = null;//创建文件输出流对象
   	BufferedWriter writer = null;//创建缓冲写
   	try {
   		out = context.openFileOutput(site, Context.MODE_PRIVATE);//通过openFileOutput方法得到文件输出流对象
	    writer = new BufferedWriter(new OutputStreamWriter(out));
		writer.write(text);//将传入的参数text写入文件
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			}finally{  
           try {  
              if(writer != null){
               	writer.close();
               }
           } catch (Exception e) {  
               e.printStackTrace();  
           }  
       }     	
    }
    
    
    //从文件中读取
    public static String load(String site, Context context)
    {
   	 FileInputStream in = null;//创建文件输入流对象
   	 BufferedReader reader = null;//创建缓存读
   	 StringBuilder content = new StringBuilder();//创建可变字符串对象content
   	 try 
   	 {
			in = context.openFileInput(site);
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null){
				content.append(line);
			}			
		 } 
   	 catch (FileNotFoundException e) 
   	 {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{  
			if (reader != null){
           try {  
                	reader.close();
                }catch (Exception e) {  
                e.printStackTrace();  
            }  
			}
		}         
		return content.toString();   	 
    }

    
    public static String loadScript(Context context, String s){
    	SharedPreferences pref = context.getSharedPreferences("script", Context.MODE_PRIVATE); 
		return pref.getString(s, "脚本加载出现错误");
    	
    }
    
    
    //将存档日期显示出来，save0-5文件中保存的是存档1-6的时间，不为0说明有存档记录
    public static void checkSave(Context context)
    {
  	 
      	if(!Fold.load("save0", context).equals("0")){
      		LoadGame.save[0] = Fold.load("save0", context);
      		}
      	if(!Fold.load("save1", context).equals("0"))
   	 {
   		 LoadGame.save[1] = Fold.load("save1", context);
   	 }
      	if(!Fold.load("save2", context).equals("0"))
   	 {
   		 LoadGame.save[2] = Fold.load("save2", context);
   	 }
      	if(!Fold.load("save3", context).equals("0"))
   	 {
   		 LoadGame.save[3] = Fold.load("save3", context);
   	 }
      	if(!Fold.load("save4", context).equals("0"))
   	 {
   		 LoadGame.save[4] = Fold.load("save4", context);
   	 }
      	if(!Fold.load("save5", context).equals("0"))
   	 {
   		 LoadGame.save[5] = Fold.load("save5", context);
   	 }

    
    }
    
    
    

    //存储脚本，只需要运行一次
    public static void saveScript(Context context){
    	File file = new File("/data/data/com.hero.app/shared_prefs/script");
    	//保证只运行一次
    	if (!file.exists()){
    	   	SharedPreferences.Editor editor = context.getSharedPreferences("script", Context.MODE_PRIVATE).edit();
        	editor.putString("0", "柔和的阳光下，男孩在草地上尽情的奔跑着。");
        	editor.putString("1", "爸爸，妈妈，这是什么呀？");
        	editor.putString("2", "等等，这是蜜蜂，不能碰。");
        	editor.putString("3", "然而男孩早已把手伸了过去，紧接着伴随着一句叫喊。");
        	editor.putString("4", "好疼！");
        	editor.putString("5", "刚才是梦吗？");
        	editor.putString("6", "额头上冒着汗珠，身体感觉很沉。");
        	editor.putString("7", "坐在床边，我揉了揉眼睛，可劳累感还是丝毫没有减弱。");
        	editor.putString("8", "虽然记不起睡前发生的事，但心中却有一股莫名的不安。");
        	editor.putString("9", "我究竟睡了多长时间，之前的事情我怎么一点都想不起来？");
        	editor.putString("10", "我勉强站了起来，走向了客厅");
        	editor.putString("11", "有什么线索呢？");
        	editor.putString("12", "绕着客厅走了一圈，依旧没有回忆起之前的事。");
        	editor.putString("13", "躺在沙发上，我打开了电视。");
        	editor.putString("14", "\"观众朋友们晚上好，今天是2018年11月14日，农历10月初7，欢迎收看《新闻联播》节目，本次节目的主要内容有......\"");
        	editor.putString("15", "新闻联播，有些印象。");
        	editor.putString("16", "现在已经是2018年了吗？");
        	editor.putString("17", "依稀觉得时间有些违和。");
        	editor.putString("18", "回到我的小屋，我寻找着可能记录着时间的东西。");
        	editor.putString("19", "书桌上有一本书，看起来像是日记的样子。");
        	editor.putString("20", "\"2008年8月8日，天气：晴          今天有北京奥运会的开幕式，开幕式上的表演太精彩了。中国队是最后入场的，运动员们都好厉害！我长大也要成为运动员！\"");
        	editor.putString("21", "好幼稚，但我却有种似曾相识的感觉。");
        	editor.putString("22", "我接着翻到了后面的一页。");
        	editor.putString("23", "\"2008年8月9日，天气：晴          今天是奥运会比赛的第一天，中国得了两块金牌，运动运们太棒了。\"");
        	editor.putString("24", "诶？");
        	editor.putString("25", "再往后翻直接到了最后一页，中间的纸不知道被谁撕掉了。");
        	editor.putString("26", "\"2008年11月14日，天气：多云          今天去郊游了，虽然手有点痛，但是好开心，晚上我要睡个好觉。\"");
        	editor.putString("27", "我刚才好像做了差不多的梦？");
        	editor.putString("28", "依旧没有思路，脑袋也变得有些混乱。");
        	editor.putString("29", "空气中弥漫着一丝燥热。");
        	editor.putString("30", "我穿上了大衣，准备去外面走走，说不定能想起些什么。");
        	editor.putString("31", "这是...?");
        	editor.putString("32", "在刚要出门的时候，我发现大门内侧贴着一张纸，纸显得有些残破，上面写满了字。");
        	editor.putString("33", "\"亲爱的高介：看到这段话的时候证明你已经醒了吧！我能够想象你此时的疑惑，但是不要着急。\"");
        	editor.putString("34", "\"不知道你对之前的记忆还留存多少。一年前，也就是2008年11月14日的晚上。\"");
        	editor.putString("35", "这不是我日记上记录的那天吗？");
        	editor.putString("36", "我接着看了下去。");
        	editor.putString("37", "\"那天我们郊游回来后，我问你想不想成为英雄。你还记得你当时的回答是什么吗？\"");
        	editor.putString("38", "\"你当时非常兴奋地答应了，还问我怎么能成为英雄。我兴奋的同时又伴有一丝悲伤，但对于你来说，这是不必可少的一段经历。\"");
        	editor.putString("39", "\"所谓英雄，是要拯救世界的。当我告诉你只要睡一觉醒后就能成为英雄时，你毫不犹豫的就上床准备去睡觉了。\"");
        	editor.putString("40", "\"我正在写些文字时，你还在床上躺着。这一觉，也不知你会睡几年。\"");
        	editor.putString("41", "妈妈在说些什么？我怎么有些听不懂呢。");
        	editor.putString("42", "\"你一定很疑惑为什么一觉会睡这么长时间，长时间的沉睡是成为英雄的必备条件，可能你还没有发现自己有什么本质的改变，但随着时间的推移，你会慢慢察觉到发生在你身上的变化。\"");
        	editor.putString("43", "\"我和你爸爸马上就要去外国办些事情了，等我们事情办完就回来。这段时间你要好好生活，一定要等到我们回来的那天。\"");
        	editor.putString("44", "\"更多的真相就由你自己去探索吧。时刻要记住，不要放弃希望，要坚强的活下去。\"");
        	editor.putString("45", "\"爱你的妈妈，2009年11月14日。\"");
        	editor.putString("46", "纸上的字到这里就结束了，但我心中的疑惑却没有消除");
        	editor.putString("47", "这种小说一样的情节是怎么回事？(官方吐槽哈哈)");
        	editor.putString("48", "估计是恶作剧吧，即使这样，我还是有些在意。");
        	editor.putString("49", "我又回到了自己的房间。");
        	editor.putString("50", "再次看着我日记本记载着郊游那天的记录。");
        	editor.putString("51", "是有人看了我的日记后写的恶作剧吧，真是过分。");
        	editor.putString("52", "刚要合上日记本的时候，我注意到这页底下似乎写着字。");
        	editor.putString("53", "\"晚安\"");
        	editor.putString("54", "这不是刚才门上贴着的纸的字迹吗？");
        	editor.putString("55", "恶作剧会做的这么细心？");
        	editor.putString("56", "我有些迷茫。");
        	editor.putString("57", "难道说，这张纸真的是妈妈留给我的？");
        	editor.putString("58", "不愿意相信是真的，不愿去思考如果是真的自己该怎么办。");
        	editor.putString("59", "但我迟迟记不起的之前的事让我感觉这像是真的。");
        	editor.putString("59", "这一觉，我睡了10年吗?");
        	editor.putString("60", "先去外面看看吧，呆在这里也没有用。");
        	editor.putString("61", "这么想着，我再次穿好外衣，准备走出家门。 ");
        	editor.putString("62", "开门的一刹那，外边一片明亮，仿佛打开了一扇通往新世界的大门。");
        	editor.commit();
    	}
 
    	
    	
    	
    }

    


}
