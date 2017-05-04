package com.dmwys.photography.domain;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
/**
 * kytv库中电视台栏目管理
 * 
 * @author peiguanghao
 * 
 */
@Table("kytv_resource_column_tvstation")
public class TVStationColumn {
	/**
	 * ID
	 */
	@Id
	private Long id;
	/**
	 * 电视台名称
	 */
	private String tv_name;
	/**
     * 电视台标签
     */
    private String label;
	/**
	 * 抽象栏目名称
	 */
	private String abs_name;
	/**
	 * 频道类型
	 */
	private String cd_type;
	/**
	 * 电视台ID
	 */
	@Column
	private Long tv_id;
	/**
	 * 电台描述
	 */
	@Column
	private String cd_desc;
	/**
	 * 抽象栏目ID
	 */
	@Column
	private Long ca_id;
	/**
	 * 电视台栏目名称
	 */
	@Column
	private String cd_name;
	/**
	 * 状态  0:无效 1:有效  2:待审
	 */
	@Column
	private int status;
	/**
	 * 电视台栏目封面地址
	 */
	@Column
	private String cover;
	/**
	 * 电视台栏目开始时间
	 */
	@Column
	private Date start_time;
	/**
	 * 电视台栏目结束时间
	 */
	@Column
	private Date end_time;
	/**
	 * 是否结束  0:已结束      1:未结束  
	 */
	@Column
	private int is_end;
	/**
	 * 默认为0， 1表示首播，2表示重播
	 */
	@Column
	private int is_live;
	/**
	 * 是否录播  0:否      1:是  
	 */
	@Column
	private int videotape;
	/**
	 * 创建时间
	 */
	@Column
	private Date create_time;
	/**
	 * 创建人
	 */
	@Column
	private Integer create_id;
	/**
	 * 修改时间
	 */
	@Column
	private Date modify_time;
	/**
	 * 修改人
	 */
	@Column
	private Integer modify_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTv_name() {
		return tv_name;
	}
	public void setTv_name(String tv_name) {
		this.tv_name = tv_name;
	}
	public String getAbs_name() {
		return abs_name;
	}
	public void setAbs_name(String abs_name) {
		this.abs_name = abs_name;
	}
	public Long getTv_id() {
		return tv_id;
	}
	public void setTv_id(Long tv_id) {
		this.tv_id = tv_id;
	}
	public Long getCa_id() {
		return ca_id;
	}
	public void setCa_id(Long ca_id) {
		this.ca_id = ca_id;
	}
	public String getCd_name() {
		return cd_name;
	}
	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getIs_end() {
		return is_end;
	}
	public void setIs_end(int is_end) {
		this.is_end = is_end;
	}
	public int getVideotape() {
		return videotape;
	}
	public void setVideotape(int videotape) {
		this.videotape = videotape;
	}
	public String getCd_desc() {
		return cd_desc;
	}
	public void setCd_desc(String cd_desc) {
		this.cd_desc = cd_desc;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Integer getModify_id() {
		return modify_id;
	}
	public void setModify_id(Integer modify_id) {
		this.modify_id = modify_id;
	}
	public String getCd_type() {
		return cd_type;
	}
	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}
    public int getIs_live() {
        return is_live;
    }
    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }
    public String getLabel() {
      return label;
    }
    public void setLabel(String label) {
      this.label = label;
    }
}
