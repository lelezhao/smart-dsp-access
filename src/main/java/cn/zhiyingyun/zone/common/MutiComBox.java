package cn.zhiyingyun.zone.common;

import java.io.Serializable;

/**
 * Created by Joy.Zhao on 2017/4/5.
 */
public class MutiComBox {
  private Serializable value;

  private String label;

  public Serializable getValue() {
    return value;
  }

  public void setValue(Serializable value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
