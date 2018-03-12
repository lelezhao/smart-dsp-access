package cn.zhiyingyun.zone.common;

import org.springframework.data.domain.Sort;

/**
 * Created by Joy.Zhao on 2017/5/15.
 */
public class CommonPage<T> {

  public String code = "200";
  public String message = "操作成功";
  private Iterable<T> data;
  private Sort sort;
  private Pagination pagination;
  private Object extention;

  public CommonPage(Iterable<T> data) {
    pagination = new Pagination();
    this.data = data;
  }

  public CommonPage() {
  }

  public Iterable<T> getData() {
    return data;
  }

  public void setData(Iterable<T> data) {
    this.data = data;
  }

  public Sort getSort() {
    return sort;
  }

  public void setSort(Sort sort) {
    this.sort = sort;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Pagination getPagination() {
    return pagination;
  }

  public void setPagination(Pagination pagination) {
    this.pagination = pagination;
  }

  public Object getExtention() {
    return extention;
  }

  public void setExtention(Object extention) {
    this.extention = extention;
  }
}
