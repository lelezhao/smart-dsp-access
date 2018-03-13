package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.entity.UpPlatResponse;

public interface ICheckResponseService {
  void commonCheck(UpPlatResponse response);

  void bannerAdCheck(UpPlatResponse respons);

  void nativeAdCheck(UpPlatResponse response);

  void videoAdCheck(UpPlatResponse response);

  void winNoticeCheck(UpPlatResponse response);
}
