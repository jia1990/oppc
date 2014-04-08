package com.oppc.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 * 菜单的实体类
 */

@Entity
@Table(name="os_menu")
public class MenuEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3963431745135785232L;

	/**菜单的id*/
	private int intDisId;
	
	/**菜单的名称*/
	private String vcDisText;
	
	/**菜单的指定路径*/
	private String vcDisUrl;
	
	/**菜单的上一级的id*/
	private int intParId;

	
	/**
	 * 菜单主键
	 * @return
	 */
	@Id
	@Column(name="int_disId",nullable=false)
	public int getIntDisId() {
		return intDisId;
	}

	public void setIntDisId(int intDisId) {
		this.intDisId = intDisId;
	}
	
	@Column(name="vc_disText")
	public String getVcDisText() {
		return vcDisText;
	}

	
	public void setVcDisText(String vcDisText) {
		this.vcDisText = vcDisText;
	}
	@Column(name="vc_disUrl")
	public String getVcDisUrl() {
		return vcDisUrl;
	}

	public void setVcDisUrl(String vcDisUrl) {
		this.vcDisUrl = vcDisUrl;
	}

	@Column(name="int_parId")
	public int getIntParId() {
		return intParId;
	}

	public void setIntParId(int intParId) {
		this.intParId = intParId;
	}
	
	
}
