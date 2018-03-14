package cn.zhiyingyun.zone.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dsp_bid_history", schema = "smart_dsp", catalog = "")
public class DspBidHistory {
  private Integer id;
  private Integer userId;
  private String bidid;
  private String requestBody;
  private String requestUrl;
  private String sellType;
  private String responseCode;
  private String responseBody;
  private String errorMessage;
  private String checkResult;
  private Timestamp createTime;
  private Timestamp updateTime;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "user_id", nullable = true)
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "bidid", nullable = true, length = 255)
  public String getBidid() {
    return bidid;
  }

  public void setBidid(String bidid) {
    this.bidid = bidid;
  }

  @Basic
  @Column(name = "request_body", nullable = true, length = -1)
  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  @Basic
  @Column(name = "request_url", nullable = true, length = 255)
  public String getRequestUrl() {
    return requestUrl;
  }

  public void setRequestUrl(String requestUrl) {
    this.requestUrl = requestUrl;
  }

  @Basic
  @Column(name = "sell_type", nullable = true, length = 255)
  public String getSellType() {
    return sellType;
  }

  public void setSellType(String sellType) {
    this.sellType = sellType;
  }

  @Basic
  @Column(name = "response_code", nullable = true, length = 255)
  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  @Basic
  @Column(name = "response_body", nullable = true, length = -1)
  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  @Basic
  @Column(name = "error_message", nullable = true, length = 1024)
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Basic
  @Column(name = "check_result", nullable = true, length = 255)
  public String getCheckResult() {
    return checkResult;
  }

  public void setCheckResult(String checkResult) {
    this.checkResult = checkResult;
  }

  @Basic
  @Column(name = "create_time", nullable = true)
  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  @Basic
  @Column(name = "update_time", nullable = true)
  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DspBidHistory that = (DspBidHistory) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
    if (bidid != null ? !bidid.equals(that.bidid) : that.bidid != null) return false;
    if (requestBody != null ? !requestBody.equals(that.requestBody) : that.requestBody != null) return false;
    if (requestUrl != null ? !requestUrl.equals(that.requestUrl) : that.requestUrl != null) return false;
    if (sellType != null ? !sellType.equals(that.sellType) : that.sellType != null) return false;
    if (responseCode != null ? !responseCode.equals(that.responseCode) : that.responseCode != null) return false;
    if (responseBody != null ? !responseBody.equals(that.responseBody) : that.responseBody != null) return false;
    if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
    if (checkResult != null ? !checkResult.equals(that.checkResult) : that.checkResult != null) return false;
    if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
    if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    result = 31 * result + (bidid != null ? bidid.hashCode() : 0);
    result = 31 * result + (requestBody != null ? requestBody.hashCode() : 0);
    result = 31 * result + (requestUrl != null ? requestUrl.hashCode() : 0);
    result = 31 * result + (sellType != null ? sellType.hashCode() : 0);
    result = 31 * result + (responseCode != null ? responseCode.hashCode() : 0);
    result = 31 * result + (responseBody != null ? responseBody.hashCode() : 0);
    result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
    result = 31 * result + (checkResult != null ? checkResult.hashCode() : 0);
    result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
    result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
    return result;
  }
}
