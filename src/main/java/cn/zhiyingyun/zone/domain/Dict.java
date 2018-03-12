package cn.zhiyingyun.zone.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dict {
  private String key;
  private String value;
  private String shortName;
  private String typeCode;
  private Integer order;
  private String pKey;
  private Integer level;
  private String remark;
  private Boolean isDel;

  @Id
  @Column(name = "key", nullable = false, length = 32)
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Basic
  @Column(name = "value", nullable = false, length = 32)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Basic
  @Column(name = "short_name", nullable = false, length = 16)
  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  @Basic
  @Column(name = "type_code", nullable = false, length = 32)
  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  @Basic
  @Column(name = "order", nullable = false)
  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  @Basic
  @Column(name = "p_key", nullable = false, length = 32)
  public String getpKey() {
    return pKey;
  }

  public void setpKey(String pKey) {
    this.pKey = pKey;
  }

  @Basic
  @Column(name = "level", nullable = false)
  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  @Basic
  @Column(name = "remark", nullable = false, length = 32)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Basic
  @Column(name = "is_del", nullable = false)
  public Boolean getDel() {
    return isDel;
  }

  public void setDel(Boolean del) {
    isDel = del;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Dict dict = (Dict) o;

    if (key != null ? !key.equals(dict.key) : dict.key != null) return false;
    if (value != null ? !value.equals(dict.value) : dict.value != null) return false;
    if (shortName != null ? !shortName.equals(dict.shortName) : dict.shortName != null) return false;
    if (typeCode != null ? !typeCode.equals(dict.typeCode) : dict.typeCode != null) return false;
    if (order != null ? !order.equals(dict.order) : dict.order != null) return false;
    if (pKey != null ? !pKey.equals(dict.pKey) : dict.pKey != null) return false;
    if (level != null ? !level.equals(dict.level) : dict.level != null) return false;
    if (remark != null ? !remark.equals(dict.remark) : dict.remark != null) return false;
    if (isDel != null ? !isDel.equals(dict.isDel) : dict.isDel != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
    result = 31 * result + (typeCode != null ? typeCode.hashCode() : 0);
    result = 31 * result + (order != null ? order.hashCode() : 0);
    result = 31 * result + (pKey != null ? pKey.hashCode() : 0);
    result = 31 * result + (level != null ? level.hashCode() : 0);
    result = 31 * result + (remark != null ? remark.hashCode() : 0);
    result = 31 * result + (isDel != null ? isDel.hashCode() : 0);
    return result;
  }
}
