package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.domain.DspUser;
import cn.zhiyingyun.zone.dto.RequstBuildDto;
import cn.zhiyingyun.zone.entity.UpPlatRequest;
import cn.zhiyingyun.zone.entity.UpPlatResponse;
import cn.zhiyingyun.zone.service.IBuildRequestService;
import cn.zhiyingyun.zone.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping(value = "/request", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RequestController extends BaseController {

  @Autowired
  IBuildRequestService buildRequestService;

  @Autowired
  IUserService userService;

  StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

  private RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(stringHttpMessageConverter).build();


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


  @PostMapping(value = "/bid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult bid(@RequestBody UpPlatRequest upPlatRequest) {
    //参数校验
    DspUser dspUser = userService.findOne(getUserId());

    String requestUrl = dspUser.getRequestUrl();
    MediaType type = MediaType.APPLICATION_JSON_UTF8;

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(type);
    httpHeaders.add("content-type", "application/json");
    httpHeaders.add("x-openrtb-version", "2.3.2");
    httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());


    HttpEntity<String> requstEntity = new HttpEntity<>(JSONObject.toJSONString(upPlatRequest), httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, requstEntity, String.class);

    if (StringUtils.isBlank(responseEntity.getBody())) {
      return renderError("竞价响应内容为空！");
    }

    UpPlatResponse upPlatResponse = JSONObject.parseObject(responseEntity.getBody(), UpPlatResponse.class);

    return renderSuccess(upPlatResponse);
  }
}
