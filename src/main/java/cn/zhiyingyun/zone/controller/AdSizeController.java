package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.ComboBox;
import cn.zhiyingyun.zone.common.JsonResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ad/size", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdSizeController extends BaseController {

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult adSize(String slotType) {

    if (slotType.equals("a_a-banner")) {

      List<ComboBox> comboBoxList = new ArrayList<>();

      ComboBox comboBox = new ComboBox();
      comboBox.setText("640*100");
      comboBox.setValue("640*100");

      comboBoxList.add(comboBox);

      return renderSuccess(comboBoxList);
    }
    return renderSuccess();
  }
}
