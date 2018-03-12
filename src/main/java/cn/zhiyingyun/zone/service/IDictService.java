package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.common.ComboBox;
import cn.zhiyingyun.zone.domain.Dict;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * Created by Joy.Zhao on 2017/5/15.
 */
public interface IDictService extends IBaseService<Dict> {
  List<Dict> findByTypeCode(String typeCode);

  List<Dict> findByTypeCode(String typeCode, Sort sort);

  List<ComboBox> findByTypeCodeAndKeyIn(String typeCode, List<String> keys);

  List<Dict> findByTypeCodeAndPkey(String typeCode, String pkey, Sort sort);

  List<String> findDistinctTypeCode();

  String findDictNameByKey(String key);

  List<Dict> getIndustryDict();

  List<Dict> getIndustryDictByPkey(String key);

  List<Dict> getAllSecondIndustry();

  List<Dict> getChildrenTypeDict(String typeCode);

  Map findCustomMapByTypeCodes(List<String> typeCodes);
}
