package cn.zhiyingyun.zone.dto.page;

/**
 * Created by Joy.Zhao on 2017/8/11.
 */
public class PageDetectionQueryDto extends BasePageQueryDto {
  private Integer id;

  private String mac;

  private String beginDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }
}
