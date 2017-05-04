package com.dmwys.photography.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * kytv库中用户信息管理
 * 
 */
@Table("uia_user_info")
public class UserImpl implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@Id
	private Long id;
	/**
	 * 是否有效
	 */
	@Column
	private Integer flag;
	/**
	 * 密码
	 */
	@Column
	private String password;
	/**
	 * 用户名
	 */
	@Column
	private String name;
	/**
	 * 用户的真实姓名
	 */
	@Column
	private String chName;
	/**
	 * 用户渠道
	 */
	@Column
	private String channelSource;
	/**
	 * 系统用户的email
	 */
	@Column
	private String email;
	/**
	 * 用户的电话
	 */
	@Column
	private String phoneNum;
	/**
	 * 用户类型，1运营人员     (暂时只有1后面的为保留项)
	 */
	@Column
	private Integer user_type;
	/***
	 * 产品ID
	 */
	@Column
	private Long productId ;
	private boolean checked;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getChannelSource() {
		return channelSource;
	}
	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer userType) {
		user_type = userType;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
