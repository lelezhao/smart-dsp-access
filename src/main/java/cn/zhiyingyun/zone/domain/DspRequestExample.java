package cn.zhiyingyun.zone.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dsp_request_example", schema = "smart_dsp", catalog = "")
public class DspRequestExample {
  private Integer id;
  private String slotType;
  private String requestExample;
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
  @Column(name = "slot_type", nullable = true, length = 255)
  public String getSlotType() {
    return slotType;
  }

  public void setSlotType(String slotType) {
    this.slotType = slotType;
  }

  @Basic
  @Column(name = "request_example", nullable = true, length = -1)
  public String getRequestExample() {
    return requestExample;
  }

  public void setRequestExample(String requestExample) {
    this.requestExample = requestExample;
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

    DspRequestExample that = (DspRequestExample) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (slotType != null ? !slotType.equals(that.slotType) : that.slotType != null) return false;
    if (requestExample != null ? !requestExample.equals(that.requestExample) : that.requestExample != null)
      return false;
    if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
    if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (slotType != null ? slotType.hashCode() : 0);
    result = 31 * result + (requestExample != null ? requestExample.hashCode() : 0);
    result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
    result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
    return result;
  }
}
