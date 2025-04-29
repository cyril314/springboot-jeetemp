package com.fit.entity;

import com.fit.base.BaseModel;

public class TbcContentModel extends BaseModel {

    private String id; //主键
    private String menuId; //关联导航
    private String title; //标题
    private String tags; //标签
    private String content; //内容
    private Integer status; //状态
    private Integer orderByNumber; //排序
    private java.sql.Timestamp createTime; //创建时间
	
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
	 * <b>功能：</b>关联导航<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return menuId
	 */
		
	public String getMenuId(){
	   return menuId;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>关联导航<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param menuId
	 */
	public void setMenuId(String menuId){
	   this.menuId=menuId;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>标题<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return title
	 */
		
	public String getTitle(){
	   return title;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>标题<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param title
	 */
	public void setTitle(String title){
	   this.title=title;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>标签<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return tags
	 */
		
	public String getTags(){
	   return tags;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>标签<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param tags
	 */
	public void setTags(String tags){
	   this.tags=tags;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>内容<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return content
	 */
		
	public String getContent(){
	   return content;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>内容<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param content
	 */
	public void setContent(String content){
	   this.content=content;
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
	 * <b>功能：</b>排序<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return orderByNumber
	 */
		
	public Integer getOrderByNumber(){
	   return orderByNumber;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>排序<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param orderByNumber
	 */
	public void setOrderByNumber(Integer orderByNumber){
	   this.orderByNumber=orderByNumber;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>创建时间<br>
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
	 * <b>功能：</b>创建时间<br>
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
