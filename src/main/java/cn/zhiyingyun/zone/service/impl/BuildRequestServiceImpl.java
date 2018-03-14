package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.entity.UpPlatRequest;
import cn.zhiyingyun.zone.service.IBuildRequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BuildRequestServiceImpl implements IBuildRequestService {

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

    if (slotType.equals("s_t-banner")) {
      impression.instl = 1;
      banner.type = 1;
      banner.w = 640;
      banner.h = 100;

      impression.tagid = "58349F1F392F79EE10715232FB249531";
    } else if (slotType.equals("s_t-full")) {
      impression.instl = 2;
      banner.type = 2;
      banner.w = 640;
      banner.h = 1136;

      impression.tagid = "58349F1F392F79EE10715232FB249531";
    } else if (slotType.equals("s_t-interstitial")) {
      impression.instl = 3;
      banner.type = 3;
      banner.w = 600;
      banner.h = 500;

      impression.tagid = "58349F1F392F79EE10715232FB249531";
    }

    banner.matypes = Arrays.asList(1, 2);

    impression.banner = banner;

    return impression;
  }

  public UpPlatRequest.Impression buildNativeImp(boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure) {

    UpPlatRequest.Impression impression = new UpPlatRequest.Impression();

    impression.tagid = "58349F1F392F79EE10715232FB249531";
    impression.instl = 1;

    buildCommonImp(impression, isSupportDeepLink, isSupportDownload, isSecure);

    UpPlatRequest.Impression.Native nativeAd = new UpPlatRequest.Impression.Native();

    nativeAd.title = new UpPlatRequest.Impression.Native.Title();
    nativeAd.title.len = 30;
    nativeAd.img = new UpPlatRequest.Impression.Native.Img();
    nativeAd.img.w = 640;
    nativeAd.img.h = 960;
    nativeAd.desc = new UpPlatRequest.Impression.Native.Desc();
    nativeAd.desc.len = 30;

    impression.native$ = nativeAd;

    return impression;
  }

  public UpPlatRequest.Impression buildVideoImp(boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure) {
    UpPlatRequest.Impression impression = new UpPlatRequest.Impression();

    impression.tagid = "58349F1F392F79EE10715232FB249531";
    impression.instl = 1;

    buildCommonImp(impression, isSupportDeepLink, isSupportDownload, isSecure);

    UpPlatRequest.Impression.Video video = new UpPlatRequest.Impression.Video();

    video.w = 600;
    video.h = 500;
    video.protocol = 101;
    video.minduration = 5000;
    video.maxduration = 15000;
    video.linearity = 1;

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
    } else if (os.equals("o_t-ios")) {//ios
      device.os = "ios";
      device.ifa = "1E2DFA89-496A-47FD-9941-DF1FC4E6484A";
      device.dpid = "";
      device.dpidmd5 = "";
      device.dpidsha1 = "";
    }

    device.mac = "9c:b2:b2:b4:cc:c6";
    device.macmd5 = "6018660532b77322ef8c327513ac0d4b";
    device.macsha1 = "2eb4cb2f061105179b9707c8450a605fca65b45a";
    device.make = "HUAWEI";
    device.model = "ATH-AL00";
    device.osv = "6.0.1";
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
}
