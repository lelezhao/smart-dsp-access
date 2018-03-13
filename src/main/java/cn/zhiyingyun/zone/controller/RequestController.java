package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.dto.RequstBuildDto;
import cn.zhiyingyun.zone.service.IBuildRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/request", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RequestController extends BaseController {

  @Autowired
  IBuildRequestService buildRequestService;

  @GetMapping(value = "/build", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult build(RequstBuildDto requstBuildDto) {

    return renderSuccess();
  }

}
