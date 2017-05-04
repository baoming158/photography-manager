package com.dmwys.photography.domain;

/**
 * Created by huang on 24/03/2017.
 */
public class VideoSearchResults {

  private Integer id;

  // 视频类型
  private String type;

  // 视频名称
  private String name;

  // 抽象视频名称
  private String nickName;

  // 视频时长
  private Integer videoMins;

  // 打点段数
  private Integer dots;

  // 状态
  private String status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Integer getVideoMins() {
    return videoMins;
  }

  public void setVideoMins(Integer videoMins) {
    this.videoMins = videoMins;
  }

  public Integer getDots() {
    return dots;
  }

  public void setDots(Integer dots) {
    this.dots = dots;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
