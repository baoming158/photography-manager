package com.dmwys.photography.domain;

import java.util.List;

/**
 * Created by huang on 24/03/2017.
 */
public class VideoSearchParam {

  // 视频标签、信息、分类、类型
  private String firstParam;

  // 视频标签类二级参数
  private String secondParam;

  // 视频标签类三级参数
  private String thirdParam;

  // 视频标签类四级参数
  private List<String> fourthParam;

  public String getFirstParam() {
    return firstParam;
  }

  public void setFirstParam(String firstParam) {
    this.firstParam = firstParam;
  }

  public String getSecondParam() {
    return secondParam;
  }

  public void setSecondParam(String secondParam) {
    this.secondParam = secondParam;
  }

  public String getThirdParam() {
    return thirdParam;
  }

  public void setThirdParam(String thirdParam) {
    this.thirdParam = thirdParam;
  }

  public List<String> getFourthParam() {
    return fourthParam;
  }

  public void setFourthParam(List<String> fourthParam) {
    this.fourthParam = fourthParam;
  }
}
