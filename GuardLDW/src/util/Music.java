package util;



import com.hero.app.R;

import android.content.Context;
import android.media.MediaPlayer;


/*
 * �ṩ�������ֵĿ�����رշ���
 */

public class Music {
	
	private static MediaPlayer mediaPlayer = null;
	
	
	public static void startBackgroundMusic(Context context){
        if(mediaPlayer == null){
        	mediaPlayer = MediaPlayer.create(context, R.raw.xiyan);
    		mediaPlayer.setLooping(true);//����Ϊѭ������
    		mediaPlayer.start();//��������
        }

	}
	
	public  static void stopBackgroundMusic(){
		if(mediaPlayer != null){
			mediaPlayer.stop();//�ر�����
			mediaPlayer = null;
		}
		
	}

}