package com.dmwys.photography.domain;

import java.util.List;

/**
 * Created by huang on 24/03/2017.
 */
public class VideoSearchParams {

  private String adId;

  private String[] meetMins;

  private String[] intervalMins;

  private String[] faultTolerantMins;

  private List<VideoSearchParam> params;

  public String getAdId() {
    return adId;
  }

  public void setAdId(String adId) {
    this.adId = adId;
  }

  public String[] getMeetMins() {
    return meetMins;
  }

  public void setMeetMins(String[] meetMins) {
    this.meetMins = meetMins;
  }

  public String[] getIntervalMins() {
    return intervalMins;
  }

  public void setIntervalMins(String[] intervalMins) {
    this.intervalMins = intervalMins;
  }

  public String[] getFaultTolerantMins() {
    return faultTolerantMins;
  }

  public void setFaultTolerantMins(String[] faultTolerantMins) {
    this.faultTolerantMins = faultTolerantMins;
  }

  public List<VideoSearchParam> getParams() {
    return params;
  }

  public void setParams(List<VideoSearchParam> params) {
    this.params = params;
  }
}
