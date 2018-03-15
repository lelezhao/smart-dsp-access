package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.domain.DspUser;
import cn.zhiyingyun.zone.dto.BidResponseDto;
import cn.zhiyingyun.zone.dto.RequstBuildDto;
import cn.zhiyingyun.zone.entity.UpPlatRequest;
import cn.zhiyingyun.zone.entity.UpPlatResponse;
import cn.zhiyingyun.zone.service.IBuildRequestService;
import cn.zhiyingyun.zone.service.ICheckResponseService;
import cn.zhiyingyun.zone.service.IDspBidHistoryService;
import cn.zhiyingyun.zone.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
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
  @Autowired
  ICheckResponseService checkResponseService;

  private static final List<String> bannerSlotTypes = Arrays.asList("s_t-banner", "s_t-full", "s_t-interstitial");
  private static final List<String> nativeSlotTypes = Arrays.asList("s_t-feeds", "s_t-focus", "s_t-icon", "s_t-imagewall");
  private static final List<String> videoSlotTypes = Arrays.asList("s_t-video");

  private static final List<String> osTypes = Arrays.asList("o_t-android", "o_t-ios");


  StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

  private RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(stringHttpMessageConverter).build();

  /**
   * 请求样例生成接口
   *
   * @param requstBuildDto 请求参数
   * @return
   */
  @PostMapping(value = "/build", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult build(RequstBuildDto requstBuildDto) {
    //参数校验
    if (requstBuildDto == null) {
      return renderError("参数不能为空！");
    }

    String slotType = requstBuildDto.getSlotType();
    String slotSize = requstBuildDto.getSlotSize();
    String osType = requstBuildDto.getOsType();
    Boolean isSupportDeeplink = requstBuildDto.getSupportDeeplink();
    Boolean isSupportDownload = requstBuildDto.getSupportDownload();
    Boolean isSecure = requstBuildDto.getSecure();

    if (StringUtils.isBlank(slotType)) {
      return renderError("请选择广告位类型！");
    }

    if (StringUtils.isBlank(osType)) {
      return renderError("请选择操作系统！");
    }
    if (!osTypes.contains(osType)) {
      return renderError("请选择正确的操作系统！");
    }

    UpPlatRequest upPlatRequest = new UpPlatRequest();

    upPlatRequest.id = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();

    UpPlatRequest.Impression impression = null;

    if (bannerSlotTypes.contains(slotType)) {
      impression = buildRequestService.buildBannerImp(slotType, isSupportDeeplink, isSupportDownload, isSecure);
    } else if (nativeSlotTypes.contains(requstBuildDto.getSlotType())) {
      impression = buildRequestService.buildNativeImp(slotType, slotSize, isSupportDeeplink, isSupportDownload, isSecure);
    } else if (videoSlotTypes.contains(requstBuildDto.getSlotType())) {
      impression = buildRequestService.buildVideoImp(slotType, slotSize, isSupportDeeplink, isSupportDownload, isSecure);
    }
    if (impression == null) {
      return renderError("impression构建失败");
    }

    UpPlatRequest.Device device = buildRequestService.buildDevice(requstBuildDto.getOsType());

    UpPlatRequest.App app = buildRequestService.buildApp();

    UpPlatRequest.User user = buildRequestService.buildUser();

    if (StringUtils.isBlank(requstBuildDto.getSellType())) {
      return renderError("请选择交易模式！");
    }

    if (requstBuildDto.getSellType().equals("s_t-pdb")) {
      if (StringUtils.isBlank(requstBuildDto.getDealId()) || requstBuildDto.getDealPrice() == null) {
        return renderError("请填写dealId或dealPrice");
      }

      UpPlatRequest.Impression.Pmp pmp = buildRequestService.buildPmp(requstBuildDto.getDealId(), requstBuildDto.getDealPrice());

      impression.pmp = pmp;
    }

    upPlatRequest.imp = Arrays.asList(impression);
    upPlatRequest.device = device;
    upPlatRequest.app = app;
    upPlatRequest.user = user;

    return renderSuccess(upPlatRequest);
  }

  /**
   * 竞价接口
   *
   * @param upPlatRequest 请求样例
   * @return
   */
  @PostMapping(value = "/bid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult bid(@RequestBody UpPlatRequest upPlatRequest, @RequestHeader String bidUrl) {
    //参数校验
    DspUser dspUser = userService.findOne(getUserId());

    String requestUrl = dspUser.getRequestUrl();

    if (StringUtils.isBlank(requestUrl)) {
      return renderError("竞价接口地址为空！");
    }

    if (StringUtils.isNotBlank(bidUrl)) {
      requestUrl = bidUrl;
    }

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


/*    DspBidHistory bidHistory = new DspBidHistory();
    createResourceInit(bidHistory);
    bidHistory.setRequestUrl(requestUrl);
    bidHistory.setRequestBody(JSONObject.toJSONString(upPlatRequest));
    bidHistory.setUserId(getUserId());
    bidHistory.setSellType("RTB");
    bidHistory.setBidid(upPlatRequest.id);
    bidHistory.setResponseCode(String.valueOf(responseEntity.getStatusCode().value()));
    bidHistory.setResponseBody(responseEntity.getBody());

    dspBidHistoryService.save(bidHistory);*/

    if (responseEntity.getStatusCodeValue() == 200 && StringUtils.isBlank(responseEntity.getBody())) {
      return renderError("竞价响应内容为空！");
    }

    UpPlatResponse upPlatResponse = null;

    try {
      upPlatResponse = JSONObject.parseObject(responseEntity.getBody(), UpPlatResponse.class);
    } catch (Exception e) {
      return renderError("竞价响应内容解析失败！");
    }

    if (upPlatRequest == null) {
      return renderError("竞价响应内容解析失败！");
    }

    BidResponseDto bidResponseDto = new BidResponseDto();
    bidResponseDto.setHttpCode(responseEntity.getStatusCodeValue());
    bidResponseDto.setUpPlatResponse(upPlatResponse);
    bidResponseDto.setErrorMessage(new ArrayList<>());

    //执行响应结果校验
    //1.commonCheck
    List<String> commonCheckError = checkResponseService.commonCheck(upPlatResponse);
    if (CollectionUtils.isNotEmpty(commonCheckError)) {
      bidResponseDto.getErrorMessage().addAll(commonCheckError);

      return renderSuccess(bidResponseDto);
    }

    Integer instl = upPlatRequest.imp.get(0).instl;

    //banner check
    if (Arrays.asList(1, 2, 3).contains(instl)) {
      List<String> bannerCheckError = checkResponseService.bannerAdCheck(instl, upPlatResponse);

      if (CollectionUtils.isNotEmpty(bannerCheckError)) {
        bidResponseDto.getErrorMessage().addAll(bannerCheckError);

        return renderSuccess(bidResponseDto);
      }
    } else if (instl == 14) {//video check
      List<String> videoCheckError = checkResponseService.videoAdCheck(upPlatResponse);

      if (CollectionUtils.isNotEmpty(videoCheckError)) {
        bidResponseDto.getErrorMessage().addAll(videoCheckError);

        return renderSuccess(bidResponseDto);
      } else {//native check
        List<String> nativeCheckError = checkResponseService.nativeAdCheck(upPlatResponse);

        if (CollectionUtils.isNotEmpty(nativeCheckError)) {
          bidResponseDto.getErrorMessage().addAll(nativeCheckError);

          return renderSuccess(nativeCheckError);
        }
      }
    }

    //check win notice

    return renderSuccess(bidResponseDto);
  }
}
