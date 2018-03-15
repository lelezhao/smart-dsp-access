package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.ComboBox;
import cn.zhiyingyun.zone.common.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/slot", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdSizeController extends BaseController {

  private String bannerSizes = "640*100";
  private String fullSizes = "640*1136";
  private String interstitialSizes = "600*500";
  private String videoSizes = "640*360,640*960,720*1280,960*640,1280*720";
  private String focusSizes = "960*640,480*320,640*960,600*500,1280*720,600*200,720*240,640*200,320*117,640*250,100*100,300*300,720*216,180*140,480*240,640*320,1080*540,1080*270,1080*270,640*160,720*1280,1080*1920";
  private String feedsSizes = "960*640,480*320,640*960,600*500,1280*720,600*200,720*240,640*200,320*117,640*250,100*100,300*300,720*216,180*140,480*240,640*320,1080*540,1080*270,1080*270,640*160,720*1280,1080*1920";
  private String imagewallSizes = "960*640,480*320,640*960,600*500,1280*720,600*200,720*240,640*200,320*117,640*250,100*100,300*300,720*216,180*140,480*240,640*320,1080*540,1080*270,1080*270,640*160,720*1280,1080*1920";
  private String iconSizes = "60*60,100*100,50*50,120*120,150*150";

  @GetMapping(value = "/size", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult slotSize(String slotType) {
    if (StringUtils.isBlank(slotType)) {
      return renderError("请选择广告位类型");
    }

    List<ComboBox> comboBoxList = new ArrayList<>();

    switch (slotType) {
      case "s_t-banner":
        for (String size : bannerSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      case "s_t-full":
        for (String size : fullSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      case "s_t-interstitial":
        for (String size : interstitialSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      case "s_t-video":
        for (String size : videoSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      case "s_t-focus":
        for (String size : focusSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      case "s_t-feeds":
        for (String size : feedsSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      case "s_t-imagewall":
        for (String size : imagewallSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      case "s_t-icon":
        for (String size : iconSizes.split(",")) {
          ComboBox comboBox = new ComboBox(size);
          comboBoxList.add(comboBox);
        }
        break;
      default:
        return renderError("选择广告位类型不存在！");
    }
    return renderSuccess(comboBoxList);
  }
}
