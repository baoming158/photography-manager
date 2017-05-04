package com.dmwys.photography.domain;

public class VideoInfo {

	public long id;
	public String title;
	public String type;
	public String absTitle;
	public String time;
	public int dotcount;
	public String edit_status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		switch (this.type) {
		case "movie":
			return "电影";
		case "series":
			return "电视剧";
		case "anime":
			return "动漫";
		case "variety":
			return "综艺";
		default:
			return "未知";
		}
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAbsTitle() {
		return absTitle;
	}
	public void setAbsTitle(String absTitle) {
		this.absTitle = absTitle;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getDotcount() {
		return dotcount;
	}
	public void setDotcount(int dotcount) {
		this.dotcount = dotcount;
	}
	public String getEdit_status() {
		switch (this.edit_status) {
		case "0":
			return "初始化";
		case "1":
			return "-";
		case "2":
			return "已编辑";
		default:
			return "未知";
		}
	}
	public void setEdit_status(String edit_status) {
		this.edit_status = edit_status;
	}
	
}
