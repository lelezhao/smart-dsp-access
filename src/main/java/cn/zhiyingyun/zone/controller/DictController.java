package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.ComboBox;
import cn.zhiyingyun.zone.common.DropDownBox;
import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.common.LinkageDropDownBox;
import cn.zhiyingyun.zone.domain.Dict;
import cn.zhiyingyun.zone.service.IDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joy.Zhao on 2017/3/23.
 */
@RestController
@RequestMapping(value = "/common/dict", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DictController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(DictController.class);

  @Autowired
  private IDictService customDictService;

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult all() {
    List<Dict> customDicts = (List<Dict>) customDictService.findAll();

    Map<String, String> dictMap = convertCustomDictListToMap(customDicts);

    return renderSuccess(dictMap);

  }

  @GetMapping(value = "/type", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getAllTypeCode() {
    List<String> typeCodes = customDictService.findDistinctTypeCode();

    return renderSuccess(typeCodes);
  }

  @GetMapping(value = "/type/{typeCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getDictListByTypeCode(@PathVariable(value = "typeCode") String typeCode) {
    Sort sort = new Sort(Sort.Direction.ASC, "order");
    List<Dict> customDicts = customDictService.findByTypeCode(typeCode, sort);

    List<ComboBox> comboBoxList = new ArrayList<>();
    for (Dict customDict : customDicts) {
      ComboBox comboBox = new ComboBox();
      comboBox.setText(customDict.getValue());
      comboBox.setValue(customDict.getKey());

      comboBoxList.add(comboBox);
    }

    return renderSuccess(comboBoxList);
  }


  @GetMapping(value = "/type/{typeCode}/{pkey}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getDictChildListByTypeCodeAndPkey(@PathVariable(value = "typeCode") String typeCode, @PathVariable(value = "pkey") String pkey) {
    Sort sort = new Sort(Sort.Direction.ASC, "order");

    List<Dict> customDicts = customDictService.findByTypeCodeAndPkey(typeCode, pkey, sort);

    List<ComboBox> comboBoxList = new ArrayList<>();
    for (Dict customDict : customDicts) {
      ComboBox comboBox = new ComboBox();
      comboBox.setText(customDict.getValue());
      comboBox.setValue(customDict.getKey());

      comboBoxList.add(comboBox);
    }

    return renderSuccess(comboBoxList);
  }

  @GetMapping(value = "/linkage/{typeCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getlinkageBox(@PathVariable(value = "typeCode") String typeCode) {
    Sort sort = new Sort(Sort.Direction.ASC, "order");

    List<Dict> customDicts = customDictService.findByTypeCodeAndPkey(typeCode, "0", sort);

    List<LinkageDropDownBox> linkageDropDownBoxList = new ArrayList<>();

    for (Dict customDict : customDicts) {
      LinkageDropDownBox linkageDropDownBox = new LinkageDropDownBox();

      linkageDropDownBox.setLabel(customDict.getValue());
      linkageDropDownBox.setText(customDict.getValue());
      linkageDropDownBox.setValue(customDict.getKey());

      linkageDropDownBoxList.add(linkageDropDownBox);
    }

    List<Dict> secondDict = customDictService.getChildrenTypeDict(typeCode);
    for (Dict dict : secondDict) {
      for (LinkageDropDownBox dropDownBox : linkageDropDownBoxList) {
        if (dict.getpKey().equals(dropDownBox.getValue())) {
          DropDownBox comboBox = new DropDownBox();
          comboBox.setLabel(dict.getValue());
          comboBox.setText(dict.getValue());
          comboBox.setValue(dict.getKey());

          dropDownBox.getChildren().add(comboBox);
        }
      }
    }

    return renderSuccess(linkageDropDownBoxList);
  }


  @GetMapping(value = "/{key}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getDictNameByKey(@PathVariable(value = "key") String key) {
    String name = customDictService.findDictNameByKey(key);

    return renderSuccess(name);
  }

  @GetMapping(value = "/industry", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getIndustry() {
    List<Dict> customDicts = customDictService.getIndustryDict();

    List<ComboBox> comboBoxList = new ArrayList<>();
    for (Dict customDict : customDicts) {
      ComboBox comboBox = new ComboBox();
      comboBox.setText(customDict.getValue());
      comboBox.setValue(customDict.getKey());

      comboBoxList.add(comboBox);
    }

    return renderSuccess(comboBoxList);
  }

  @GetMapping(value = "/industry/{key}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getIndustry(@PathVariable(value = "key") String key) {

    List<Dict> customDicts = customDictService.getIndustryDictByPkey(key);

    List<ComboBox> comboBoxList = new ArrayList<>();
    for (Dict customDict : customDicts) {
      ComboBox comboBox = new ComboBox();
      comboBox.setText(customDict.getValue());
      comboBox.setValue(customDict.getKey());

      comboBoxList.add(comboBox);
    }

    return renderSuccess(comboBoxList);
  }

  @GetMapping(value = "/industries", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonResult getIndustries() {
    List<Dict> customDicts = customDictService.getIndustryDict();

    List<LinkageDropDownBox> linkageDropDownBoxList = new ArrayList<>();

    for (Dict customDict : customDicts) {
      LinkageDropDownBox linkageDropDownBox = new LinkageDropDownBox();

      linkageDropDownBox.setLabel(customDict.getValue());
      linkageDropDownBox.setText(customDict.getValue());
      linkageDropDownBox.setValue(customDict.getKey());

      linkageDropDownBoxList.add(linkageDropDownBox);
    }

    List<Dict> secondDict = customDictService.getAllSecondIndustry();
    for (Dict dict : secondDict) {
      for (LinkageDropDownBox dropDownBox : linkageDropDownBoxList) {
        if (dict.getpKey().equals(dropDownBox.getValue())) {
          DropDownBox comboBox = new DropDownBox();
          comboBox.setLabel(dict.getValue());
          comboBox.setText(dict.getValue());
          comboBox.setValue(dict.getKey());

          dropDownBox.getChildren().add(comboBox);
        }
      }
    }

    return renderSuccess(linkageDropDownBoxList);
  }

  private Map convertCustomDictListToMap(List<Dict> customDictList) {
    Map<String, String> dictMap = new HashMap<>();
    for (Dict customDict : customDictList) {
      dictMap.put(customDict.getKey(), customDict.getValue());
    }

    return dictMap;
  }

}
