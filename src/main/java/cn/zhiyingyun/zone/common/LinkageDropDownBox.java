package cn.zhiyingyun.zone.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy.Zhao on 2017/5/19.
 */
public class LinkageDropDownBox {
  private String text;

  private String label;

  private String value;

  private List<DropDownBox> children;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public List<DropDownBox> getChildren() {
    if (this.children == null) {
      List<DropDownBox> comboBoxes = new ArrayList<>();
      setChildren(comboBoxes);
      return comboBoxes;
    } else {
      return children;
    }
  }

  public void setChildren(List<DropDownBox> children) {
    this.children = children;
  }
}
