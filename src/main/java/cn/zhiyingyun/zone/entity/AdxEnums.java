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
}
