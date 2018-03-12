package cn.zhiyingyun.zone.repository.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 条件接口
 * 用户提供条件表达式接口
 * Created by Joy.Zhao on 2017/5/20.
 */
public interface Criterion {
  Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);

  enum Operator {
    EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR
  }

}
