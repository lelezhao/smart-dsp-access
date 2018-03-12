package cn.zhiyingyun.zone.exception;

/**
 * Created by kalrey on 17/4/15.
 */
public class ResourceAlreadyExistException extends RuntimeException {
  public ResourceAlreadyExistException() {
    super("已存在，请勿重复添加！");
  }

  public ResourceAlreadyExistException(String message) {
    super(message);
  }

  public ResourceAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
