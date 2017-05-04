package com.dmwys.photography.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("kytv_client_config")
public class ClientConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7340942421638886939L;
	@Id
	private Integer id;
	@Column(value="config_key")
	private String key;
	@Column(value="config_value")
	private String value;
	@Column
	private Integer key_id;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getKey_id() {
		return key_id;
	}
	public void setKey_id(Integer key_id) {
		this.key_id = key_id;
	}
}
