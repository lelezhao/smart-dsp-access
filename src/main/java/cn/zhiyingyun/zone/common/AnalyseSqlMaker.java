package cn.zhiyingyun.zone.common;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by llzhao on 2016-5-28.
 */
public class AnalyseSqlMaker {

  /***
   * 分隔符号--百分号
   ***/
  private static final String SPLIT_FLAG_BFH = "%";

  /**
   * 组装and like sql (处理特殊字符的sql脚本注入问题)
   *
   * @param column 列名
   * @param param  参数
   * @return
   */
  public static String popuSqlLikeForEscape(String column, Serializable param) {
    StringBuilder sb = new StringBuilder();

    if (param != null) {
      String paramStr = param.toString();
      if (StringUtils.isNotBlank(paramStr)) {
        String cleanParam = escapeSql(paramStr);
        sb.append(" and ").append(column).append(" like '%").append(cleanParam.trim()).append("%' escape '/' ");
      }
      return sb.toString();
    } else {
      return "";
    }

  }

  /**
   * 组装or like hql
   *
   * @param column 列名
   * @param param  参数
   * @return String
   */
  public static String popuSqlOrLikeForEscape(String column, String param) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotBlank(param)) {
      String cleanParam = escapeSql(param);
      sb.append(" or ").append(column).append(" like '%").append(cleanParam.trim()).append("%' escape '/' ");
    }
    return sb.toString();
  }

  /**
   * 组装and脚本
   *
   * @param column 列名
   * @param param
   * @return String
   * @author llzhao
   * @created 2015年7月15日 下午2:08:38
   * @lastModified
   * @history
   */
  public static String popuSqlEqual(String column, String param) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotBlank(param)) {
      String cleanParam = escapeSql(param);
      sb.append(" and ").append(column).append(" = '").append(cleanParam.trim()).append("'");
    }

    return sb.toString();
  }

  /**
   * 组装and脚本
   *
   * @param column 列名
   * @param param
   * @return String
   * @author llzhao
   * @created 2015年7月15日 下午2:08:38
   * @lastModified
   * @history
   */
  public static String popuSqlEqual(String column, Integer param) {
    StringBuilder sb = new StringBuilder();
    if (param != null) {
      sb.append(" and ").append(column).append(" = ").append(param).append("");
    }

    return sb.toString();
  }

  public static String popuSqlIdIn(String column, Iterable<Integer> ids) {
    if (ids == null) {
      ids = Arrays.asList(-1);
    }
    StringBuilder sb = new StringBuilder();
    List<String> cleanParamsList = new ArrayList<String>();
    for (Integer id : ids) {
      cleanParamsList.add(id.toString());
    }
    String paramsResult = StringUtils.join(cleanParamsList.toArray(), ",");

    if (StringUtils.isNotEmpty(paramsResult)) {
      sb.append(" and ").append(column.trim()).append(" in (").append(paramsResult).append(") ");
    }

    return sb.toString();
  }

  public static String popuSqlValueIn(String column, Iterable<String> values) {
    StringBuilder sb = new StringBuilder();
    List<String> cleanParamsList = new ArrayList<String>();
    for (String value : values) {
      cleanParamsList.add("'" + value + "'");
    }
    String paramsResult = StringUtils.join(cleanParamsList.toArray(), ",");

    if (StringUtils.isNotEmpty(paramsResult)) {
      sb.append(" and ").append(column.trim()).append(" in (").append(paramsResult).append(") ");
    }

    return sb.toString();
  }

  public static String popuSqlValueNotIn(String column, Collection<String> values) {
    if (values == null || CollectionUtils.isEmpty(values)) {
      values = Arrays.asList("-1");
    }

    StringBuilder sb = new StringBuilder();
    List<String> cleanParamsList = new ArrayList<String>();
    for (String value : values) {
      cleanParamsList.add("'" + value + "'");
    }
    String paramsResult = StringUtils.join(cleanParamsList.toArray(), ",");

    if (StringUtils.isNotEmpty(paramsResult)) {
      sb.append(" and ").append(column.trim()).append(" not in (").append(paramsResult).append(") ");
    }

    return sb.toString();
  }

  public static String popuSqlValueIn(String column, Deque<String> values) {
    StringBuilder sb = new StringBuilder();
    List<String> cleanParamsList = new ArrayList<String>();
    for (String value : values) {
      cleanParamsList.add("'" + value + "'");
    }
    String paramsResult = StringUtils.join(cleanParamsList.toArray(), ",");

    if (StringUtils.isNotEmpty(paramsResult)) {
      sb.append(" and ").append(column.trim()).append(" in (").append(paramsResult).append(") ");
    }

    return sb.toString();
  }

  /**
   * sql方式的等值查询
   *
   * @param column     表列名
   * @param paramValue 对应的值
   * @return 拼接好的sql语句
   * @author llzhao
   * @created 2015年9月4日 上午11:13:45
   * @lastModified
   * @history
   */
  public static String popuSqlEq(final String column, final Serializable paramValue) {
    if (paramValue != null) {
      String paramValueStr = paramValue.toString();
      if (StringUtils.isNotBlank(paramValueStr)) {
        StringBuilder sql = new StringBuilder(200);
        sql.append(" and ").append(column).append("=").append("'").append(paramValue).append("'");
        return sql.toString();
      } else {
        return "";
      }
    } else {
      return "";
    }

  }

  /**
   * sql方式的左模糊查询 ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
   *
   * @param column
   * @param paramValue
   * @return
   * @author llzhao
   * @created 2015年12月23日 上午9:43:34
   * @lastModified
   * @history
   */
  public static String popuSqlLeftLike(final String column, final String paramValue) {
    StringBuilder sql = new StringBuilder(200);
    if (StringUtils.isNotBlank(paramValue)) {
      String cleanParam = escapeSql(paramValue);
      sql.append(" and ").append(column).append(" like %").append(column).append(cleanParam.trim()).append("' ");
    }
    return sql.toString();
  }

  /**
   * sql方式的右模糊查询 ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
   *
   * @param column
   * @param paramValue
   * @return
   * @author llzhao
   * @created 2015年12月23日 上午9:43:34
   * @lastModified
   * @history
   */
  public static String popuSqlRightLike(final String column, final String paramValue) {
    StringBuilder sql = new StringBuilder(200);
    if (StringUtils.isNotBlank(paramValue)) {
      String cleanParam = escapeSql(paramValue);
      sql.append(" and ").append(column).append(" like '").append(column).append(cleanParam.trim()).append("%' ");
    }
    return sql.toString();
  }

  /**
   * sql大于等于查询 ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
   *
   * @param column
   * @param paramValue
   * @return
   * @author llzhao
   * @created 2015年12月23日 上午9:46:43
   * @lastModified
   * @history
   */
  public static String popuSqlGreaterEq(final String column, final String paramValue) {
    StringBuilder sql = new StringBuilder(200);
    if (StringUtils.isNotBlank(paramValue)) {
      String cleanParam = escapeSql(paramValue);
      sql.append(" and ").append(column).append(">=").append(cleanParam);
    }
    return sql.toString();
  }

  /**
   * sql大于查询 ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
   *
   * @param column
   * @param paramValue
   * @return
   * @author llzhao
   * @created 2015年12月23日 上午9:46:43
   * @lastModified
   * @history
   */
  public static String popuSqlGreater(final String column, final String paramValue) {
    StringBuilder sql = new StringBuilder(200);
    if (StringUtils.isNotBlank(paramValue)) {
      String cleanParam = escapeSql(paramValue);
      sql.append(" and ").append(column).append(">").append(cleanParam);
    }
    return sql.toString();
  }

  /**
   * sql大于查询 ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
   *
   * @param column
   * @param paramValue
   * @return
   * @author llzhao
   * @created 2015年12月23日 上午9:46:43
   * @lastModified
   * @history
   */
  public static String popuSqlGreater(final String column, final Integer paramValue) {
    StringBuilder sql = new StringBuilder(200);
    if (paramValue != null) {
      sql.append(" and ").append(column).append(">").append(paramValue);
    }
    return sql.toString();
  }

  /**
   * sql小于等于查询 ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
   *
   * @param column
   * @param paramValue
   * @return
   * @author llzhao
   * @created 2015年12月23日 上午9:46:43
   * @lastModified
   * @history
   */
  public static String popuSqlLess(final String column, final String paramValue) {
    StringBuilder sql = new StringBuilder(200);
    if (StringUtils.isNotBlank(paramValue)) {
      String cleanParam = escapeSql(paramValue);
      sql.append(" and ").append(column).append("<").append(cleanParam);
    }
    return sql.toString();
  }

  /**
   * sql小于等于查询 ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
   *
   * @param column
   * @param paramValue
   * @return
   * @author llzhao
   * @created 2015年12月23日 上午9:46:43
   * @lastModified
   * @history
   */
  public static String popuSqlLessEq(final String column, final String paramValue) {
    StringBuilder sql = new StringBuilder(200);
    if (StringUtils.isNotBlank(paramValue)) {
      String cleanParam = escapeSql(paramValue);
      sql.append(" and ").append(column).append("<=").append(cleanParam);
    }
    return sql.toString();
  }

  /**
   * 组装 开始日期 查询
   *
   * @param column
   * @param param
   * @return String
   * @author llzhao
   * @created 2015年7月18日 下午1:04:54
   * @lastModified
   * @history
   */
  public static String popuBeginStringDateSql(String column, String param) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotBlank(param)) {
      String cleanParam = escapeSql(param);
      sb.append(" and ").append(column).append(" >= '").append(cleanParam).append("'");
    }
    return sb.toString();
  }

  /**
   * 组装like脚本
   *
   * @param column
   * @param param
   * @return String
   * @author llzhao
   * @created 2015年7月18日 下午1:04:54
   * @lastModified
   * @history
   */
  public static String popuEndStringDateSql(String column, String param) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotBlank(param)) {
      String cleanParam = escapeSql(param);
      sb.append(" and ").append(column).append(" <= '").append(cleanParam).append("'");
    }
    return sb.toString();
  }

  /**
   * 查询的开始时间,按日期日期格式查询
   *
   * @param coloumn
   * @param param
   * @return String
   */
  public static String popuBeginDateSql(String coloumn, String param) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotBlank(param)) {
      String cleanParam = escapeSql(param);
      sb.append(" and ").append(coloumn).append(" >='").append(cleanParam + " 00:00:00'");
    }
    return sb.toString();
  }

  /**
   * 查询的结束时间,按日期日期格式查询
   *
   * @param coloumn
   * @param param
   * @return String
   */
  public static String popuEndDateSql(String coloumn, String param) {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isNotBlank(param)) {
      String cleanParam = escapeSql(param);
      sb.append(" and ").append(coloumn).append(cleanParam + " 23:59:59'");
    }
    return sb.toString();
  }

  private static String escapeSql(String str) {
    if (str == null) {
      return null;
    }
    return StringUtils.replace(str, "'", "''");
  }
}
