package cn.zhiyingyun.zone.exception;

/**
 * Created by kalrey on 17/4/30.
 */
public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException(String msg) {
    super(msg);
  }

  public InternalServerErrorException(String message, Throwable cause) {
    super(message, cause);
  }
}
