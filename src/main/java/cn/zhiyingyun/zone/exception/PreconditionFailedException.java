package cn.zhiyingyun.zone.exception;

/**
 * Created by kalrey on 17/4/27.
 */
public class PreconditionFailedException extends RuntimeException {
  public PreconditionFailedException(String message) {
    super(message);
  }
}
