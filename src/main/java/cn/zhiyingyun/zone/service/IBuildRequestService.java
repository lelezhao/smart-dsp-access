package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.entity.UpPlatRequest;

public interface IBuildRequestService {
  UpPlatRequest.Impression buildBannerImp(String slotType, boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure);

  UpPlatRequest.Impression buildNativeImp(boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure);

  UpPlatRequest.Impression buildVideoImp(boolean isSupportDeepLink, boolean isSupportDownload, boolean isSecure);

  UpPlatRequest.Device buildDevice(String os);

  UpPlatRequest.App buildApp();

  UpPlatRequest.Impression.Pmp buildPmp(String dealId, Double bidFloor);

  UpPlatRequest.User buildUser();
}
