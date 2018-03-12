package cn.zhiyingyun.zone.dto;

import java.io.Serializable;

/**
 * Created by llzhao on 17/6/22.
 */
public class TokenInfo implements Serializable {
  private static final long serialVersionUID = -1L;
  private Integer id;
  private String name;
  private String token;
  private String account;
  private String mobile;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
