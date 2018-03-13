package cn.zhiyingyun.zone.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class BidHistoryDto implements Serializable {
  private Integer id;
  private Timestamp createTime;
  private Timestamp updateTime;
  private Integer userId;
  private String bidid;
  private String requestBody;
  private String requestUrl;
  private String sellType;
  private String responseCode;
  private String responseBody;
  private String errorMessage;
  private String checkResult;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getBidid() {
    return bidid;
  }

  public void setBidid(String bidid) {
    this.bidid = bidid;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public String getRequestUrl() {
    return requestUrl;
  }

  public void setRequestUrl(String requestUrl) {
    this.requestUrl = requestUrl;
  }

  public String getSellType() {
    return sellType;
  }

  public void setSellType(String sellType) {
    this.sellType = sellType;
  }

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getCheckResult() {
    return checkResult;
  }

  public void setCheckResult(String checkResult) {
    this.checkResult = checkResult;
  }
}
