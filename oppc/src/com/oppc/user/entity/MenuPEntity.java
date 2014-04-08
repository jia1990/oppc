package com.oppc.user.entity;

/**
 * @author Administrator
 * 这是一个虚拟的菜单类
 * 主要用来接收菜单的值的
 */
public class MenuPEntity {
	
	/**根节点的id*/
	private int rooId;
	/**根节点的名字*/
	private String rootStr;
	/**根节点的路径*/
	private String rootUrl;
	
	/**父节点的id*/
	private int fatherId;
	/**父节点的名字*/
	private String fatherStr;
	/**父节点的路径*/
	private String fatherUrl;
	
	/**子节点的id*/
	private int childId;
	/**子节点的名字*/
	private String childStr;
	/**子节点的路径*/
	private String childUrl;
	
	
	/**
	 * 这是根节点
	 * @return
	 */
	public int getRooId() {
		return rooId;
	}
	/**
	 * 这是根节点
	 * @param rooId
	 */
	public void setRooId(int rooId) {
		this.rooId = rooId;
	}
	
	/**
	 * 这是根节点的名字
	 * @return
	 */
	public String getRootStr() {
		return rootStr;
	}
	
	/**
	 * 这是根节点的名字
	 * @param rootStr
	 */
	public void setRootStr(String rootStr) {
		this.rootStr = rootStr;
	}
	
	/**
	 * 这是根节点的url
	 * @return
	 */
	public String getRootUrl() {
		return rootUrl;
	}
	/**
	 * 这是根节点的url 
	 * @param rootUrl
	 */
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	
	/**
	 * 这是父节点的id
	 * @return
	 */
	public int getFatherId() {
		return fatherId;
	}
	/**
	 * 这是父节点的id
	 * @param fatherId
	 */
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}
	/**
	 * 这是父节点的名字
	 * @return
	 */
	public String getFatherStr() {
		return fatherStr;
	}
	/**
	 * 这是父节点的名字
	 * @param fatherStr
	 */
	public void setFatherStr(String fatherStr) {
		this.fatherStr = fatherStr;
	}
	/**
	 * 这是父节点的url
	 * @return
	 */
	public String getFatherUrl() {
		return fatherUrl;
	}
	/**
	 * 这是父节点的url
	 * @param fatherUrl
	 */
	public void setFatherUrl(String fatherUrl) {
		this.fatherUrl = fatherUrl;
	}
	
	/**
	 * 这是子节点的id 
	 * @return
	 */
	public int getChildId() {
		return childId;
	}
	
	/**
	 * 这是子节点的id
	 * @param childId
	 */
	public void setChildId(int childId) {
		this.childId = childId;
	}
	/**
	 * 这是子节点的名字
	 * @return
	 */
	public String getChildStr() {
		return childStr;
	}
	/**
	 * 这是子节点的名字
	 * @param childStr
	 */
	public void setChildStr(String childStr) {
		this.childStr = childStr;
	}
	
	/**
	 * 这是子节点的url
	 * @return
	 */
	public String getChildUrl() {
		return childUrl;
	}
	
	/**
	 * 这是子节点的url
	 * @param childUrl
	 */
	public void setChildUrl(String childUrl) {
		this.childUrl = childUrl;
	}
	
	
	
}
