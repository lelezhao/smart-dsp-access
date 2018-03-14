package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.domain.DspBidHistory;
import cn.zhiyingyun.zone.domain.DspUser;
import cn.zhiyingyun.zone.dto.RequstBuildDto;
import cn.zhiyingyun.zone.entity.UpPlatRequest;
import cn.zhiyingyun.zone.entity.UpPlatResponse;
import cn.zhiyingyun.zone.service.IBuildRequestService;
import cn.zhiyingyun.zone.service.IDspBidHistoryService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/request", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RequestController extends BaseController {

  @Autowired
  IBuildRequestService buildRequestService;
  @Autowired
  IUserService userService;
  @Autowired
  IDspBidHistoryService dspBidHistoryService;

  private static final List<String> bannerSlotTypes = Arrays.asList("s_t-banner,s_t-full,s_t-interstitial");
  private static final List<String> nativeSlotTypes = Arrays.asList("s_t-feeds,s_t-focus,s_t-icon,s_t-imagewall");
  private static final List<String> videoSlotTypes = Arrays.asList("s_t-video");


  StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

  private RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(stringHttpMessageConverter).build();


  @PostMapping(value = "/build", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult build(RequstBuildDto requstBuildDto) {
    //参数校验
    if (requstBuildDto == null) {
      return renderError("参数不能为空！");
    }

    if (StringUtils.isBlank(requstBuildDto.getSlotType())) {
      return renderError("请选择广告位类型！");
    }

    UpPlatRequest upPlatRequest = new UpPlatRequest();

    upPlatRequest.id = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();

    UpPlatRequest.Impression impression = null;

    if (bannerSlotTypes.contains(requstBuildDto.getSlotType())) {
      impression = buildRequestService.buildBannerImp(requstBuildDto.getSupportDeeplink(), requstBuildDto.getSupportDownload(), requstBuildDto.getSecure());
    } else if (nativeSlotTypes.contains(requstBuildDto.getSlotType())) {
      impression = buildRequestService.buildNativeImp(requstBuildDto.getSupportDeeplink(), requstBuildDto.getSupportDownload(), requstBuildDto.getSecure());
    } else if (videoSlotTypes.contains(requstBuildDto.getSlotType())) {
      impression = buildRequestService.buildVideoImp(requstBuildDto.getSupportDeeplink(), requstBuildDto.getSupportDownload(), requstBuildDto.getSecure());
    }
    if(impression==null){
      return renderError("impression构建失败");
    }

    if(StringUtils.isBlank(requstBuildDto.getOsType())){
      return renderError("请选择操作系统！");
    }

    UpPlatRequest.Device device = buildRequestService.buildDevice(requstBuildDto.getOsType());

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
    httpHeaders.add("Accept-Encoding", "gzip");
    httpHeaders.add("Content-Encoding", "UTF-8");


    HttpEntity<String> requstEntity = new HttpEntity<>(JSONObject.toJSONString(upPlatRequest), httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, requstEntity, String.class);


    DspBidHistory bidHistory = new DspBidHistory();
    createResourceInit(bidHistory);
    bidHistory.setRequestUrl(requestUrl);
    bidHistory.setRequestBody(JSONObject.toJSONString(upPlatRequest));
    bidHistory.setUserId(getUserId());
    bidHistory.setSellType("RTB");
    bidHistory.setBidid(upPlatRequest.id);
    bidHistory.setResponseCode(String.valueOf(responseEntity.getStatusCode().value()));
    bidHistory.setResponseBody(responseEntity.getBody());

    dspBidHistoryService.save(bidHistory);

    if (StringUtils.isBlank(responseEntity.getBody())) {
      return renderError("竞价响应内容为空！");
    }

    UpPlatResponse upPlatResponse = JSONObject.parseObject(responseEntity.getBody(), UpPlatResponse.class);

    return renderSuccess(upPlatResponse);
  }
}
