package com.oppc.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xj
 * 用户信息表(t_user)
 */
/**
 * @author Administrator
 *
 */
@Entity
@Table(name="bs_user")
public class UserEntity implements java.io.Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1938405251854753964L;

	/** 主键，流水号*/
	private String vcUid;
	
	/** 用户年龄*/
	private int intAge;
	
	/**用户名*/
	private String vcUname;
	
	/** 用户密码 */
	private String vcPwd;
	
	/**用户出生日期*/
	private Date dtBirday;
	
	/**用户权限 1代表超级用户，2是普通用户*/
	private int intStatue;
	
	
	/**
	 * 用户主键
	 * @return
	 */
	@Id
	@Column(name="vc_uid",nullable=false)
	public String getVcUid() {
		return vcUid;
	}
	
	/**
	 * 用户主键
	 * @param vcUid
	 */
	public void setVcUid(String vcUid) {
		this.vcUid = vcUid;
	}
	
	/**
	 * 用户年龄
	 * @return
	 */
	@Column(name="int_uage")
	public int getIntAge() {
		return intAge;
	}
	
	/**
	 * 用户年龄
	 * @param intAge
	 */
	public void setIntAge(int intAge) {
		this.intAge = intAge;
	}
	
	/**
	 * 用户名
	 * @return
	 */
	@Column(name="vc_uname")
	public String getVcUname() {
		return vcUname;
	}
	/**
	 * 用户名
	 * @param vcUname
	 */
	public void setVcUname(String vcUname) {
		this.vcUname = vcUname;
	}
	/**
	 * 用户密码
	 * @return
	 */
	@Column(name="vc_upwd")
	public String getVcPwd() {
		return vcPwd;
	}
	
	/**
	 * 用户密码
	 * @param vcPwd
	 */
	public void setVcPwd(String vcPwd) {
		this.vcPwd = vcPwd;
	}
	/**
	 * 用户出生日期
	 * @return
	 */
	@Column(name="dt_ubirday",nullable=false)
	public Date getDtBirday() {
		return dtBirday;
	}
	/**
	 * 用户出生日期
	 * @param dtBirday
	 */
	public void setDtBirday(Date dtBirday) {
		this.dtBirday = dtBirday;
	}

	/**
	 * 用户权限
	 * @return
	 */
	@Column(name="int_statue")
	public int getIntStatue() {
		return intStatue;
	}

	/**
	 * 用户权限
	 * @param intStatue
	 */
	public void setIntStatue(int intStatue) {
		this.intStatue = intStatue;
	}
	
	
	
}
