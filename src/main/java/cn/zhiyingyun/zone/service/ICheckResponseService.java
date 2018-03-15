package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.entity.UpPlatResponse;

import java.util.List;

public interface ICheckResponseService {
  List<String> commonCheck(UpPlatResponse response);

  List<String> bannerAdCheck(Integer instl, UpPlatResponse respons);

  List<String> nativeAdCheck(UpPlatResponse response);

  List<String> videoAdCheck(UpPlatResponse response);

  List<String> winNoticeCheck(UpPlatResponse response);
}
