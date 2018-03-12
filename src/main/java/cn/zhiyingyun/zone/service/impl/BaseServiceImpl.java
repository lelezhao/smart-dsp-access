package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.common.CommonPage;
import cn.zhiyingyun.zone.common.DateUtils;
import cn.zhiyingyun.zone.domain.Dict;
import cn.zhiyingyun.zone.exception.ResourceNotFoundException;
import cn.zhiyingyun.zone.repository.CommonJdbcTemplateRepository;
import cn.zhiyingyun.zone.repository.DictRepository;
import cn.zhiyingyun.zone.service.IBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static cn.zhiyingyun.zone.common.DateUtils.FORMATTER_L;
import static cn.zhiyingyun.zone.common.DateUtils.FORMATTER_S;

/**
 * Created by Joy.Zhao on 2017/3/24.
 */
@SuppressWarnings("Duplicates")
@Transactional
public class BaseServiceImpl<M extends JpaRepository, T> implements IBaseService<T> {

  private final static String CDN = "http://cdn.apilnk.com";
  @Autowired
  protected M baseRepository;
  @Autowired
  private CommonJdbcTemplateRepository jdbc;
  @Autowired
  private DictRepository customDictRepository;

  @Override
  public T save(T entity) {
    return (T) baseRepository.save(entity);
  }

  @Override
  public <S extends T> List<S> save(Iterable<S> entities) {
    return baseRepository.save(entities);
  }

  @Override
  public T findOne(Serializable id) {
    T t = (T) baseRepository.findOne(id);
    if (t == null) {
      throw new ResourceNotFoundException(id);
    }
    return t;
  }

  @Override
  public boolean exists(Serializable id) {
    return baseRepository.exists(id);
  }

  @Override
  public Iterable<T> findAll() {
    return baseRepository.findAll();
  }

  @Override
  public Iterable<T> findAll(Iterable<Integer> ids) {
    return baseRepository.findAll(ids);
  }

  @Override
  public long count() {
    return baseRepository.count();
  }

  @Override
  public void delete(Serializable id) {
    baseRepository.delete(id);
  }

  @Override
  public void delete(T entity) {
    baseRepository.delete(entity);
  }

  @Override
  public void delete(Iterable<? extends T> entities) {
    baseRepository.delete(entities);
  }

  @Override
  public void deleteByIds(Iterable<Serializable> ids) {
    for (Serializable id : ids) {
      baseRepository.delete(id);
    }
  }

  @Override
  public void deleteByIds(String ids) {
    List<Serializable> idList = Arrays.asList(ids);
    for (Serializable id : idList) {
      baseRepository.delete(id);
    }
  }

  @Override
  public void deleteAll() {
    baseRepository.deleteAll();
  }

  @Override
  public Page<T> findAll(Pageable page) {
    return baseRepository.findAll(page);
  }

  @Override
  public List<T> findByIds(Iterable<Serializable> ids) {
    return baseRepository.findAll(ids);
  }

  @Override
  public List<T> findByIds(String ids) {
    List<Serializable> idList = Arrays.asList(ids);

    return baseRepository.findAll(idList);
  }

  @Override
  public List<T> findAll(Sort sort) {
    return baseRepository.findAll(sort);
  }

  @Override
  public void flush() {
    baseRepository.flush();
  }

  @Override
  public <S extends T> S saveAndFlush(S entity) {
    return (S) baseRepository.saveAndFlush(entity);
  }

  @Override
  public void deleteInBatch(Iterable<T> entities) {
    baseRepository.deleteInBatch(entities);
  }

  @Override
  public void deleteAllInBatch() {
    baseRepository.deleteAllInBatch();
  }

  @Override
  public T getOne(Serializable id) {
    return (T) baseRepository.getOne(id);
  }

  @Override
  public <S extends T> List<S> findAll(Example<S> entity) {
    return baseRepository.findAll(entity);
  }

  @Override
  public <S extends T> List<S> findAll(Example<S> entity, Sort sort) {
    return baseRepository.findAll(entity, sort);
  }

  @Override
  public List<String> getResourceTagName(String resourceTag) {
    if (StringUtils.isNotEmpty(resourceTag)) {
      return Arrays.asList(resourceTag.split(","));
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public String parseTimestampToData(Timestamp timestamp) {
    if (timestamp == null) {
      return "";
    }
    return DateUtils.parseTimestamp(timestamp);
  }

  @Override
  public String parseTimestampToSecond(Timestamp timestamp) {
    if (timestamp == null) {
      return "";
    }
    return DateUtils.parseTimestamp(timestamp, FORMATTER_L);
  }

  @Override
  public Timestamp parseBeginDateStrToTimestamp(String beginDate) {
    if (StringUtils.isNotEmpty(beginDate)) {
      beginDate = beginDate + " 00:00:00";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      try {
        return DateUtils.toTimestamp(simpleDateFormat.parse(beginDate));
      } catch (Exception e) {
        return null;
      }

    } else {
      return null;
    }

  }

  @Override
  public Timestamp parseEndDateStrToTimestamp(String endDate) {
    if (StringUtils.isNotEmpty(endDate)) {
      endDate = endDate + " 23:59:59";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      try {
        return DateUtils.toTimestamp(simpleDateFormat.parse(endDate));
      } catch (Exception e) {
        return null;
      }

    } else {
      return null;
    }
  }

  @Override
  public Timestamp beginDateDefaultValue() {
    String date = "1970-01-01 00:00:00";

    SimpleDateFormat beginSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    try {
      return DateUtils.toTimestamp(beginSdf.parse(date));
    } catch (Exception e) {
      return null;
    }

  }

  @Override
  public Timestamp endDateDefaultValue() {
    return DateUtils.toTimestamp(new Date());
  }

  @Override
  public Timestamp beginDateValue(List<String> createTime) {
    if (createTime != null && createTime.size() > 0 && StringUtils.isNotEmpty(createTime.get(0))) {
      return parseBeginDateStrToTimestamp(createTime.get(0));
    } else {
      return beginDateDefaultValue();
    }
  }

  @Override
  public Timestamp endDateValue(List<String> endTime) {
    if (endTime != null && endTime.size() > 1 && StringUtils.isNotEmpty(endTime.get(1))) {
      return parseEndDateStrToTimestamp(endTime.get(1));
    } else {
      return endDateDefaultValue();
    }
  }

  @Override
  public <U extends Serializable> CommonPage<U> buildCommonPage(Iterable<U> data, Page<T> page) {
    CommonPage<U> dtoPage = new CommonPage<>(data);

    dtoPage.setSort(page.getSort());
    dtoPage.getPagination().setFirst(page.isFirst());
    dtoPage.getPagination().setLast(page.isLast());
    dtoPage.getPagination().setHasPrevious(page.hasPrevious());
    dtoPage.getPagination().setHasNext(page.hasNext());
    if (page.getTotalPages() > 0) {
      dtoPage.getPagination().setNumber(page.getNumber() + 1);
    } else {
      dtoPage.getPagination().setNumber(page.getNumber());
    }

    dtoPage.getPagination().setSize(page.getSize());
    dtoPage.getPagination().setNumberOfElements(page.getNumberOfElements());
    dtoPage.getPagination().setTotalPages(page.getTotalPages());
    dtoPage.getPagination().setTotalElements(page.getTotalElements());
    dtoPage.getPagination().setHasContent(page.hasContent());

    return dtoPage;
  }

  @Override
  public String getDomainNameById(Class domain, Integer id) {
    if (id == null || id == 0) {
      return "";
    }
    return jdbc.getDomainNameById(domain, id);
  }

  @Override
  public List getDomainComboBox(Class domain, String name) {
    return jdbc.getDomainComboBox(domain, name);
  }

  @Override
  public List getDomainComboBoxDelIsFalse(Class domain, String name) {
    return jdbc.getDomainComboBoxDelIsFalse(domain, name);
  }

  @Override
  public List getDomainComboBoxDelIsFalse(Class domain, String name, List<Integer> ids) {
    return jdbc.getDomainComboBoxDelIsFalse(domain, name, ids);
  }

  @Override
  public List getDomainComboBox(Class domain, String name, Boolean isDel) {
    return jdbc.getDomainComboBox(domain, name, isDel);
  }

  @Override
  public List getDomainComboBox(Class domain, String name, Boolean isDel, List<Integer> ids) {
    if (ids == null || ids.size() == 0) {
      return new ArrayList();
    }

    return jdbc.getDomainComboBox(domain, name, isDel, ids);
  }

  @Override
  public List getDomainComboBox(Class domain, Integer tenantId, String name, Boolean isDel, List<Integer> ids) {
    if (ids == null || ids.size() == 0) {
      return new ArrayList();
    }
    return jdbc.getDomainComboBox(domain, tenantId, isDel, name, ids);
  }

  @Override
  public List getDomainComboBox(Class domain, Integer tenantId, String name) {
    return jdbc.getDomainComboBox(domain, tenantId, name, null);
  }

  @Override
  public List getDomainComboBox(Class domain, String name, List<Integer> ids) {
    if (ids == null || ids.size() == 0) {
      return new ArrayList();
    }
    return jdbc.getDomainComboBox(domain, null, name, ids);
  }

  @Override
  public List getDomainComboBox(Class domain, Integer tenantId, String name, List<Integer> ids) {
    if (ids == null || ids.size() == 0) {
      return new ArrayList();
    }
    return jdbc.getDomainComboBox(domain, tenantId, name, ids);
  }

  @Override
  public List<String> getResourceNamesByIds(Class domain, String ids) {
    if (StringUtils.isEmpty(ids)) {
      return new ArrayList<>();
    }

    List list = jdbc.getResourceNamesByIds(domain, ids);
    List<String> result = new ArrayList<>();

    for (Object object : list) {
      result.add(object.toString());
    }

    return result;
  }

  @Override
  public List getResourceByIds(Class domain, String ids) {
    if (StringUtils.isEmpty(ids)) {
      return new ArrayList<>();
    }

    return jdbc.getResourceByIds(domain, ids);
  }

  @Override
  public List getResourceByIds(Class domain, List<String> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      return new ArrayList<>();
    }
    return jdbc.getResourceByIds(domain, ids);
  }

  @Override
  public List getResourceByIntIds(Class domain, List<Integer> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      return new ArrayList<>();
    }

    return jdbc.getResourceByIntIds(domain, ids);
  }

  @Override
  public String getDictValue(String dictKey) {
    if (StringUtils.isEmpty(dictKey)) {
      return "";
    }
    return customDictRepository.findDictNameByKey(dictKey);
  }

  @Override
  public String getDictValue(String typeCode, String bizId) {
    return null;
  }

  @Override
  public List<String> getDictValues(String dictKey) {
    List<String> result = new ArrayList<>();
    if (StringUtils.isEmpty(dictKey)) {
      return result;
    }

    List<Dict> customDicts = customDictRepository.findByKeyIn(Arrays.asList(dictKey.split(",")));
    for (Dict customDict : customDicts) {
      result.add(customDict.getValue());
    }

    return result;
  }

  @Override
  public List<String> getOrderDictValues(String dictKey) {
    List<String> result = new ArrayList<>();
    if (StringUtils.isEmpty(dictKey)) {
      return result;
    }

    for (String key : Arrays.asList(dictKey.split(","))) {
      result.add(getDictValue(key));
    }

    return result;
  }

  @Override
  public String getCdnUrl(String fileName) {
    if (StringUtils.isEmpty(fileName)) {
      return StringUtils.EMPTY;
    }
    if (fileName.startsWith("http") || fileName.startsWith("https")) {
      return fileName;
    } else {
      return CDN + fileName;
    }
  }

  @Override
  public Timestamp dateToTimestamp(String shortDate) {
    if (StringUtils.isNotEmpty(shortDate)) {
      return DateUtils.toTimestamp(shortDate, FORMATTER_S);
    } else {
      return null;
    }
  }

  @Override
  public String timestampToShortDate(Timestamp timestamp) {
    DateFormat sdf = new SimpleDateFormat(DateUtils.FORMATTER_S);
    return sdf.format(timestamp);
  }

  @Override
  public String timestampToLongDate(Timestamp timestamp) {
    DateFormat sdf = new SimpleDateFormat(FORMATTER_L);
    return sdf.format(timestamp);
  }

  @Override
  public String parseBeginDateStr(String beginDate) {
    if (StringUtils.isNotEmpty(beginDate)) {
      beginDate = beginDate + " 00:00:00";

      return beginDate;

    } else {
      return null;
    }
  }

  @Override
  public String parseEndDateStr(String endDate) {
    if (StringUtils.isNotEmpty(endDate)) {
      endDate = endDate + " 23:59:59";

      return endDate;

    } else {
      return null;
    }
  }

  @Override
  public Timestamp dateToBeginTimestamp(String date) {
    if (StringUtils.isNotEmpty(date)) {
      date = date + " 00:00:00";

      return DateUtils.toTimestamp(date, FORMATTER_L);

    } else {
      return null;
    }
  }

  @Override
  public Timestamp dateToEndTimestamp(String date) {
    if (StringUtils.isNotEmpty(date)) {
      date = date + " 23:59:59";

      return DateUtils.toTimestamp(date, FORMATTER_L);

    } else {
      return null;
    }
  }
}
