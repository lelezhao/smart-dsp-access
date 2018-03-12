package cn.zhiyingyun.zone.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joy.Zhao on 2017/4/5.
 */
public abstract class BeanUtils extends org.springframework.beans.BeanUtils {

  private static final List<String> types = Arrays.asList("java.lang.Long", "java.lang.Integer", "java.math.BigInteger", "java.lang.Float", "java.lang.Double", "java.math.BigDecimal");

  public static void copyProperties(Object source, Object target) throws BeansException {
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");
    Class<?> actualEditable = target.getClass();
    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    for (PropertyDescriptor targetPd : targetPds) {
      if (targetPd.getWriteMethod() != null) {
        PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
        if (sourcePd != null && sourcePd.getReadMethod() != null) {
          try {
            Method readMethod = sourcePd.getReadMethod();
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
              readMethod.setAccessible(true);
            }
            Object value = readMethod.invoke(source);

            Method writeMethod = targetPd.getWriteMethod();
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
              writeMethod.setAccessible(true);
            }

            // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
            if (value != null) {
              writeMethod.invoke(target, value);
            } else {//null转默认值
              String type = targetPd.getPropertyType().getName();

              //如果是java.lang.String类型的
              if (type.equals("java.lang.String")) {
                writeMethod.invoke(target, StringUtils.EMPTY);
              }
              //如果是java.lang.Boolean类型的
              if (type.equals("java.lang.Boolean")) {
                writeMethod.invoke(target, Boolean.FALSE);
              }
              //如果是数值类型的，赋值null
              if (types.contains(type)) {
                writeMethod.invoke(target, value);
              }

            }
          } catch (Throwable ex) {
            throw new FatalBeanException("Could not copy properties from source to target", ex);
          }
        }
      }
    }
  }

  public static void copyPropertiesIgnoreNull(Object source, Object target) throws BeansException {
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");
    Class<?> actualEditable = target.getClass();
    PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
    for (PropertyDescriptor targetPd : targetPds) {
      if (targetPd.getWriteMethod() != null) {
        PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
        if (sourcePd != null && sourcePd.getReadMethod() != null) {
          try {
            Method readMethod = sourcePd.getReadMethod();
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
              readMethod.setAccessible(true);
            }
            Object value = readMethod.invoke(source);

            Method writeMethod = targetPd.getWriteMethod();
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
              writeMethod.setAccessible(true);
            }

            // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
            if (value != null) {
              writeMethod.invoke(target, value);
            } else {

              String type = targetPd.getPropertyType().getName();

              //如果是数值类型的，赋值null
              if (types.contains(type)) {
                writeMethod.invoke(target, value);
              }

            }
          } catch (Throwable ex) {
            throw new FatalBeanException("Could not copy properties from source to target", ex);
          }
        }
      }
    }
  }

  public static void setNullToEmptyString(Object bean) {
    Class<?> cls = bean.getClass();
    // 取出bean里的所有方法
    Field[] fields = cls.getDeclaredFields();
    List<Field> allFields = new ArrayList<>(Arrays.asList(fields));
    if (cls.getSuperclass() != null && !cls.getSuperclass().getName().equals("java.lang.Object")) {
      Field[] parentFields = cls.getSuperclass().getDeclaredFields();
      if (parentFields != null && parentFields.length > 0) {
        allFields.addAll(Arrays.asList(parentFields));
      }
    }

    for (Field field : allFields) {
      try {
        String fieldSetName = parSetName(field.getName());
        String fieldGetName = parGetName(field.getName());

        Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
        Method fieldGetMet = cls.getMethod(fieldGetName);

        String fieldType = field.getType().getSimpleName();
        if ("String".equals(fieldType) && fieldGetMet.invoke(bean) == null) {
          fieldSetMet.invoke(bean, "");
        }
      } catch (Exception e) {
        continue;
      }
    }
  }


  /**
   * 拼接某属性的 get方法
   *
   * @param fieldName
   * @return String
   */
  public static String parGetName(String fieldName) {
    if (null == fieldName || "".equals(fieldName)) {
      return null;
    }
    int startIndex = 0;
    if (fieldName.charAt(0) == '_')
      startIndex = 1;
    return "get"
            + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
            + fieldName.substring(startIndex + 1);
  }

  /**
   * 拼接在某属性的 set方法
   *
   * @param fieldName
   * @return String
   */
  public static String parSetName(String fieldName) {
    if (null == fieldName || "".equals(fieldName)) {
      return null;
    }
    int startIndex = 0;
    if (fieldName.charAt(0) == '_')
      startIndex = 1;
    return "set"
            + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
            + fieldName.substring(startIndex + 1);
  }
}
