package util;


import com.hero.app.R;
import android.content.Context;
import android.media.MediaPlayer;

/*
 * 提供背景音乐的开启与关闭方法
 */

public class Music {
	
	private static MediaPlayer mediaPlayer = null;
	
	
	public static void startBackgroundMusic(Context context){
		
        if(mediaPlayer == null){
        	
        	mediaPlayer = MediaPlayer.create(context, R.raw.xiyan);
        	
        	//设置为循环播放
    		mediaPlayer.setLooping(true);
    		
    		//开启音乐
    		mediaPlayer.start();
        }

	}
	
	
	public  static void stopBackgroundMusic(){
		if(mediaPlayer != null){
			
			//关闭音乐
			mediaPlayer.stop();
			mediaPlayer = null;
		}
		
	}

}