package cn.zhiyingyun.zone.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dsp_user", schema = "smart_dsp", catalog = "")
public class DspUser {
  private Integer id;
  private String account;
  private String password;
  private Integer dspId;
  private String dspName;
  private String requestUrl;
  private String token;
  private String noticeType;
  private String companyName;
  private Timestamp createTime;
  private Timestamp updateTime;

  @Id
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "account", nullable = false, length = 32)
  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  @Basic
  @Column(name = "password", nullable = false, length = 64)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "dsp_id", nullable = false)
  public Integer getDspId() {
    return dspId;
  }

  public void setDspId(Integer dspId) {
    this.dspId = dspId;
  }

  @Basic
  @Column(name = "dsp_name", nullable = false, length = 255)
  public String getDspName() {
    return dspName;
  }

  public void setDspName(String dspName) {
    this.dspName = dspName;
  }

  @Basic
  @Column(name = "request_url", nullable = false, length = 255)
  public String getRequestUrl() {
    return requestUrl;
  }

  public void setRequestUrl(String requestUrl) {
    this.requestUrl = requestUrl;
  }

  @Basic
  @Column(name = "token", nullable = false, length = 255)
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Basic
  @Column(name = "notice_type", nullable = true, length = 255)
  public String getNoticeType() {
    return noticeType;
  }

  public void setNoticeType(String noticeType) {
    this.noticeType = noticeType;
  }

  @Basic
  @Column(name = "company_name", nullable = true, length = 255)
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Basic
  @Column(name = "create_time", nullable = false)
  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  @Basic
  @Column(name = "update_time", nullable = false)
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

    DspUser dspUser = (DspUser) o;

    if (id != null ? !id.equals(dspUser.id) : dspUser.id != null) return false;
    if (account != null ? !account.equals(dspUser.account) : dspUser.account != null) return false;
    if (password != null ? !password.equals(dspUser.password) : dspUser.password != null) return false;
    if (dspId != null ? !dspId.equals(dspUser.dspId) : dspUser.dspId != null) return false;
    if (dspName != null ? !dspName.equals(dspUser.dspName) : dspUser.dspName != null) return false;
    if (requestUrl != null ? !requestUrl.equals(dspUser.requestUrl) : dspUser.requestUrl != null) return false;
    if (token != null ? !token.equals(dspUser.token) : dspUser.token != null) return false;
    if (noticeType != null ? !noticeType.equals(dspUser.noticeType) : dspUser.noticeType != null) return false;
    if (companyName != null ? !companyName.equals(dspUser.companyName) : dspUser.companyName != null) return false;
    if (createTime != null ? !createTime.equals(dspUser.createTime) : dspUser.createTime != null) return false;
    if (updateTime != null ? !updateTime.equals(dspUser.updateTime) : dspUser.updateTime != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (account != null ? account.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (dspId != null ? dspId.hashCode() : 0);
    result = 31 * result + (dspName != null ? dspName.hashCode() : 0);
    result = 31 * result + (requestUrl != null ? requestUrl.hashCode() : 0);
    result = 31 * result + (token != null ? token.hashCode() : 0);
    result = 31 * result + (noticeType != null ? noticeType.hashCode() : 0);
    result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
    result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
    result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
    return result;
  }
}
