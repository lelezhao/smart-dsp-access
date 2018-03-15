package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.controller.LoginController;
import cn.zhiyingyun.zone.entity.SizePair;
import cn.zhiyingyun.zone.entity.UpPlatRequest;
import cn.zhiyingyun.zone.service.IBuildRequestService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BuildRequestServiceImpl implements IBuildRequestService {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  private UpPlatRequest.Impression buildCommonImp(UpPlatRequest.Impression impression, boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure) {
    impression.id = UUID.randomUUID().toString();

    impression.bidfloor = 0.01;
    if (isSupportDeepLink) {
      impression.is_support_deeplink = 1;
    } else {
      impression.is_support_deeplink = 0;
    }

    if (isSupportDownload) {
      impression.is_downloadable = 1;
    } else {
      impression.is_downloadable = 0;
    }

    if (isSecure) {
      impression.secure = 1;
    } else {
      impression.secure = 0;
    }

    return impression;
  }

  public UpPlatRequest.Impression buildBannerImp(String slotType, boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure) {
    UpPlatRequest.Impression impression = new UpPlatRequest.Impression();

    buildCommonImp(impression, isSupportDeepLink, isSupportDownload, isSecure);

    UpPlatRequest.Impression.Banner banner = new UpPlatRequest.Impression.Banner();

    impression.instl = getInstlBySlotType(slotType);

    if (slotType.equals("s_t-banner")) {
      banner.type = 1;
      banner.w = 640;
      banner.h = 100;

      impression.tagid = "58349F1F392F79EE10715232FB249531";
    } else if (slotType.equals("s_t-full")) {
      banner.type = 2;
      banner.w = 640;
      banner.h = 1136;

      impression.tagid = "58349F1F392F79EE10715232FB249531";
    } else if (slotType.equals("s_t-interstitial")) {
      banner.type = 3;
      banner.w = 600;
      banner.h = 500;

      impression.tagid = "58349F1F392F79EE10715232FB249531";
    }

    banner.matypes = Arrays.asList(1, 2);

    impression.banner = banner;

    return impression;
  }

  public UpPlatRequest.Impression buildNativeImp(String slotType, String slotSize, boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure) {

    UpPlatRequest.Impression impression = new UpPlatRequest.Impression();

    impression.tagid = "58349F1F392F79EE10715232FB249531";
    impression.instl = getInstlBySlotType(slotType);

    buildCommonImp(impression, isSupportDeepLink, isSupportDownload, isSecure);

    UpPlatRequest.Impression.Native nativeAd = new UpPlatRequest.Impression.Native();

    if (Arrays.asList(7, 8, 13).contains(impression.instl)) {
      nativeAd.title = new UpPlatRequest.Impression.Native.Title();
      nativeAd.title.len = 30;
    }

    if (impression.instl == 8) {
      nativeAd.desc = new UpPlatRequest.Impression.Native.Desc();
      nativeAd.desc.len = 30;
    }

    nativeAd.img = new UpPlatRequest.Impression.Native.Img();

    SizePair sizePair = parseSize(slotSize);
    if (sizePair != null) {
      nativeAd.img.w = sizePair.getW();
      nativeAd.img.h = sizePair.getH();
    }

    impression.native$ = nativeAd;

    return impression;
  }

  public UpPlatRequest.Impression buildVideoImp(String slotType, String slotSize, boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure) {
    UpPlatRequest.Impression impression = new UpPlatRequest.Impression();

    impression.tagid = "58349F1F392F79EE10715232FB249531";
    impression.instl = 14; //默认为原生视频

    buildCommonImp(impression, isSupportDeepLink, isSupportDownload, isSecure);

    UpPlatRequest.Impression.Video video = new UpPlatRequest.Impression.Video();

    SizePair sizePair = parseSize(slotSize);
    if (sizePair != null) {
      video.w = sizePair.getW();
      video.h = sizePair.getH();
    }

    video.protocol = 101;
    video.minduration = 0;
    video.maxduration = 15000;
    video.linearity = 1;

    video.sizes = new ArrayList<>();
    video.sizes.add(new UpPlatRequest.Impression.Video.Size(640, 360));
    video.sizes.add(new UpPlatRequest.Impression.Video.Size(640, 960));
    video.sizes.add(new UpPlatRequest.Impression.Video.Size(720, 1280));
    video.sizes.add(new UpPlatRequest.Impression.Video.Size(960, 640));
    video.sizes.add(new UpPlatRequest.Impression.Video.Size(1280, 720));

    impression.video = video;

    return impression;
  }

  public UpPlatRequest.Device buildDevice(String os) {
    UpPlatRequest.Device device = new UpPlatRequest.Device();
    device.w = 1080;
    device.h = 1776;
    device.ua = "Mozilla/5.0 (Linux; Android 6.0.1; ATH-AL00 Build/HONORATH-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36";
    device.ip = "14.30.158.152";
    device.geo = new UpPlatRequest.Device.Geo();
    device.geo.lat = 23.043595;
    device.geo.lon = 112.869539;
    //android
    if (os.equals("o_t-android")) {
      device.os = "android";
      device.did = "863546034395903";
      device.didmd5 = "5656cc117aa7fdff17383d7ae66f1514";
      device.didsha1 = "b889b1fff1c90838ef677bf7f1deeddac638a156";
      device.dpid = "7743974f06212051";
      device.dpidmd5 = "5656cc117aa7fdff17383d7ae66f1514";
      device.dpidsha1 = "0e7ba7f58fdde994a51abfd1c0f14d9f90b51d2d";

      device.make = "HUAWEI";
      device.model = "ATH-AL00";
      device.osv = "6.0.1";
    } else if (os.equals("o_t-ios")) {//ios
      device.os = "ios";
      device.ifa = "1E2DFA89-496A-47FD-9941-DF1FC4E6484A";
      device.dpid = "137891ec369c899b01cc3df6cad90360514827e7";
      device.dpidmd5 = "103493a52bbb76feac9a9f14e0612f21";
      device.dpidsha1 = "64c675b80be940d7dfaf84bf14c64e395f652319";

      device.make = "Apple";
      device.model = "iPhone";
      device.osv = "8.3";
    }

    device.mac = "9c:b2:b2:b4:cc:c6";
    device.macmd5 = "6018660532b77322ef8c327513ac0d4b";
    device.macsha1 = "2eb4cb2f061105179b9707c8450a605fca65b45a";

    device.carrier = "46003";
    device.language = "zh-CN";
    device.js = 1;
    device.connectiontype = 2;
    device.devicetype = 0;

    return device;
  }

  public UpPlatRequest.App buildApp() {
    UpPlatRequest.App app = new UpPlatRequest.App();
    app.id = "58ed8ca5";
    app.name = "测试 APP";
    app.bundle = "com.test";
    app.version = "1.0.0";
    app.cat = Arrays.asList("IAB5");

    return app;
  }

  public UpPlatRequest.Impression.Pmp buildPmp(String dealId, Double bidFloor) {

    UpPlatRequest.Impression.Pmp pmp = new UpPlatRequest.Impression.Pmp();

    List<UpPlatRequest.Impression.Pmp.Deal> deals = new ArrayList<>();

    UpPlatRequest.Impression.Pmp.Deal deal = new UpPlatRequest.Impression.Pmp.Deal();
    deal.id = dealId;
    deal.bidfloor = bidFloor;

    deals.add(deal);

    pmp.deals = deals;

    return pmp;
  }

  public UpPlatRequest.User buildUser() {
    return new UpPlatRequest.User();
  }

  private Integer getInstlBySlotType(String slotType) {
    if (StringUtils.isBlank(slotType)) {
      return null;
    }

    switch (slotType) {
      case "s_t-banner":
        return 1;
      case "s_t-full":
        return 2;
      case "s_t-interstitial":
        return 3;
      case "s_t-video":
        return 19;
      case "s_t-focus":
        return 7;
      case "s_t-feeds":
        return 8;
      case "s_t-imagewall": //就是一图
        return 12;
      case "s_t-icon":
        return 15;
      default:
        return 0;
    }
  }

  public static SizePair parseSize(String size) {
    try {
      if (StringUtils.isBlank(size)) {
        return null;
      }

      String[] sizes = size.trim().split("\\*");
      if (sizes.length != 2) {
        return null;
      }

      int w = Integer.valueOf(sizes[0].trim());
      int h = Integer.valueOf(sizes[1].trim());

      return new SizePair(w, h);
    } catch (Exception e) {
      logger.info("parseSize(String size): exception = " + e.getMessage());
      return null;
    }
  }
}
