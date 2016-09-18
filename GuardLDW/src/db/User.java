package db;

/*
 * User表对应的实体类
 */


public class User {

	private String username;
	private String password;
	private String comment;

	
	//set()
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	
	//get()
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getComment(){
		return comment;
	}
	

}
