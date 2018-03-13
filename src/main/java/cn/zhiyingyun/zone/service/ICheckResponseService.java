package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.entity.UpPlatResponse;

public interface ICheckResponseService {
  void commonCheck(UpPlatResponse response);

  void bannerAdCheck(UpPlatResponse respons);

  void nativeAdCheck();

  void videoAdCheck();

  void winNoticeCheck();
}
