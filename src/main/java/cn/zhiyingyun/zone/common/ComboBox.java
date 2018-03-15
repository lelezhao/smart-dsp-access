package cn.zhiyingyun.zone.common;

/**
 * Created by Joy.Zhao on 2017/5/15.
 */
public class ComboBox {
  private String text;

  private String value;

  public ComboBox() {
  }

  public ComboBox(String textValue) {
    this.text = textValue;
    this.value = textValue;
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
}
