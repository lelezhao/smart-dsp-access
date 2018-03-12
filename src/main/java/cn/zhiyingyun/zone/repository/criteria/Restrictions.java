package cn.zhiyingyun.zone.repository.criteria;

import com.aliyun.oss.model.MatchMode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 条件构造器
 * 用于创建条件表达式
 * Created by Joy.Zhao on 2017/5/20.
 */
public class Restrictions {

  private static boolean isEmpty(Object value) {
    return value == null;
  }

  /**
   * 等于
   *
   * @param fieldName
   * @param value
   * @return
   */
  public static SimpleExpression equal(String fieldName, Object value) {

    if (isEmpty(value)) return null;
    return new SimpleExpression(fieldName, value, Criterion.Operator.EQ);
  }

  /**
   * 不等于
   *
   * @param fieldName
   * @param value
   * @return
   */
  public static SimpleExpression notEqual(String fieldName, Object value) {
    if (isEmpty(value)) return null;
    return new SimpleExpression(fieldName, value, Criterion.Operator.NE);
  }

  /**
   * 模糊匹配
   *
   * @param fieldName
   * @param value
   * @return
   */
  public static SimpleExpression like(String fieldName, String value) {
    if (StringUtils.isEmpty(value)) return null;
    return new SimpleExpression(fieldName, value, Criterion.Operator.LIKE);
  }

  /**
   * 未实现
   *
   * @param fieldName
   * @param value
   * @param matchMode
   * @return
   */
  public static SimpleExpression like(String fieldName, String value, MatchMode matchMode) {

    if (StringUtils.isEmpty(value)) return null;
    return null;
  }

  /**
   * 大于
   *
   * @param fieldName
   * @param value
   * @return
   */
  public static SimpleExpression greater(String fieldName, Object value) {
    if (isEmpty(value)) return null;
    return new SimpleExpression(fieldName, value, Criterion.Operator.GT);
  }

  /**
   * 小于
   *
   * @param fieldName
   * @param value
   * @return
   */
  public static SimpleExpression less(String fieldName, Object value) {
    if (isEmpty(value)) return null;
    return new SimpleExpression(fieldName, value, Criterion.Operator.LT);
  }

  /**
   * 大于等于
   *
   * @param fieldName
   * @param value
   * @return
   */
  public static SimpleExpression greaterEqual(String fieldName, Object value) {
    if (isEmpty(value)) return null;
    return new SimpleExpression(fieldName, value, Criterion.Operator.GTE);
  }

  /**
   * 小于等于
   *
   * @param fieldName
   * @param value
   * @return
   */
  public static SimpleExpression lessEqual(String fieldName, Object value) {
    if (isEmpty(value)) return null;
    return new SimpleExpression(fieldName, value, Criterion.Operator.LTE);
  }

  /**
   * 并且
   *
   * @param criterions
   * @return
   */
  public static LogicalExpression and(Criterion... criterions) {
    return new LogicalExpression(criterions, Criterion.Operator.AND);
  }

  /**
   * 或者
   *
   * @param criterions
   * @return
   */
  public static LogicalExpression or(Criterion... criterions) {
    return new LogicalExpression(criterions, Criterion.Operator.OR);
  }

  /**
   * 包含于
   *
   * @param fieldName
   * @param value
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
    if (ignoreNull) {
      return null;
    } else {
      if (CollectionUtils.isEmpty(value)) {
        List<Integer> id = new ArrayList<>();
        id.add(-1);
        value = id;
      }
    }


    SimpleExpression[] ses = new SimpleExpression[value.size()];
    int i = 0;
    for (Object obj : value) {
      ses[i] = new SimpleExpression(fieldName, obj, Criterion.Operator.EQ);
      i++;
    }
    return new LogicalExpression(ses, Criterion.Operator.OR);
  }

  /**
   * 包含于
   *
   * @param fieldName
   * @param value
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static LogicalExpression notIn(String fieldName, Collection value, boolean ignoreNull) {
    if (ignoreNull) {
      return null;
    } else {
      if (CollectionUtils.isEmpty(value)) {
        List<Integer> id = new ArrayList<>();
        id.add(-1);
        value = id;
      }
    }


    SimpleExpression[] ses = new SimpleExpression[value.size()];
    int i = 0;
    for (Object obj : value) {
      ses[i] = new SimpleExpression(fieldName, obj, Criterion.Operator.NE);
      i++;
    }
    return new LogicalExpression(ses, Criterion.Operator.OR);
  }
}
