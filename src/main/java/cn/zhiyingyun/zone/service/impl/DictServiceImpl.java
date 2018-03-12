package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.common.ComboBox;
import cn.zhiyingyun.zone.domain.Dict;
import cn.zhiyingyun.zone.repository.DictRepository;
import cn.zhiyingyun.zone.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joy.Zhao on 2017/5/15.
 */
@Service
@Transactional
public class DictServiceImpl extends BaseServiceImpl<DictRepository, Dict> implements IDictService {
  @Autowired
  private DictRepository customDictRepository;

  @Override
  public List<Dict> findByTypeCode(String typeCode) {
    return customDictRepository.findByTypeCode(typeCode);
  }

  @Override
  public List<Dict> findByTypeCodeAndPkey(String typeCode, String pkey, Sort sort) {
    return customDictRepository.findByTypeCodeAndPKey(typeCode, pkey, sort);
  }

  @Override
  public List<String> findDistinctTypeCode() {
    return customDictRepository.findDistinctTypeCode();
  }

  @Override
  public String findDictNameByKey(String key) {
    return customDictRepository.findDictNameByKey(key);
  }

  @Override
  public List<Dict> findByTypeCode(String typeCode, Sort sort) {
    String pkey = "0";

    return customDictRepository.findByTypeCodeAndPKeyLessThanEqualAndDelIsFalse(typeCode, pkey, sort);
  }

  @Override
  public List<ComboBox> findByTypeCodeAndKeyIn(String typeCode, List<String> keys) {

    String pkey = "0";

    Sort sort = new Sort(Sort.Direction.ASC, "order");

    List<Dict> customDicts = customDictRepository.findByTypeCodeAndPKeyLessThanEqualAndKeyInAndDelIsFalse(typeCode, pkey, keys, sort);

    List<ComboBox> comboBoxList = new ArrayList<>();
    for (Dict customDict : customDicts) {
      ComboBox comboBox = new ComboBox();
      comboBox.setText(customDict.getValue());
      comboBox.setValue(customDict.getKey());

      comboBoxList.add(comboBox);
    }

    return comboBoxList;
  }

  @Override
  public Map findCustomMapByTypeCodes(List<String> typeCodes) {
    List<Dict> customDicts = customDictRepository.findByTypeCodeIn(typeCodes);

    Map<String, String> map = new HashMap();

    for (Dict customDict : customDicts) {
      map.put(customDict.getKey(), customDict.getValue());
    }

    return map;
  }

  @Override
  public List<Dict> getIndustryDict() {
    return customDictRepository.findByTypeCodeAndPKey("industry", "0");
  }

  @Override
  public List<Dict> getIndustryDictByPkey(String key) {
    return customDictRepository.findByTypeCodeAndPKey("industry", key);
  }

  @Override
  public List<Dict> getChildrenTypeDict(String typeCode) {
    Sort sort = new Sort(Sort.Direction.ASC, "order");
    return customDictRepository.findByTypeCodeAndPKeyNot(typeCode, "0", sort);
  }

  @Override
  public List<Dict> getAllSecondIndustry() {
    Sort sort = new Sort(Sort.Direction.ASC, "order");
    return customDictRepository.findByTypeCodeAndPKeyNot("industry", "0", sort);
  }
}
