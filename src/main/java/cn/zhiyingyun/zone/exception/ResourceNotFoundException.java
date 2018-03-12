package cn.zhiyingyun.zone.exception;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Integer id;

  public ResourceNotFoundException(Integer id) {
    super(String.format("resource %s was not found", id));
    this.id = id;
  }

  public ResourceNotFoundException(Serializable id) {
    super(String.format("resource %s was not found", id));
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public Integer getId() {
    return id;
  }
}
