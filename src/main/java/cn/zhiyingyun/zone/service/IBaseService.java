package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.common.CommonPage;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Joy.Zhao on 2017/3/24.
 */
public interface IBaseService<T> {
  T save(T entity);

  <S extends T> List<S> save(Iterable<S> entities);

  T findOne(Serializable id);

  boolean exists(Serializable id);

  Iterable<T> findAll();

  Iterable<T> findAll(Iterable<Integer> ids);

  long count();

  void delete(Serializable id);

  void delete(T entity);

  void delete(Iterable<? extends T> entities);

  void deleteByIds(Iterable<Serializable> ids);

  void deleteByIds(String ids);

  void deleteAll();

  Page<T> findAll(Pageable page);

  List<T> findAll(Sort sort);

  List<T> findByIds(String ids);

  List<T> findByIds(Iterable<Serializable> ids);

  void flush();

  <S extends T> S saveAndFlush(S entity);

  void deleteInBatch(Iterable<T> entities);

  void deleteAllInBatch();

  T getOne(Serializable id);

  <S extends T> List<S> findAll(Example<S> entity);

  <S extends T> List<S> findAll(Example<S> entity, Sort sort);

  List<String> getResourceTagName(String resourceTag);

  String parseTimestampToData(Timestamp timestamp);

  String parseTimestampToSecond(Timestamp timestamp);

  Timestamp parseBeginDateStrToTimestamp(String beginDate);

  Timestamp parseEndDateStrToTimestamp(String endDate);

  Timestamp beginDateDefaultValue();

  Timestamp endDateDefaultValue();

  Timestamp beginDateValue(List<String> createTime);

  Timestamp endDateValue(List<String> createTime);

  <U extends Serializable> CommonPage<U> buildCommonPage(Iterable<U> data, Page<T> page);

  String getDomainNameById(Class domain, Integer id);

  List getDomainComboBox(Class domain, String name);

  List getDomainComboBoxDelIsFalse(Class domain, String name);

  List getDomainComboBoxDelIsFalse(Class domain, String name, List<Integer> ids);

  List getDomainComboBox(Class domain, String name, Boolean isDel);

  List getDomainComboBox(Class domain, String name, Boolean isDel, List<Integer> ids);

  List getDomainComboBox(Class domain, Integer tenantId, String name, Boolean isDel, List<Integer> ids);

  List getDomainComboBox(Class domain, Integer tenantId, String name);

  List getDomainComboBox(Class domain, String name, List<Integer> ids);

  List getDomainComboBox(Class domain, Integer tenantId, String name, List<Integer> ids);

  List<String> getResourceNamesByIds(Class domain, String ids);

  List getResourceByIds(Class domain, String ids);

  List getResourceByIds(Class domain, List<String> ids);

  List getResourceByIntIds(Class domain, List<Integer> ids);

  String getDictValue(String dictKey);

  String getDictValue(String typeCode, String bizId);

  List<String> getDictValues(String dictKey);

  List<String> getOrderDictValues(String dictKey);

  String getCdnUrl(String fileName);

  Timestamp dateToTimestamp(String shortDate);

  String timestampToShortDate(Timestamp timestamp);

  String timestampToLongDate(Timestamp timestamp);

  String parseBeginDateStr(String beginDate);

  String parseEndDateStr(String endDate);

  Timestamp dateToBeginTimestamp(String date);

  Timestamp dateToEndTimestamp(String date);
}
