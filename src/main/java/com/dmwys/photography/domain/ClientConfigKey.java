package com.dmwys.photography.domain;
import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
@Table("kytv_client_config_key")
public class ClientConfigKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3580891648833130818L;
	@Id
	private Integer id;
	@Column(value="config_key")
	private String key;
	@Column(value="description")
	private String desc;
	@Column
	private Integer create_id;
	@Column
	private Date create_time;
	@Column
	private Integer modify_id;
	@Column
	private Date modify_time;
	//临时属性
	private String createName;
	private String modifyName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getModify_id() {
		return modify_id;
	}
	public void setModify_id(Integer modify_id) {
		this.modify_id = modify_id;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
}
