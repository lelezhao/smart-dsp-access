package cn.zhiyingyun.zone.dto;

public class RequstBuildDto {
  private String slotType;
  private String slotSize;
  private String sellType;
  private String dealId;
  private Double dealPrice;
  private Boolean isSupportDeeplink;
  private Boolean isSupportDownload;
  private Boolean isSecure;
  private String carrir;
  private String netType;
  private String osType;

  public String getSlotType() {
    return slotType;
  }

  public void setSlotType(String slotType) {
    this.slotType = slotType;
  }

  public String getSlotSize() {
    return slotSize;
  }

  public void setSlotSize(String slotSize) {
    this.slotSize = slotSize;
  }

  public String getSellType() {
    return sellType;
  }

  public void setSellType(String sellType) {
    this.sellType = sellType;
  }

  public String getDealId() {
    return dealId;
  }

  public void setDealId(String dealId) {
    this.dealId = dealId;
  }

  public Double getDealPrice() {
    return dealPrice;
  }

  public void setDealPrice(Double dealPrice) {
    this.dealPrice = dealPrice;
  }

  public Boolean getSupportDeeplink() {
    return isSupportDeeplink;
  }

  public void setSupportDeeplink(Boolean supportDeeplink) {
    isSupportDeeplink = supportDeeplink;
  }

  public Boolean getSupportDownload() {
    return isSupportDownload;
  }

  public void setSupportDownload(Boolean supportDownload) {
    isSupportDownload = supportDownload;
  }

  public Boolean getSecure() {
    return isSecure;
  }

  public void setSecure(Boolean secure) {
    isSecure = secure;
  }

  public String getCarrir() {
    return carrir;
  }

  public void setCarrir(String carrir) {
    this.carrir = carrir;
  }

  public String getNetType() {
    return netType;
  }

  public void setNetType(String netType) {
    this.netType = netType;
  }

  public String getOsType() {
    return osType;
  }

  public void setOsType(String osType) {
    this.osType = osType;
  }
}
