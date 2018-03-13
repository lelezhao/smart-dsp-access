package cn.zhiyingyun.zone.controller;


import cn.zhiyingyun.zone.common.DateUtils;
import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.dto.page.BasePageQueryDto;
import cn.zhiyingyun.zone.exception.ResourceAlreadyExistException;
import cn.zhiyingyun.zone.repository.CommonJdbcTemplateRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.zhiyingyun.zone.common.DateUtils.FORMATTER_S;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;


/**
 * Created by Joy.Zhao on 2017/3/24.
 */
@Controller
public abstract class BaseController {

  private final static String CDN = "https://cdn.apilnk.com/smartad/";

  protected Logger logger = LogManager.getLogger(getClass());

  @Autowired
  private CommonJdbcTemplateRepository jdbcTempleRepository;

  protected HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  /**
   * 获取当前用户id
   *
   * @return
   */
  public Integer getUserId() {
    return (Integer) getRequest().getAttribute("X-User-Id");
  }

  /**
   * ajax失败
   *
   * @param msg 失败的消息
   * @return {Object}
   */
  public JsonResult renderError(String msg) {

    JsonResult jsonResult = new JsonResult();
    jsonResult.setCode(HttpStatus.BAD_REQUEST);
    jsonResult.setMessage(msg);

    return jsonResult;
  }

  /**
   * @param status
   * @param msg
   * @return
   * @author kalrey
   */
  public JsonResult renderError(HttpStatus status, String msg) {
    JsonResult jsonResult = new JsonResult();
    jsonResult.setCode(status);
    jsonResult.setMessage(msg);
    return jsonResult;
  }

  /**
   * ajax成功
   *
   * @return {Object}
   */
  public JsonResult renderSuccess() {
    JsonResult jsonResult = new JsonResult();
    jsonResult.setCode(HttpStatus.OK);
    jsonResult.setMessage("操作成功");

    return jsonResult;
  }

  /**
   * ajax成功
   *
   * @param msg 消息
   * @return {Object}
   */
  public JsonResult renderSuccessMessage(String msg) {
    JsonResult result = new JsonResult();
    result.setCode(HttpStatus.OK);
    result.setMessage(msg);

    return result;
  }

  /**
   * ajax成功
   *
   * @param obj 成功时的对象
   * @return {Object}
   */
  public JsonResult renderSuccess(Object obj) {
    JsonResult result = new JsonResult();
    result.setCode(HttpStatus.OK);
    result.setData(obj);
    return result;
  }

  protected List<Integer> convertStringToIntegerList(String ids) {
    List<String> idList = Arrays.asList(ids.split("_"));

    List<Integer> intIds = new ArrayList<>();

    for (String id : idList) {
      intIds.add(Integer.parseInt(id));
    }

    return intIds;
  }

  void createResourceInit(Object object) {
    Class<?> actualEditable = object.getClass();
    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    for (PropertyDescriptor targetPd : targetPds) {
      Method writeMethod = targetPd.getWriteMethod();
      try {
        switch (targetPd.getName()) {
          case "createTime":
            writeMethod.invoke(object, DateUtils.currentTimestamp());
            break;
          case "updateTime":
            writeMethod.invoke(object, DateUtils.currentTimestamp());
            break;
          case "del":
            writeMethod.invoke(object, Boolean.FALSE);
            break;
          default:
            break;
        }
      } catch (Exception e) {
        throw new NotImplementedException();
      }

    }
  }

  void modifyResourceInit(Object object) {
    Class<?> actualEditable = object.getClass();
    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    for (PropertyDescriptor targetPd : targetPds) {
      Method writeMethod = targetPd.getWriteMethod();
      try {
        switch (targetPd.getName()) {
          case "updateTime":
            writeMethod.invoke(object, DateUtils.currentTimestamp());
            break;

          default:
            break;
        }
      } catch (Exception e) {
        throw new NotImplementedException();
      }

    }
  }

  void deleteResourceInit(Object object) {
    Class<?> actualEditable = object.getClass();
    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    for (PropertyDescriptor targetPd : targetPds) {
      Method writeMethod = targetPd.getWriteMethod();
      try {
        switch (targetPd.getName()) {
          case "updateTime":
            writeMethod.invoke(object, DateUtils.currentTimestamp());
            break;
          case "del":
            writeMethod.invoke(object, Boolean.TRUE);
            break;
          default:
            break;
        }
      } catch (Exception e) {
        throw new NotImplementedException();
      }

    }
  }

  void isResourceNameAlreadyExist(Class domain, String value, String beforeValue) {
    if (StringUtils.isNotEmpty(value) && !value.equals(beforeValue)) {
      isResourceNameAlreadyExist(domain, value);
    }
  }

  void isResourceNameAlreadyExist(Class domain, String resourceName) {
    if (StringUtils.isNotEmpty(resourceName)) {
      boolean result = jdbcTempleRepository.existsByNameAndUserId(domain, resourceName, getUserId());

      if (result) {
        throw new ResourceAlreadyExistException(resourceName + "，名称已存在！");
      }
    }
  }

  void isColumnValueAlreadyExist(Class domain, String queryColumn, String value, String beforeValue) {
    if (StringUtils.isNotEmpty(value) && !value.equals(beforeValue)) {
      isColumnValueAlreadyExist(domain, queryColumn, value);
    }
  }

  void isColumnValueAlreadyExist(Class domain, String queryColumn, String resourceName) {
    if (StringUtils.isNotEmpty(resourceName)) {
      boolean result = jdbcTempleRepository.existsBy(domain, queryColumn, resourceName);

      if (result) {
        throw new ResourceAlreadyExistException(resourceName + "，名称已存在！");
      }
    }
  }

  void initBasePageQuery(BasePageQueryDto queryDto) {
    queryDto.setUserId(getUserId());
  }

  void isAccountAlreadyExist(String account) {
    if (StringUtils.isNotEmpty(account)) {
      boolean result = jdbcTempleRepository.existsByAccount(account);

      if (result) {
        throw new ResourceAlreadyExistException(account + "，账户已存在，新建失败！");
      }
    }
  }

  public String getCdnUrl(String fileName) {
    return CDN + fileName;
  }

  public List<Integer> findDomainIdsBy(Class clzss, String column, Serializable value) {
    return jdbcTempleRepository.findIdsBy(clzss, column, value);
  }

  public List<Integer> findIdsByIdValueIn(Class clzss, String column, List<Integer> value) {
    if (value == null || value.size() == 0) {
      return new ArrayList<>();
    }
    return jdbcTempleRepository.findIdsByIdIn(clzss, column, value);
  }

  /**
   * 短日期格式转Timestap
   *
   * @param shortDate（yyyy-MM-dd）
   * @return
   */
  public Timestamp shortDateToTimestap(String shortDate) {
    if (StringUtils.isNotEmpty(shortDate)) {
      return DateUtils.toTimestamp(shortDate, FORMATTER_S);
    } else {
      return null;
    }
  }

}
