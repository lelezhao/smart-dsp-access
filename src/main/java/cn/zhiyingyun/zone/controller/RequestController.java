package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.dto.RequstBuildDto;
import cn.zhiyingyun.zone.entity.UpPlatRequest;
import cn.zhiyingyun.zone.service.IBuildRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping(value = "/request", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RequestController extends BaseController {

  @Autowired
  IBuildRequestService buildRequestService;

  @PostMapping(value = "/build", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult build(RequstBuildDto requstBuildDto) {

    //参数校验

    UpPlatRequest upPlatRequest = new UpPlatRequest();

    upPlatRequest.id = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();

    UpPlatRequest.Impression impression = buildRequestService.buildBannerImp(requstBuildDto.getSupportDeeplink(), requstBuildDto.getSupportDownload(), requstBuildDto.getSecure());

    UpPlatRequest.Device device = buildRequestService.buildDevice(requstBuildDto.getCarrir(), 4, requstBuildDto.getOsType());

    UpPlatRequest.App app = buildRequestService.buildApp();

    UpPlatRequest.User user = buildRequestService.buildUser();

    upPlatRequest.imp = Arrays.asList(impression);
    upPlatRequest.device = device;
    upPlatRequest.app = app;
    upPlatRequest.user = user;

    return renderSuccess(upPlatRequest);
  }

}
