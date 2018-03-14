package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.entity.AdxEnums;
import cn.zhiyingyun.zone.entity.UpPlatResponse;
import cn.zhiyingyun.zone.service.ICheckResponseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckResponseServiceImpl implements ICheckResponseService {

  private static final Logger logger = LoggerFactory.getLogger(CheckResponseServiceImpl.class);

  private static final String AUCTION_PRICE = "${AUCTION_PRICE}";

  @Override
  public void commonCheck(UpPlatResponse response) {

    /*
    （2）响应为空
（3）响应内容参数错误

（4）seatbid为空
（5）bid为空
（6）若请求为pdb交易，响应中dealid为空或与请求中不一致
（7）出价为空
（9）下发了下载类广告,但媒体不支持
    * */
    if (response == null) {
      logger.debug("response转UplatResponse返回null,sid={}");
    }
    if (response.seatbid == null || response.seatbid.size() == 0) {
      logger.debug("seatbid为空,sid={}");
    }
    if (response.seatbid.get(0).bid == null || response.seatbid.get(0).bid.size() == 0) {
      logger.debug("bid为空,sid={}");
    }

    UpPlatResponse.SeatBid.Bid bid = response.seatbid.get(0).bid.get(0);

    // 出价
    Double price = bid.price;
    if (price == null) {
      logger.debug("出价price为空,sid={}");
    }

    Integer lattr = bid.lattr;

    if (lattr != null && lattr == AdxEnums.Lattr.DOWNLOAD) {
      //todo 下发了下载类广告,但媒体不支持
    }

  }

  @Override
  public void bannerAdCheck(UpPlatResponse response) {
    /*（8）响应banner_ad为空
（10）mtype为空
(11)若下发html或html片段，banner中的html为空

（12）若下发html片段，controlHtmlSnippetType[1,5]单独的曝光监控或点击监控为空；
controlHtmlSnippetType[2,6]单独的曝光监控为空
controlHtmlSnippetType[3,7]单独的点击监控为空
controlHtmlSnippetType[4,8]不做监控校验
（13）若下发html，返回的完整的html物料没有讯飞曝光宏或者没有讯飞点击宏
    * */
    UpPlatResponse.SeatBid.Bid bid = response.seatbid.get(0).bid.get(0);

    UpPlatResponse.SeatBid.Bid.BannerAd banner = bid.banner_ad;
    if (banner == null) {
      logger.debug("banner_ad为空,sid={}");
    }

    Integer mtype = banner.mtype;

    if (mtype == null) {
      logger.debug("mtype为空,sid={}");
    }

    if (mtype == AdxEnums.MType.HTML || mtype == AdxEnums.MType.HTML_SNIPPET) {
      if (StringUtils.isBlank(banner.html)) {
        logger.debug("banner中的html为空！sid={}");
      }
    }

    //需要判断点击监控和曝光监控是否存在
    List<String> impUrls = new ArrayList<>();
    List<String> clickUrls = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(banner.impress)) {
      for (String imp : banner.impress) {
        if (StringUtils.isNotBlank(imp)) {
          impUrls.add(imp);
        }
      }
    }
    if (CollectionUtils.isNotEmpty(banner.click)) {
      for (String clickUrl : banner.click) {
        if (StringUtils.isNotBlank(clickUrl)) {
          clickUrls.add(clickUrl);
        }
      }
    }

    String title = banner.title;
    String desc = banner.desc;
    String image = banner.image_url;
    String landing = banner.landing;
    String deeplink = banner.deep_link;
    List<String> impress = banner.impress;
    List<String> click = banner.click;

    if (StringUtils.isBlank(landing)) {
      logger.debug("landing为空,sid={}");
    }

    // 展示监控不为空，且至少有一个要包含${AUCTION_PRICE}
    if (impress == null || impress.size() == 0) {
      logger.debug("曝光监控impress为空,sid={}");
    }
    // 点击监控不为空
    if (click == null || click.size() == 0) {
      logger.debug("点击监控click为空,sid={}");
    }

    if (mtype == AdxEnums.MType.PLAIN_TEXT) {
      if (1 != AdxEnums.AdConstants.ADUNIT_TYPE_ID_BANNER) {
        logger.debug("目前只有横幅支持文字链,sid={}");
      }
      if (StringUtils.isBlank(title)) {
        logger.debug("title为空,sid={}");
      }
    } else if (mtype == AdxEnums.MType.PLAIN_IMAGE) {
      if (StringUtils.isBlank(image)) {
        logger.debug("image url为空,sid={}");
      }
    } else if (mtype == AdxEnums.MType.IMAGE_TEXT) {
      if (2 == AdxEnums.AdConstants.ADUNIT_TYPE_ID_FULL) {
        logger.debug("全屏暂不支持图文素材,sid={}");
      }

      if (StringUtils.isBlank(title)) {
        logger.debug("title为空,sid={}");
      }

      if (StringUtils.isBlank(image)) {
        logger.debug("image url为空,sid={}");
      }
    } else {
      logger.debug("不支持的mtype = {},sid={}", mtype);
    }
  }

  @Override
  public void nativeAdCheck(UpPlatResponse response) {

    UpPlatResponse.SeatBid.Bid bid = response.seatbid.get(0).bid.get(0);

    UpPlatResponse.SeatBid.Bid.NativeAd nativeAd = bid.native_ad;
    if (nativeAd == null) {
      logger.debug("native_ad为空,sid={}");
    }
  }

  @Override
  public void videoAdCheck(UpPlatResponse response) {

    UpPlatResponse.SeatBid.Bid bid = response.seatbid.get(0).bid.get(0);

    UpPlatResponse.SeatBid.Bid.VideoAd videoAd = bid.video_ad;

    String src = videoAd.src;
    String landing = videoAd.landing;
    String deeplink = videoAd.deep_link;
    Integer dur = videoAd.duration;
    String startCover = videoAd.start_cover;
    String overCover = videoAd.complete_cover;
  }

  @Override
  public void winNoticeCheck(UpPlatResponse response) {
  }


  private boolean validAuctionPriceMacro(int controlPriceNotice, String nurl, List<String> imps, List<String> clicks) {
    switch (controlPriceNotice) {
      case AdxEnums.ControlPriceNotice.SERVER_NOTICE:
        if (!validNurlAuctionPrice(nurl)) {
          logger.warn("nurl does contains price macro!");
          return false;
        }
        break;
      case AdxEnums.ControlPriceNotice.CLIENT_NOTICE:
        if (!validTrackAuctionPrice(imps)) {
          logger.warn("imps does contains price macro!");
          return false;
        }
        break;
      case AdxEnums.ControlPriceNotice.SERVER_AND_CLIENT:
        if (!validNurlAuctionPrice(nurl)) {
          logger.warn("nurl does contains price macro!");
          return false;
        }
        if (!validTrackAuctionPrice(imps)) {
          logger.warn("imps does contains price macro!");
          return false;
        }
        break;
      case AdxEnums.ControlPriceNotice.SERVER_AND_CLIENT_AND_CLICK:
        if (!validNurlAuctionPrice(nurl)) {
          logger.warn("nurl does contains price macro!");
          return false;
        }
        if (!validTrackAuctionPrice(imps)) {
          logger.warn("imps does contains price macro!");
          return false;
        }
        if (!validTrackAuctionPrice(clicks)) {
          logger.warn("clicks does contains price macro!");
          return false;
        }
        break;
      default:
        logger.warn("controlPriceNotice = {} not valid", controlPriceNotice);
    }
    return true;
  }

  private boolean validNurlAuctionPrice(String nurl) {
    if (StringUtils.isBlank(nurl) || !nurl.contains(AUCTION_PRICE)) {
      logger.warn("controlPriceNotice = {},nurl is blank or nurl doesn't contains price macro");
      return false;
    }
    return true;
  }

  private boolean validTrackAuctionPrice(List<String> trackes) {
    if (CollectionUtils.isEmpty(trackes)) {
      logger.warn("trackes is empty");
      return false;
    }
    for (String tracker : trackes) {
      if (StringUtils.isNotBlank(tracker)) {
        if (tracker.contains(AUCTION_PRICE)) {
          return true;
        }
      }
    }
    return false;
  }
}
