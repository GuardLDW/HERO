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
 * �ļ������ݵı��棬��ȡ�뽨��
 */



public class Fold {

	//������ʼ��¼���ݵ��ļ�,ֻ��Ҫ����һ��
    public static void build(Context context)
    {
   	 File file_gaonai = new File("/data/data/com.hero.app/files/gaonai");//��Ÿ�����
   	 File file_shixin = new File("/data/data/com.hero.app/files/shixin");//���ʫܰ��
   	 File file_newpage = new File("/data/data/com.hero.app/files/newpage");//�����ƪ��
   	 
   	 File file_music = new File("/data/data/com.hero.app/files/music");//��ű�������
   	 
   	 File file_userInfo = new File("/data/data/com.hero.app/file/userInfo");//���ģ����������û�����
   	 
   	 File file_save0 = new File("/data/data/com.hero.app/files/save0");//��Ŵ浵1
   	 File file_save1 = new File("/data/data/com.hero.app/files/save1");//��Ŵ浵2
   	 File file_save2 = new File("/data/data/com.hero.app/files/save2");//��Ŵ浵3
   	 File file_save3 = new File("/data/data/com.hero.app/files/save3");//��Ŵ浵4
   	 File file_save4 = new File("/data/data/com.hero.app/files/save4");//��Ŵ浵5
   	 File file_save5 = new File("/data/data/com.hero.app/files/save5");//��Ŵ浵6

   	
   	 File file_savei0 = new File("/data/data/com.hero.app/files/savei0");//��Ŵ浵i0��ֵ
   	 File file_savei1 = new File("/data/data/com.hero.app/files/savei1");//��Ŵ浵i1��ֵ
   	 File file_savei2 = new File("/data/data/com.hero.app/files/savei2");//��Ŵ浵i2��ֵ
   	 File file_savei3 = new File("/data/data/com.hero.app/files/savei3");//��Ŵ浵i3��ֵ
   	 File file_savei4 = new File("/data/data/com.hero.app/files/savei4");//��Ŵ浵i4��ֵ
   	 File file_savei5 = new File("/data/data/com.hero.app/files/savei5");//��Ŵ浵i5��ֵ

   	 
   	 if(!file_gaonai.exists())//����һ�κ��ļ�������������
   	 {
   		 save("gaonai", "0", context);//������ʼ�ļ�����ֵΪ0
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
	
	
	
	//���浽�ļ�    
    public static void save(String site, String text, Context context){
   	FileOutputStream out = null;//�����ļ����������
   	BufferedWriter writer = null;//��������д
   	try {
   		out = context.openFileOutput(site, Context.MODE_PRIVATE);//ͨ��openFileOutput�����õ��ļ����������
	    writer = new BufferedWriter(new OutputStreamWriter(out));
		writer.write(text);//������Ĳ���textд���ļ�
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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
    
    
    //���ļ��ж�ȡ
    public static String load(String site, Context context)
    {
   	 FileInputStream in = null;//�����ļ�����������
   	 BufferedReader reader = null;//���������
   	 StringBuilder content = new StringBuilder();//�����ɱ��ַ�������content
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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
		return pref.getString(s, "�ű����س��ִ���");
    	
    }
    
    
    //���浵������ʾ������save0-5�ļ��б�����Ǵ浵1-6��ʱ�䣬��Ϊ0˵���д浵��¼
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
    
    
    

    //�洢�ű���ֻ��Ҫ����һ��
    public static void saveScript(Context context){
    	File file = new File("/data/data/com.hero.app/shared_prefs/script");
    	//��ֻ֤����һ��
    	if (!file.exists()){
    	   	SharedPreferences.Editor editor = context.getSharedPreferences("script", Context.MODE_PRIVATE).edit();
        	editor.putString("0", "��͵������£��к��ڲݵ��Ͼ���ı����š�");
        	editor.putString("1", "�ְ֣����裬����ʲôѽ��");
        	editor.putString("2", "�ȵȣ������۷䣬��������");
        	editor.putString("3", "Ȼ���к����Ѱ������˹�ȥ�������Ű�����һ��к���");
        	editor.putString("4", "���ۣ�");
        	editor.putString("5", "�ղ�������");
        	editor.putString("6", "��ͷ��ð�ź��飬����о��ܳ���");
        	editor.putString("7", "���ڴ��ߣ����������۾��������۸л���˿��û�м�����");
        	editor.putString("8", "��Ȼ�ǲ���˯ǰ�������£�������ȴ��һ��Ī���Ĳ�����");
        	editor.putString("9", "�Ҿ���˯�˶೤ʱ�䣬֮ǰ����������ôһ�㶼�벻������");
        	editor.putString("10", "����ǿվ�������������˿���");
        	editor.putString("11", "��ʲô�����أ�");
        	editor.putString("12", "���ſ�������һȦ������û�л�����֮ǰ���¡�");
        	editor.putString("13", "����ɳ���ϣ��Ҵ��˵��ӡ�");
        	editor.putString("14", "\"�������������Ϻã�������2018��11��14�գ�ũ��10�³�7����ӭ�տ���������������Ŀ�����ν�Ŀ����Ҫ������......\"");
        	editor.putString("15", "������������Щӡ��");
        	editor.putString("16", "�����Ѿ���2018������");
        	editor.putString("17", "��ϡ����ʱ����ЩΥ�͡�");
        	editor.putString("18", "�ص��ҵ�С�ݣ���Ѱ���ſ��ܼ�¼��ʱ��Ķ�����");
        	editor.putString("19", "��������һ���飬�����������ռǵ����ӡ�");
        	editor.putString("20", "\"2008��8��8�գ���������          �����б������˻�Ŀ�Ļʽ����Ļʽ�ϵı���̫�����ˡ��й���������볡�ģ��˶�Ա�Ƕ����������ҳ���ҲҪ��Ϊ�˶�Ա��\"");
        	editor.putString("21", "�����ɣ�����ȴ����������ʶ�ĸо���");
        	editor.putString("22", "�ҽ��ŷ����˺����һҳ��");
        	editor.putString("23", "\"2008��8��9�գ���������          �����ǰ��˻�����ĵ�һ�죬�й�����������ƣ��˶�����̫���ˡ�\"");
        	editor.putString("24", "����");
        	editor.putString("25", "������ֱ�ӵ������һҳ���м��ֽ��֪����˭˺���ˡ�");
        	editor.putString("26", "\"2008��11��14�գ�����������          ����ȥ�����ˣ���Ȼ���е�ʹ�����Ǻÿ��ģ�������Ҫ˯���þ���\"");
        	editor.putString("27", "�Ҹղź������˲����Σ�");
        	editor.putString("28", "����û��˼·���Դ�Ҳ�����Щ���ҡ�");
        	editor.putString("29", "������������һ˿���ȡ�");
        	editor.putString("30", "�Ҵ����˴��£�׼��ȥ�������ߣ�˵����������Щʲô��");
        	editor.putString("31", "����...?");
        	editor.putString("32", "�ڸ�Ҫ���ŵ�ʱ���ҷ��ִ����ڲ�����һ��ֽ��ֽ�Ե���Щ���ƣ�����д�����֡�");
        	editor.putString("33", "\"�װ��ĸ߽飺������λ���ʱ��֤�����Ѿ����˰ɣ����ܹ��������ʱ���ɻ󣬵��ǲ�Ҫ�ż���\"");
        	editor.putString("34", "\"��֪�����֮ǰ�ļ��仹������١�һ��ǰ��Ҳ����2008��11��14�յ����ϡ�\"");
        	editor.putString("35", "�ⲻ�����ռ��ϼ�¼��������");
        	editor.putString("36", "�ҽ��ſ�����ȥ��");
        	editor.putString("37", "\"�������ǽ��λ������������벻���ΪӢ�ۡ��㻹�ǵ��㵱ʱ�Ļش���ʲô��\"");
        	editor.putString("38", "\"�㵱ʱ�ǳ��˷ܵش�Ӧ�ˣ���������ô�ܳ�ΪӢ�ۡ����˷ܵ�ͬʱ�ְ���һ˿���ˣ�����������˵�����ǲ��ؿ��ٵ�һ�ξ�����\"");
        	editor.putString("39", "\"��νӢ�ۣ���Ҫ��������ġ����Ҹ�����ֻҪ˯һ���Ѻ���ܳ�ΪӢ��ʱ���������ԥ�ľ��ϴ�׼��ȥ˯���ˡ�\"");
        	editor.putString("40", "\"������дЩ����ʱ���㻹�ڴ������š���һ����Ҳ��֪���˯���ꡣ\"");
        	editor.putString("41", "������˵Щʲô������ô��Щ�������ء�");
        	editor.putString("42", "\"��һ�����ɻ�Ϊʲôһ����˯��ô��ʱ�䣬��ʱ��ĳ�˯�ǳ�ΪӢ�۵ıر������������㻹û�з����Լ���ʲô���ʵĸı䣬������ʱ������ƣ������������������������ϵı仯��\"");
        	editor.putString("43", "\"�Һ���ְ����Ͼ�Ҫȥ�����Щ�����ˣ��������������ͻ��������ʱ����Ҫ�ú����һ��Ҫ�ȵ����ǻ��������졣\"");
        	editor.putString("44", "\"���������������Լ�ȥ̽���ɡ�ʱ��Ҫ��ס����Ҫ����ϣ����Ҫ��ǿ�Ļ���ȥ��\"");
        	editor.putString("45", "\"��������裬2009��11��14�ա�\"");
        	editor.putString("46", "ֽ�ϵ��ֵ�����ͽ����ˣ��������е��ɻ�ȴû������");
        	editor.putString("47", "����С˵һ�����������ô���£�(�ٷ��²۹���)");
        	editor.putString("48", "�����Ƕ�����ɣ���ʹ�������һ�����Щ���⡣");
        	editor.putString("49", "���ֻص����Լ��ķ��䡣");
        	editor.putString("50", "�ٴο������ռǱ������Ž�������ļ�¼��");
        	editor.putString("51", "�����˿����ҵ��ռǺ�д�Ķ�����ɣ����ǹ��֡�");
        	editor.putString("52", "��Ҫ�����ռǱ���ʱ����ע�⵽��ҳ�����ƺ�д���֡�");
        	editor.putString("53", "\"��\"");
        	editor.putString("54", "�ⲻ�Ǹղ��������ŵ�ֽ���ּ���");
        	editor.putString("55", "�������������ôϸ�ģ�");
        	editor.putString("56", "����Щ��ã��");
        	editor.putString("57", "�ѵ�˵������ֽ��������������ҵģ�");
        	editor.putString("58", "��Ը����������ģ���Ըȥ˼�����������Լ�����ô�졣");
        	editor.putString("59", "���ҳٳټǲ����֮ǰ�������Ҹо���������ġ�");
        	editor.putString("59", "��һ������˯��10����?");
        	editor.putString("60", "��ȥ���濴���ɣ���������Ҳû���á�");
        	editor.putString("61", "��ô���ţ����ٴδ������£�׼���߳����š� ");
        	editor.putString("62", "���ŵ�һɲ�ǣ����һƬ�������·����һ��ͨ��������Ĵ��š�");
        	editor.commit();
    	}
 
    	
    	
    	
    }

    


}
