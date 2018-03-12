package cn.zhiyingyun.zone.repository;

import cn.zhiyingyun.zone.domain.Dict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DictRepository extends JpaRepository<Dict, String> {
  @Cacheable
  List<Dict> findByTypeCode(String typeCode);

  @Cacheable
  List<Dict> findByTypeCodeAndPKeyLessThanEqualAndDelIsFalse(String typeCode, String pkey, Sort sort);

  @Cacheable
  List<Dict> findByTypeCodeAndPKeyLessThanEqualAndKeyInAndDelIsFalse(String typeCode, String pkey, List<String> keys, Sort sort);

  @Cacheable
  List<Dict> findByTypeCodeAndPKey(String typeCode, String pkey, Sort sort);

  @Cacheable
  List<Dict> findByTypeCodeIn(List<String> typeCodes);

  @Query("SELECT DISTINCT typeCode FROM  Dict")
  List<String> findDistinctTypeCode();

  @Query("SELECT value from Dict where key=?1")
  String findDictNameByKey(String key);

  List<Dict> findByKeyIn(List<String> key);

  List<Dict> findByTypeCodeAndPKey(String typeCode, String key);

  List<Dict> findByTypeCodeAndPKeyNot(String typeCode, String key, Sort sort);
}