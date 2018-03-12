package cn.zhiyingyun.zone.dto.page;

/**
 * Created by Joy.Zhao on 2017/7/28.
 */
public class PageUserQueryDto extends BasePageQueryDto {
  private String companyName;

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
}
