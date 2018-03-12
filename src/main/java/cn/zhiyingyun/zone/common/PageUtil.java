package cn.zhiyingyun.zone.common;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Joy.Zhao on 2017/5/15.
 */
public class PageUtil<T> {

  public static PageRequest createPageRequest(final Integer pageNumber, final Integer pageSize, Sort sort) {

    Sort newSort = sort;
    Iterator<Sort.Order> iterator = sort.iterator();
    HashMap<String, String> sortFeildMap = mappingSortField();

    while (iterator.hasNext()) {

      Sort.Order order = iterator.next();

      if (sortFeildMap.containsKey(order.getProperty())) {
        newSort = new Sort(order.getDirection(), sortFeildMap.get(order.getProperty()));
      }
    }
    if (null == pageNumber || null == pageSize || pageSize <= 0) {
      return new PageRequest(0, 20, newSort);
    }
    if (pageNumber - 1 <= 0) {
      return new PageRequest(0, pageSize, newSort);
    }
    return new PageRequest(pageNumber - 1, pageSize, newSort);
  }

  //需要单独处理的排序字段
  private static HashMap<String, String> mappingSortField() {
    HashMap<String, String> map = new HashMap();
    map.put("typeName", "type");
    map.put("promotionTypeName", "promotionType");
    map.put("date", "lastVisitTime");

    return map;
  }

  public static List setListPageDate(Integer begin, Integer end, List list) {
    List pageList = null;
    //设置内存分页数据
    if (list != null && begin != null && end != null) {
      Integer maxSize = begin + end;
      Integer dataSize = list.size();

      if (begin > dataSize) {
        return new ArrayList();
      }

      if (maxSize < dataSize) {
        pageList = list.subList(begin, maxSize);
      } else {
        pageList = list.subList(begin, list.size());
      }
    }
    return pageList == null ? new ArrayList() : pageList;
  }

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
}
