package cn.zhiyingyun.zone.exception;


import cn.zhiyingyun.zone.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Called when an exception occurs during request processing. Transforms exception message into JSON format.
 */
@RestControllerAdvice()
public class RestExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(value = {Exception.class, RuntimeException.class})
  //@ResponseBody
  public ResponseEntity<JsonResult> handleGenericException(Exception ex, WebRequest request) {
    if (log.isDebugEnabled()) {
      log.debug("handling exception...");
    }
    ex.printStackTrace();
    return new ResponseEntity<>(new JsonResult(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(value = {UnauthorizedException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public JsonResult handleUnauthorized(HttpServletRequest req, UnauthorizedException e) throws Exception {
    JsonResult jsonResult = new JsonResult();
    jsonResult.setCode(HttpStatus.UNAUTHORIZED);
    jsonResult.setMessage(e.getMessage());
    return jsonResult;
  }


  @ExceptionHandler(value = {InternalServerErrorException.class})
  @ResponseBody
  public JsonResult handleInternalServerErrorException(HttpServletRequest req, InternalServerErrorException e) {
    JsonResult jsonResult = new JsonResult();
    jsonResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
    jsonResult.setMessage(e.getMessage());
    return jsonResult;
  }

  @ExceptionHandler(value = {NumberFormatException.class})
  @ResponseBody
  public JsonResult handlerNumberFormatException(HttpServletRequest req, NumberFormatException e) {
    JsonResult jsonResult = new JsonResult();
    jsonResult.setCode(HttpStatus.BAD_REQUEST);
    jsonResult.setMessage(e.getMessage());
    return jsonResult;
  }


}
