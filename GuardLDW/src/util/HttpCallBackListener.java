package util;

/*
 * 回调向服务器发送请求后服务器返回结果
 */

public interface HttpCallBackListener {//接口不能有方法体

	void onFinish(String response);//参数为返回的数据
	
	void onError(Exception e);//参数为错误信息

}
