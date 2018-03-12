package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.service.IAuthService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Joy.Zhao on 2017/3/24.
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private IAuthService authService;


  @RequestMapping(method = RequestMethod.POST, value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult loginPost(String loginname, String password) {
    logger.info("POST请求登录");
    // 改为全部抛出异常，避免ajax csrf token被刷新
    if (StringUtils.isBlank(loginname)) {
      throw new RuntimeException("登录名不能为空");
    }
    if (StringUtils.isBlank(password)) {
      throw new RuntimeException("密码不能为空");
    }
    return renderSuccess(authService.login(loginname, password));
  }


}
