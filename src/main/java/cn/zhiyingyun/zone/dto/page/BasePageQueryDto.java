package cn.zhiyingyun.zone.dto.page;

/**
 * Created by Joy.Zhao on 2017/5/15.
 */
public class BasePageQueryDto {
  Integer userId;

  String name;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
