package cn.zhiyingyun.zone.entity;

public class AdxEnums {

  public interface Lattr {
    int WEB = 1;
    int DOWNLOAD = 2;
    int APPSTORE = 3;
    int GTD_DOWNLOAD = 4;
  }


  public interface MType {
    int PLAIN_TEXT = 1;
    int PLAIN_IMAGE = 2;
    int IMAGE_TEXT = 3;
    int HTML = 4;
    int HTML_SNIPPET = 5;
  }

  public interface ControlPriceNotice {
    int SERVER_NOTICE = 1;                 // 只在服务端（获胜通知）中通知价格
    int CLIENT_NOTICE = 2;                 // 只在客户端（展示监控）中通知价格
    int SERVER_AND_CLIENT = 3;             // 既在服务端（获胜通知）又在客户端（展示监控）
    int SERVER_AND_CLIENT_AND_CLICK = 4;   // 在服务端（获胜通知）在客户端（展示监控）在点击广告的时候都需要上报成交价格
  }

  public interface AdConstants {
    int ADUNIT_TYPE_ID_BANNER = 1;
    int ADUNIT_TYPE_ID_FULL = 2;
    int ADUNIT_TYPE_ID_INTERSTITIAL = 3;
    int ADUNIT_TYPE_ID_AUDIO = 5;
    int ADUNIT_TYPE_ID_FOCUS_IMAGE = 7;
    int ADUNIT_TYPE_ID_INFORMATION_STREAM = 8;
    int ADUNIT_TYPE_ID_CONTENT_FLOW = 9;
    int ADUNIT_TYPE_ID_DYNAMIC_MSG = 10;
    int ADUNIT_TYPE_ID_NATIVE_GENERAL = 11;
    int ADUNIT_TYPE_ID_ONE_IMAGE = 12;
    int ADUNIT_TYPE_ID_THREEIMAGES_ONETEXT = 13;
    int ADUNIT_TYPE_ID_NATIVE_VIDEO = 14; //原生视频
    int ADUNIT_TYPE_ID_NATIVE_ICON = 15; //新增图标广告
    int ADUNIT_TYPE_ID_KEYWORD = 16; //新增关键词广告
    int ADUNIT_TYPE_ID_NATIVE_VARIOUS = 17; //新增多样原生
    int ADUNIT_TYPE_ID_REWARD_VIDEO = 18; //激励视频
    int ADUNIT_TYPE_ID_PATCH_VIDEO = 19; //贴片视频
    int ADUNIT_TYPE_ID_AIUI_HEADAD = 20; //冠名广告
    int ADUNIT_TYPE_ID_AIUI_ITAAD = 21; //交互广告
    int ADUNIT_TYPE_ID_MAX_NUMBER = 21; //记录广告类型id最大值，目前流量定投会用到
  }
}
