package cn.zhiyingyun.zone.common;

/**
 * Created by zyb on 4/21/17.
 */
public class Constants {
  // http header constants
  public static final String HTTP_HEADER_USER_TOKEN = "X-User-Token";

  // http request attribute
  public static final String HTTP_ATTR_USER_ID = "X-User-Id";
  public static final String HTTP_ATTR_TENANT_ID = "X-Tenant-Id";
  public static final String HTTP_ATTR_ROLE_ID = "X-Role-Id";

  public static String getDomainTableName(Class domain) {
    switch (domain.getSimpleName()) {
      case "Activity":
        return "activity";
      case "ActivityDeviceParam":
        return "activity_device_param";
      case "ActivityReportDay":
        return "activity_report_day";
      case "ActivityReportDetails":
        return "activity_report_details";
      case "ActivityReportHour":
        return "activity_report_hour";
      case "City":
        return "city";
      case "Device":
        return "device";
      case "Dict":
        return "dict";
      case "District":
        return "district";
      case "Module":
        return "module";
      case "Province":
        return "province";
      case "Role":
        return "role";
      case "Shop":
        return "shop";
      case "ShopDeviceParam":
        return "shop_device_param";
      case "ShopReportDay":
        return "shop_report_day";
      case "ShopReportDetails":
        return "shop_report_details";
      case "ShopReportHour":
        return "shop_report_hour";
      case "User":
        return "user";
      case "BusinessCircle":
        return "business_circle";
      default:
        break;
    }
    return null;
  }

  public interface COMMON_SQL {
    String TABLE_MARCO = "$TABLENAME$";
    String COLUMN_MARCO = "$COLUMNNAME$";
    String SELECT_ID_LIST = "SELECT ID FROM $TABLENAME$ WHERE 1=1";
    String SELECT_NAME_LIST = "SELECT NAME FROM $TABLENAME$ WHERE 1=1";
    String SELECT_COUNT = "SELECT COUNT(1) FROM $TABLENAME$ WHERE 1=1";

    String SELECT_DICT_BUSINESS_ID = "SELECT business_id FROM dict WHERE 1=1";

    String SELECT_OR_ID_LIST = "SELECT ID FROM $TABLENAME$ WHERE 1>1";
    String SELECT_COLUMN_LIST = "SELECT $COLUMNNAME$ FROM $TABLENAME$ WHERE 1=1";
    String SELECT_COMBOBOX_LIST = "SELECT ID AS value,NAME AS text FROM $TABLENAME$ WHERE 1=1";
    String SELECT_RESOURCE_LIST = "SELECT ID AS id,NAME AS name FROM $TABLENAME$ WHERE 1=1";
    String SELECT_ALL_LIST = "SELECT * FROM $TABLENAME$ WHERE 1=1";

    String USER_USEFUL_DEVICE = "select id from device where enable=1 and user_id=$USER_ID$ and id not in (SELECT device_id from activity_device_param) and id not in (SELECT device_id from shop_device_param)";

    String ACTIVITY_DEVICE_STATUS = "SELECT status from device where id in (SELECT device_id from activity_device_param where activity_id=$ACTIVITY_ID$)";

    String SHOP_DEVICE_STATUS = "SELECT status from device where id in (SELECT device_id from shop_device_param where shop_id=$SHOP_ID$)";
  }
}
