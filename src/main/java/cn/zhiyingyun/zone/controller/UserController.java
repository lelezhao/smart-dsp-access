package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Joy.Zhao on 2017/7/28.
 */
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseController {

  @Autowired
  private IUserService userService;
}
