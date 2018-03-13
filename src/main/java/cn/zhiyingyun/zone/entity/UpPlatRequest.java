package cn.zhiyingyun.zone.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UpPlatRequest {
  private static final Integer HTTP_URL = 0;//secure

  public String id;
  public List<Impression> imp;
  public App app;
  public Device device;
  public User user;
  public RequestExt ext;

  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  public static class Impression {
    public String id;
    public List<Integer> bindustry; // 禁投行业列表
    public List<Integer> bmaterial; // 禁投素材列表
    public String tagid;
    public Banner banner;
    @JSONField(name = "native")
    public Native native$;
    public Map<Integer, List<Native>> vars_native;
    public AdWords adWords;
    public Video video;
    public SemanticBo semantic;
    public Integer instl;//1:横幅,2:全屏,3:插屏,5:音频,6:视频,7:焦点图,8:信息流,9:内容流,10:动态消息,12:图片墙,13:三图一文,15:图标广告
    public Double bidfloor;
    public Boolean itavoiceable; // N 是否支持语义互动广告 true支持 其他值不支持
    public String cur;
    public Pmp pmp;
    public Integer is_support_deeplink; //是否支持deeplink 1：支持 0：不支持
    public ImpressionExt ext;
    public Integer secure = HTTP_URL;//本次请求期望物料中所有的 url 的网络协议类型是 http 还是 https取值范围， 0:httpurl,1:https url， 默认为 0(http url)
    public int is_downloadable = 1; //本次请求是否支持下载类广告,0不支持,1支持  默认支持

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Banner extends BasicReqPros {
      public Integer type;
      public Integer w;
      public Integer h;
      public List<Integer> matypes; // 1:json 2:html
      public BannerExt ext;

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class BannerExt {
      }
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Native extends BasicReqPros {
      public Title title;
      public Desc desc;
      public Icon icon;
      public Img img;
      public NativeExt ext;

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Title {
        public Integer len;
        public TitleExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class TitleExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Desc {
        public Integer len;
        public DescExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class DescExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Icon {
        public Integer w;
        public Integer h;
        public IconExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class IconExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Img {
        public Integer w;
        public Integer h;
        public ImgExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class ImgExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class NativeExt {
      }
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class BasicReqPros {
      public List<Integer> formats; // 图片素材格式
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class AdWords {
      public Title title;
      public Desc desc;
      public Icon icon;
      public AdWordsExt ext;

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Title {
        public Integer len;
        public TitleExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class TitleExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Desc {
        public Integer len;
        public DescExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class DescExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Icon {
        public Integer w;
        public Integer h;
        public IconExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class IconExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class AdWordsExt {
      }
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Video {
      public Integer protocol;
      public Integer w;
      public Integer h;
      public Integer minduration;
      public Integer maxduration;
      public Integer linearity;
      public Integer skip;
      public Integer maxsize;
      public Set<Integer> formats;
      public List<Size> sizes;


      public VideoExt ext;

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class VideoExt {
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Size {
        private Integer w;
        private Integer h;

        public Size(Integer w, Integer h) {
          this.w = w;
          this.h = h;
        }
      }
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Pmp {
      public List<Deal> deals;
      public Boolean monopolistic; // 标识此次请求的广告位是否为兜底广告位

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class Deal {
        public String id;
        public double bidfloor;
      }
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class ImpressionExt {
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public class SemanticBo {

      public List<String> texts; // Y 听写结果，同一语义场景前几轮会话的包含的语义槽位值（如果有normValue取normValue,没有normValue取value）
      public String answerText; // N 开放语义回答结果
      public String data; // N  开放语义从数据源获取的数据结果，不同的场景对应的数据源格式不同 json串
      public String appId; // Y AIUI appId
      public String userId; // Y AIUI userId
      public String service; // Y 语义service
      public List<SemanticSlot> semanticSlots; // N 语义槽及对应实体名称（实体名称为实体真实名称不是别名），参见附录 广告语义槽说明
      public String intent; // Y 	语义意图，广告技能语义意图参见附录 语义意图
      public String sceneVendor; // N 技能的全局唯一名称，一般为vendor.name,vendor不存在时默认为IFLYTEK提供的开放技能
      public String dspExt; // dsp私有数据


      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public class SemanticSlot {
        public String name; // Y 语义槽名称
        public String value; // N 语义槽位值 与normValue必有一个
        public String normValue; // N 语义槽位正常值 与value必有一个 优先读取该值
      }

    }
  }

  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  public static class App {
    public String id;
    public String name;
    public String bundle;
    public String version;      // 上行请求添加app版本信息
    public List<String> cat;
    public AppExt ext;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class AppExt {
    }
  }

  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  public static class Device {
    public Integer w;
    public Integer h;
    public String ua;
    public String ip;
    public Geo geo;
    public String did;
    public String iflydid;
    public String didmd5;
    public String didsha1;
    public String dpid;
    public String dpidmd5;
    public String dpidsha1;
    public String mac;
    public String macmd5;
    public String macsha1;
    public String ifa;
    public String make;
    public String model;
    public String os;
    public String osv;
    public String carrier;
    public String language;
    public Integer js;
    public Integer connectiontype;
    public Integer devicetype;
    public DeviceExt ext;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Geo {
      public Double lat;
      public Double lon;
      public GeoExt ext;

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class GeoExt {
      }
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class DeviceExt {
    }
  }

  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  public static class User {
    public List<String> tags;
    public UserExt ext;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class UserExt {
      public List<String> app_type_list;
      public Integer vip_install_statu; // 0-未安装 1-已安装
      public String media_user_id;
    }
  }

  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  public static class RequestExt {
  }

  //
  private static final Logger log = LoggerFactory.getLogger(UpPlatRequest.class);

  @Override
  public String toString() {
    try {
      return JSONObject.toJSONString(this);
    } catch (Exception e) {
      log.debug("Exception = {}", e.getMessage());
      return null;
    }
  }
}