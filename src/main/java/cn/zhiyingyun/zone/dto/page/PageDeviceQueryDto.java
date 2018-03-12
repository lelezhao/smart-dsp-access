package cn.zhiyingyun.zone.dto.page;

/**
 * Created by Joy.Zhao on 2017/7/28.
 */
public class PageDeviceQueryDto extends BasePageQueryDto {
  private String mac;
  private String supply;
  private String model;

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public String getSupply() {
    return supply;
  }

  public void setSupply(String supply) {
    this.supply = supply;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }
}
