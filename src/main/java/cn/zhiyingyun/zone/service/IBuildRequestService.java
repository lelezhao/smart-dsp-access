package cn.zhiyingyun.zone.service;

public interface IBuildRequestService {
  public void buildBannerImp(boolean isSupportDeepLink, boolean isDowmloadable, int secure);

  public void buildNativeImp(boolean isSupportDeepLink, boolean isDowmloadable, int secure);

  public void buildVideoImp(boolean isSupportDeepLink, boolean isDowmloadable, int secure);

  public void buildDevice(String carrier, Integer connnectionType, String os);

  public void buildApp();

  public void buildPmp();

  public void buildUser();
}
