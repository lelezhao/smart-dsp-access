package cn.zhiyingyun.zone.repository;


import cn.zhiyingyun.zone.common.AnalyseSqlMaker;
import cn.zhiyingyun.zone.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.zhiyingyun.zone.common.Constants.COMMON_SQL.TABLE_MARCO;

/**
 * Created by Joy.Zhao on 2017/3/29.
 */
@org.springframework.stereotype.Repository
public class CommonJdbcTemplateRepository implements Repository<String, Integer> {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Integer> findIdsBy(Class clzss, String column, String value) {

    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_ID_LIST.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlEq(column, value));

    List ids = jdbcTemplate.queryForList(sql.toString(), Integer.class);

    return ids;
  }

  public List<Integer> findIdsBy(Class clzss, String column, Serializable value) {

    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_ID_LIST.replace(TABLE_MARCO, tableName));

    if (value != null) {
      sql.append(AnalyseSqlMaker.popuSqlEq(column, value));
    }

    List ids = jdbcTemplate.queryForList(sql.toString(), Integer.class);

    if (ids.size() == 0) {
      ids.add(-1);
    }

    return ids;
  }

  public List<Integer> findIdsByLike(Class clzss, String column, String value) {

    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_ID_LIST.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlLikeForEscape(column, value));

    List ids = jdbcTemplate.queryForList(sql.toString(), Integer.class);

    if (ids.size() == 0) {
      ids.add(-1);
    }
    return ids;
  }

  public List<Integer> findIdsByIdIn(Class clzss, String column, List<Integer> value) {

    if (value == null || value.size() == 0) {
      List<Integer> ids = new ArrayList<>();
      ids.add(-1);
      return ids;
    }

    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_ID_LIST.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlIdIn(column, value));

    List ids = jdbcTemplate.queryForList(sql.toString(), Integer.class);

    if (CollectionUtils.isEmpty(ids)) {
      ids.add(-1);
    }

    return ids;
  }

  public List<Integer> findIdsByValuesIn(Class clzss, String column, Iterable value) {

    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_ID_LIST.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlValueIn(column, value));

    List ids = jdbcTemplate.queryForList(sql.toString(), Integer.class);

    return ids;
  }

  public List<String> findColumnsByValuesIn(Class clzss, String queryColumn, String column, Iterable value) {

    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COLUMN_LIST.replace(Constants.COMMON_SQL.COLUMN_MARCO, queryColumn).replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlValueIn(column, value));

    List ids = jdbcTemplate.queryForList(sql.toString(), String.class);

    return ids;
  }

  public List<Integer> findIdsByValuesOrLike(Class clzss, String column, Iterable<String> values) {

    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_ID_LIST.replace(Constants.COMMON_SQL.TABLE_MARCO, tableName));

    for (String value : values) {
      sql.append(AnalyseSqlMaker.popuSqlOrLikeForEscape(column, value));
    }

    List ids = jdbcTemplate.queryForList(sql.toString(), Integer.class);

    return ids;
  }

  public List<String> findColumnBy(Class clzss, String queryColumn, String column, String value) {
    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COLUMN_LIST.replace(Constants.COMMON_SQL.COLUMN_MARCO, queryColumn).replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlEq(column, value));

    List<String> ids = jdbcTemplate.queryForList(sql.toString(), String.class);

    return ids;
  }

  public List<String> findColumnByLike(Class clzss, String queryColumn, String column, String value) {
    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COLUMN_LIST.replace(Constants.COMMON_SQL.COLUMN_MARCO, queryColumn).replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlLikeForEscape(column, value));

    List columns = jdbcTemplate.queryForList(sql.toString(), String.class);

    return columns;
  }

  public String findColumnValueBy(Class clzss, String queryColumn, String column, Serializable value) {
    String tableName = Constants.getDomainTableName(clzss);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COLUMN_LIST.replace(Constants.COMMON_SQL.COLUMN_MARCO, queryColumn).replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlEq(column, value));

    try {
      String columns = jdbcTemplate.queryForObject(sql.toString(), String.class);
      if (StringUtils.isNotEmpty(columns)) {
        return columns;
      } else {
        return "";
      }
    } catch (Exception e) {
      return "";
    }
  }

  public String getDomainNameById(Class domain, Integer id) {
    return this.findColumnValueBy(domain, "name", "id", id);
  }

  public List getDomainComboBox(Class domain, String name) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }


    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getDomainComboBoxDelIsFalse(Class domain, String name) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }

    sql.append(AnalyseSqlMaker.popuSqlEqual("is_del", 0));
    sql.append(AnalyseSqlMaker.popuSqlEqual("status", 1));

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getDomainComboBoxNotDel(Class domain, String name) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }

    sql.append(AnalyseSqlMaker.popuSqlEqual("is_del", 0));

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getDomainComboBoxDelIsFalse(Class domain, String name, List<Integer> ids) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }
    if (!CollectionUtils.isEmpty(ids)) {
      sql.append(AnalyseSqlMaker.popuSqlIdIn("id", ids));
    } else {
      return new ArrayList();
    }
    sql.append(AnalyseSqlMaker.popuSqlEqual("is_del", 0));
    sql.append(AnalyseSqlMaker.popuSqlEqual("status", 1));

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getDomainComboBox(Class domain, String name, Boolean isDel) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }
    if (isDel != null) {
      sql.append(AnalyseSqlMaker.popuSqlEq("is_del", isDel));
    }

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getDomainComboBox(Class domain, String name, Boolean isDel, List<Integer> ids) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }
    if (isDel != null) {
      sql.append(AnalyseSqlMaker.popuSqlEq("is_del", isDel));
    }
    if (!CollectionUtils.isEmpty(ids)) {
      sql.append(AnalyseSqlMaker.popuSqlIdIn("id", ids));
    }


    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getDomainComboBox(Class domain, Integer tenantId, Boolean isDel, String name, List<Integer> ids) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }
    if (isDel != null) {
      sql.append(AnalyseSqlMaker.popuSqlEq("is_del", isDel));
    }
    if (tenantId != null) {
      sql.append(AnalyseSqlMaker.popuSqlEqual("tenant_id", tenantId));
    }
    if (!CollectionUtils.isEmpty(ids)) {
      sql.append(AnalyseSqlMaker.popuSqlIdIn("id", ids));
    }

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getDomainComboBox(Class domain, Integer tenantId, String name, List<Integer> ids) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COMBOBOX_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(name)) {
      sql.append(AnalyseSqlMaker.popuSqlLikeForEscape("name", name));
    }
    if (tenantId != null) {
      sql.append(AnalyseSqlMaker.popuSqlEqual("tenant_id", tenantId));
    }
    if (!CollectionUtils.isEmpty(ids)) {
      sql.append(AnalyseSqlMaker.popuSqlIdIn("id", ids));
    }

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getResourceNamesByIds(Class domain, String ids) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_NAME_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(ids) && !CollectionUtils.isEmpty(Arrays.asList(ids.split(",")))) {

      sql.append(AnalyseSqlMaker.popuSqlValueIn("id", Arrays.asList(ids.split(","))));
    } else {
      return new ArrayList();
    }

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getResourceByIds(Class domain, String ids) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_RESOURCE_LIST.replace(TABLE_MARCO, tableName));

    if (StringUtils.isNotEmpty(ids) && !CollectionUtils.isEmpty(Arrays.asList(ids.split(",")))) {

      sql.append(AnalyseSqlMaker.popuSqlValueIn("id", Arrays.asList(ids.split(","))));
    } else {
      return new ArrayList();
    }

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getResourceByIds(Class domain, List<String> ids) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_RESOURCE_LIST.replace(TABLE_MARCO, tableName));

    if (!CollectionUtils.isEmpty(ids)) {

      sql.append(AnalyseSqlMaker.popuSqlValueIn("id", ids));
    } else {
      return new ArrayList();
    }

    return jdbcTemplate.queryForList(sql.toString());
  }

  public List getResourceByIntIds(Class domain, List<Integer> ids) {
    List<String> stringIds = new ArrayList<>();

    for (Integer id : ids) {
      stringIds.add(id.toString());
    }

    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_RESOURCE_LIST.replace(TABLE_MARCO, tableName));

    if (!CollectionUtils.isEmpty(stringIds)) {

      sql.append(AnalyseSqlMaker.popuSqlValueIn("id", stringIds));
    } else {
      return new ArrayList();
    }

    return jdbcTemplate.queryForList(sql.toString());
  }

  public boolean existsByNameAndUserId(Class domain, String name, Integer userId) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COUNT.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlEq("name", name));
    sql.append(AnalyseSqlMaker.popuSqlEq("user_id", userId));

    Integer count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);

    return count > 0;
  }

  public boolean existsByAccount(String account) {
    String tableName = Constants.getDomainTableName(DspUser.class);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COUNT.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlEq("account", account));

    Integer count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);

    return count > 0;
  }

  public boolean existsByName(Class domain, String name) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COUNT.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlEq("name", name));
    sql.append(AnalyseSqlMaker.popuSqlEq("is_del", 0));

    Integer count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);

    return count > 0;
  }

  public boolean existsBy(Class domain, String queryColumn, String queryValue) {
    String tableName = Constants.getDomainTableName(domain);

    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SELECT_COUNT.replace(TABLE_MARCO, tableName));

    sql.append(AnalyseSqlMaker.popuSqlEq(queryColumn, queryValue));

    Integer count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);

    return count > 0;
  }

  public List<Integer> findUserUsefulDeviceIds(Integer userId) {
    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.USER_USEFUL_DEVICE);

    List<Integer> ids = jdbcTemplate.queryForList(sql.toString().replace("$USER_ID$", userId.toString()), Integer.class);

    return ids;
  }

  public List<Integer> findActivityDeviceStatus(Integer activityId) {
    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.ACTIVITY_DEVICE_STATUS);

    List<Integer> ids = jdbcTemplate.queryForList(sql.toString().replace("$ACTIVITY_ID$", activityId.toString()), Integer.class);

    return ids;
  }

  public List<Integer> findShopDeviceStatus(Integer shopId) {
    StringBuffer sql = new StringBuffer(Constants.COMMON_SQL.SHOP_DEVICE_STATUS);

    List<Integer> ids = jdbcTemplate.queryForList(sql.toString().replace("$SHOP_ID$", shopId.toString()), Integer.class);

    return ids;
  }
}

