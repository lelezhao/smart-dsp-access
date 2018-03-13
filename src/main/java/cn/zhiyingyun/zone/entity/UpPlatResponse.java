package cn.zhiyingyun.zone.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UpPlatResponse {
  public String id;
  public String bidid;
  public String cur;
  public List<SeatBid> seatbid;
  public ResponseExt ext;

  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  public static class SeatBid {
    public List<Bid> bid;
    public SeatBidExt ext;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Bid {
      public String impid;
      public Double price;
      public Integer ptype; //  N  1:兜底订单素材 2:优先拿量订单素材 其他值或者为空：普通订单素材
      public Integer ordtype; // N 1:测试素材订单 2:效果验证订单
      public ItaVoiceInfo ita_voice_info; // N
      public String nurl;
      public BannerAd banner_ad;
      public NativeAd native_ad;
      public AdWords adWords_ad;
      public VideoAd video_ad;
      public AiuiAdBo aiui_ad;
      public Integer lattr;
      public String dealid;
      public BidExt ext;

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public class AiuiAdBo {

        public int type; // 1：冠名广告素材 2：交互广告answer素材 3：交互广告交互部分

        public TitleAd titleAd; // type = 1 必填 冠名广告素材

        public InteractionAnswer itaAnswer; // type = 2 必填 交互广告回答结果类

        public ItaContent itaContent; // type = 3 必填 交互内容类

        // 冠名广告素材 type = 1 必填
        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public class TitleAd extends AiuiBasicPros {
          public String prefixText; // N 冠名广告前缀文本，和suffix_text必有一个，或者两个都有
          public String suffixText; // N 冠名广告后缀文本，和prefix_text必有一个，或者两个都有
          public String fullText; //  N 拼接之后的完整文本（将语义answer.text和冠名广告进行拼接）
        }

        // 交互广告回答结果类 type = 2 必填
        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public class InteractionAnswer extends AiuiBasicPros {
          public String product; // Y 推荐产品名称
          public String text; // Y 交互广告播报文本
          public Integer orderId; // Y 营销云订单id
          public String dspExt; // Y dsp私有数据
        }

        // 交互内容类 type = 3 必填
        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public class ItaContent {
          public List<String> reportUrls; // Y 用户信息上报接口地址
          public List<Content> contents; // Y 交互内容，按照顺序依次播报，最后一步收集用户信息
          public Integer orderId; // Y 营销云的订单编码
          public String dspExt; // Y DSP私有数据
        }

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public class Content extends AiuiBasicPros {
          public String text; // Y 交互广告播报文本
          public List<String> hopeAnswer; // Y 期望回答，匹配该回答进行下一步，正则表达式
          public String key; // Y 本次提问问题对应的key，如果提问是“请说出你的电话号码？”，key为“电话号码”
          public String jsonKey; // Y 本次提问问题对应的json key
        }

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public class AiuiBasicPros {
          public List<String> beginImprs; // Y 播报开始监控 需要包含价格宏
          public List<String> endImprs; // Y 播报结束监控 需要包含价格宏
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class BannerAd extends BasicAdProperty {
        public Integer mtype;
        public String title;
        public String desc;
        public String image_url;
        public String html;
        public String landing;
        public String deep_link;
        public Integer w;
        public Integer h;
        public List<String> impress;
        public List<String> click;
        public String package_name;
        public String app_name;//应用名
        public String app_icon;     // 下载类广告应用icon   ----------------  2017/7/25 whb
        public String app_version;  // 下载类广告应用版本
        public BannerAdExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class BannerAdExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class NativeAd extends BasicAdProperty {
        public Integer hit_instl;
        public Integer width;
        public Integer height;
        public String title;
        public String desc;
        public String icon;
        public String img;
        public String landing;
        public String deep_link;
        public List<String> imptrackers;
        public List<String> clicktrackers;
        public String package_name;
        public String app_name;//应用名
        public String app_icon;     // 下载类广告应用icon   ----------------  2017/7/25 whb
        public String app_version;  // 下载类广告应用版本
        public List<String> img_urls;
        public NativeAdExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class NativeAdExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class AdWords extends BasicAdProperty {
        public String title;
        public String desc;
        public String icon;
        public String landing;
        public String deep_link;
        public List<String> imptrackers;
        public List<String> clicktrackers;
        public String package_name;
        public String app_name;//应用名
        public List<String> img_urls;
        public NativeAdExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class NativeAdExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class VideoAd extends BasicAdProperty {
        public String src;
        public Integer w; // 视频宽，单位 pixel, 激励视频、贴片视频、原生视频必填
        public Integer h; // 视频高，单位 pixel，激励视频、贴片视频、原生视频必填
        public Integer size; // 视频体积，单位KB
        public Integer bitrate; // 视频码率，单位kbps  激励视频、贴片视频、原生视频必填
        public Integer duration;
        public Integer format;
        public Integer cover_type; // 覆盖页类型 1：img 2:html
        public String start_cover; //N 视频播放开始覆盖页
        public String complete_cover;//N 视频播放完成覆盖页
        public String landing;
        public String deep_link;
        public String title;
        public List<String> imptrackers; // 必填
        public List<String> starttrackers;
        public List<String> completetrackers;
        public List<String> clicktrackers;
        /**
         * 视频播放检测事件
         * creativeView、firstQuartile、midPoint、thirdQuartile、pause、resume、skip、mute、unmute、closeLinear
         */
        public Map<String, List<String>> eventtrackers;
        public String package_name;
        public String app_name;//应用名
        public String app_icon;     // 下载类广告应用icon   ----------------  2017/7/25 whb
        public String app_version;  // 下载类广告应用版本
        public VideoAdExt ext;

        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class VideoAdExt {
        }
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class BasicAdProperty {
        String is_marked;
        String ad_source_mark;
        List<String> inst_downstart_url; // 下载 开始上报
        List<String> inst_downsucc_url; // 下载 完成上报
        List<String> inst_installstart_url; // 安装 开始上报
        List<String> inst_installsucc_url; // 安装 完成上报
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      static class ItaVoiceInfo {
        String app_id; // Y 应用id
        String lib_id; // Y 语音库id
        String topic_id; // N 话题id
        String app_id_key; // N 应用key
        List<String> voice_imprs; // N 语义监控和语音合成监控
      }

      @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
      public static class BidExt {
      }
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class SeatBidExt {
    }
  }

  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  public static class ResponseExt {
  }


  //
  private static final Logger log = LoggerFactory.getLogger(UpPlatResponse.class);

  public static UpPlatResponse fromString(String response) {
    try {

      return JSONObject.parseObject(response, UpPlatResponse.class);
    } catch (Exception e) {
      log.debug("Exception = {}", e.getMessage());
      return null;
    }
  }
}