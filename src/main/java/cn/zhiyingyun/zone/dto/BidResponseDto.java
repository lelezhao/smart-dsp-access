package cn.zhiyingyun.zone.dto;

import cn.zhiyingyun.zone.entity.UpPlatResponse;

import java.util.List;

public class BidResponseDto {
  private Integer httpCode;

  private UpPlatResponse upPlatResponse;

  private List<String> errorMessage;

  public Integer getHttpCode() {
    return httpCode;
  }

  public void setHttpCode(Integer httpCode) {
    this.httpCode = httpCode;
  }

  public UpPlatResponse getUpPlatResponse() {
    return upPlatResponse;
  }

  public void setUpPlatResponse(UpPlatResponse upPlatResponse) {
    this.upPlatResponse = upPlatResponse;
  }

  public List<String> getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(List<String> errorMessage) {
    this.errorMessage = errorMessage;
  }
}
