package com.fit.entity;

import com.fit.base.BaseModel;

public class TbsLoginLogModel extends BaseModel {

    private String id; //主键
    private String username; //用户名
    private String password; //密码
    private java.sql.Timestamp createTime; //时间
    private String ip; //IP
    private String userAgent; //设备
    private Integer status; //状态
    private String msg; //消息
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>主键<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return id
	 */
		
	public String getId(){
	   return id;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>主键<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param id
	 */
	public void setId(String id){
	   this.id=id;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>用户名<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return username
	 */
		
	public String getUsername(){
	   return username;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>用户名<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param username
	 */
	public void setUsername(String username){
	   this.username=username;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>密码<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return password
	 */
		
	public String getPassword(){
	   return password;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>密码<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param password
	 */
	public void setPassword(String password){
	   this.password=password;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return createTime
	 */
	@com.alibaba.fastjson.annotation.JSONField(format="yyyy-MM-dd HH:mm:ss")	
	public java.sql.Timestamp getCreateTime(){
	   return createTime;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param createTime
	 */
	public void setCreateTime(java.sql.Timestamp createTime){
	   this.createTime=createTime;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>IP<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return ip
	 */
		
	public String getIp(){
	   return ip;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>IP<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param ip
	 */
	public void setIp(String ip){
	   this.ip=ip;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>设备<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return userAgent
	 */
		
	public String getUserAgent(){
	   return userAgent;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>设备<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param userAgent
	 */
	public void setUserAgent(String userAgent){
	   this.userAgent=userAgent;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>状态<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return status
	 */
		
	public Integer getStatus(){
	   return status;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>状态<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param status
	 */
	public void setStatus(Integer status){
	   this.status=status;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>消息<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return msg
	 */
		
	public String getMsg(){
	   return msg;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>消息<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param msg
	 */
	public void setMsg(String msg){
	   this.msg=msg;
	}
    
	/**
	 * 
	 * <br>
	 * <b>功能：</b>重写<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return
	 */
    public String toString(){
	  return com.alibaba.fastjson.JSON.toJSONString(this);
	  // return ${SQL.toString}
    }
	
	///////////////////////////增加/////////////////////////
	
   

}
