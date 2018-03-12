package cn.zhiyingyun.zone.common;

import org.springframework.http.HttpStatus;

/**
 * Created by Joy.Zhao on 2017/3/24.
 */
public class JsonResult {
  private int code;
  private String message;
  private Object data;

  public JsonResult() {
    this.setCode(HttpStatus.OK);
    this.setMessage(HttpStatus.OK.getReasonPhrase());

  }

  public JsonResult(HttpStatus status) {
    this.setCode(status);
    this.setMessage(status.getReasonPhrase());
  }

  public JsonResult(Object data) {
    this.setCode(HttpStatus.OK);
    this.setMessage(HttpStatus.OK.getReasonPhrase());
    this.setData(data);
  }

  public JsonResult(HttpStatus status, String message) {
    this.setCode(status);
    this.setMessage(message);
  }

  public JsonResult(HttpStatus status, String message, Object data) {
    this.setCode(status);
    this.setMessage(message);
    this.setData(data);
  }

  public int getCode() {
    return code;
  }

  public void setCode(HttpStatus status) {
    this.code = status.value();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
