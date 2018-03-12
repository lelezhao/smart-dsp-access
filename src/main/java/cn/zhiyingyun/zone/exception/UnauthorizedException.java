package cn.zhiyingyun.zone.exception;

/**
 * Created by kalrey on 17/4/13.
 */
public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }
}
