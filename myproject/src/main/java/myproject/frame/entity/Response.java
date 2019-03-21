package myproject.frame.entity;

import java.io.Serializable;

/**
 * user
 * @author user
 *
 */
public class Response implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private String statu;
	private User user;
	public Response(){
 	}
	public Response(String msg,String statu,User user){
		this.msg=msg;
		this.statu=statu;
		this.user=user;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
 
}
