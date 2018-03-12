package cn.zhiyingyun.zone.common;

import cn.zhiyingyun.zone.exception.PreconditionFailedException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joy.Zhao on 2017/5/6.
 */
public class TinkerExcelUtil {
  private static final DecimalFormat decimalFormat = new DecimalFormat("0");// 格式化 number String

  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串

  private static final DecimalFormat decimalFormatn = new DecimalFormat("0.00");// 格式化数字

  public static List<List<Object>> readExcel(String ext, InputStream inputStream) throws IOException {
    if (ext.equals(".xls")) {
      return TinkerExcelUtil.readExcel2003(inputStream);
    } else if (ext.equals(".xlsx")) {
      return TinkerExcelUtil.readExcel(inputStream);
    } else {
      throw new PreconditionFailedException("格式错误！");
    }
  }

  public static List<List<Object>> readExcel(InputStream inputStream) throws IOException {

    List<List<Object>> list = new LinkedList<List<Object>>();

    // 构造 XSSFWorkbook 对象，strPath 传入InputStream
    XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
    // 读取第一章表格内容
    XSSFSheet sheet = xwb.getSheetAt(0);

    XSSFRow row;
    XSSFCell cell;
    int counter = 0;
    for (int i = sheet.getFirstRowNum() + 1; counter < sheet.getPhysicalNumberOfRows(); i++) {

      row = sheet.getRow(i);
      if (row == null) {
        counter++;
        continue;
      } else {
        counter++;
      }

      //判断当前Row 第一个单元格是否有值，没有的话，认为内容结束
      XSSFCell firstCell = row.getCell(row.getFirstCellNum());
      if (getCellValue(firstCell) == null || StringUtils.isEmpty(getCellValue(firstCell).toString())) {
        break;
      }

      List<Object> linked = new LinkedList<Object>();
      for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
        cell = row.getCell(j);
        if (cell == null) {
          continue;
        }

        linked.add(getCellValue(cell));
      }
      list.add(linked);
    }
    return list;
  }


  public static List<List<Object>> readExcel2003(InputStream inputStream) throws IOException {
    List<List<Object>> list = new LinkedList<List<Object>>();

    // 构造 HSSFWorkbook 对象，传入InputStream
    HSSFWorkbook hwb = new HSSFWorkbook(inputStream);

    // 读取第一个Sheet夜内容
    HSSFSheet sheet = hwb.getSheetAt(0);

    HSSFRow row = null;
    HSSFCell cell = null;
    int counter = 0;
    for (int i = sheet.getFirstRowNum() + 1; counter < sheet.getPhysicalNumberOfRows(); i++) {

      row = sheet.getRow(i);

      if (row == null) {
        counter++;
        continue;
      } else {
        counter++;
      }

      //判断当前Row 第一个单元格是否有值，没有的话，认为内容结束
      HSSFCell firstCell = row.getCell(row.getFirstCellNum());
      if (getCellValue2003(firstCell) == null || StringUtils.isEmpty(getCellValue2003(firstCell).toString())) {
        break;
      }

      List<Object> linked = new LinkedList<Object>();
      for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
        cell = row.getCell(j);
        if (cell == null) {
          continue;
        }

        linked.add(getCellValue2003(cell));
      }
      list.add(linked);
    }
    return list;
  }

  public static String exportExcel(String title, List<String> headers, List<ExcelReplaceDataVO> datas) {
    // 声明一个工作薄
    XSSFWorkbook workbook = new XSSFWorkbook();
    // 生成一个表格
    XSSFSheet sheet = workbook.createSheet(title);

    // 设置表格默认列宽度为15个字节
    sheet.setDefaultColumnWidth((short) 15);
    // 生成一个样式
    XSSFCellStyle style = workbook.createCellStyle();
    // 设置这些样式
    style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setBorderTop(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.CENTER);

    // 生成一个字体
    XSSFFont font = workbook.createFont();
    font.setColor(HSSFColor.VIOLET.index);
    font.setFontHeightInPoints((short) 12);
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

    // 把字体应用到当前的样式
    style.setFont(font);
    // 生成并设置另一个样式
    XSSFCellStyle style2 = workbook.createCellStyle();
    style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style2.setBorderBottom(BorderStyle.THIN);
    style2.setBorderLeft(BorderStyle.THIN);
    style2.setBorderRight(BorderStyle.THIN);
    style2.setBorderTop(BorderStyle.THIN);
    style2.setAlignment(HorizontalAlignment.CENTER);
    style2.setVerticalAlignment(VerticalAlignment.CENTER);
    // 生成另一个字体
    XSSFFont font2 = workbook.createFont();
    font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
    // 把字体应用到当前的样式
    style2.setFont(font2);

    // 产生表格标题行
    XSSFRow row = sheet.createRow(0);
    for (short i = 0; i < headers.size(); i++) {
      XSSFCell cell = row.createCell(i);
      cell.setCellStyle(style);
      XSSFRichTextString text = new XSSFRichTextString(headers.get(i));
      cell.setCellValue(text);
    }
    // 遍历集合数据，产生数据行
    for (ExcelReplaceDataVO data : datas) {
      //如果当前写入row不存在，则创建
      row = sheet.getRow(data.getRow());
      if (row == null) {
        row = sheet.createRow(data.getRow());
      }

      XSSFCell cell = row.createCell((short) data.getColumn());
      cell.setCellStyle(style2);
      // 获取单元格内容
      String str = data.getValue();
      // 写入单元格内容
      cell.setCellType(CellType.STRING);
      // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
      cell.setCellValue(str);
    }

    // 输出文件到OSS云存储上
    try {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      workbook.write(os);

      String fileName = "exports" + DateUtils.currentDateString() + "_" + DigestUtils.md5Hex(os.toByteArray()) + ".xlsx";

      boolean isSuccess = AliOssManagerUtil.uploadFileByBytes(os.toByteArray(), fileName);

      if (isSuccess) {
        return AliOssManagerUtil.getOrignPictureUrl(fileName);
      } else {
        throw new RuntimeException("文件上传至OSS失败！");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static ByteArrayOutputStream exportExcelStream(String title, List<String> headers, List<ExcelReplaceDataVO> datas) {
    // 声明一个工作薄
    XSSFWorkbook workbook = new XSSFWorkbook();
    // 生成一个表格
    XSSFSheet sheet = workbook.createSheet(title);

    // 设置表格默认列宽度为15个字节
    sheet.setDefaultColumnWidth((short) 15);
    // 生成一个样式
    XSSFCellStyle style = workbook.createCellStyle();
    // 设置这些样式
    style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setBorderTop(BorderStyle.THIN);
    style.setAlignment(HorizontalAlignment.CENTER);

    // 生成一个字体
    XSSFFont font = workbook.createFont();
    font.setColor(HSSFColor.VIOLET.index);
    font.setFontHeightInPoints((short) 12);
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

    // 把字体应用到当前的样式
    style.setFont(font);
    // 生成并设置另一个样式
    XSSFCellStyle style2 = workbook.createCellStyle();
    style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style2.setBorderBottom(BorderStyle.THIN);
    style2.setBorderLeft(BorderStyle.THIN);
    style2.setBorderRight(BorderStyle.THIN);
    style2.setBorderTop(BorderStyle.THIN);
    style2.setAlignment(HorizontalAlignment.CENTER);
    style2.setVerticalAlignment(VerticalAlignment.CENTER);
    // 生成另一个字体
    XSSFFont font2 = workbook.createFont();
    font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
    // 把字体应用到当前的样式
    style2.setFont(font2);

    // 产生表格标题行
    XSSFRow row = sheet.createRow(0);
    for (short i = 0; i < headers.size(); i++) {
      XSSFCell cell = row.createCell(i);
      cell.setCellStyle(style);
      XSSFRichTextString text = new XSSFRichTextString(headers.get(i));
      cell.setCellValue(text);
    }
    // 遍历集合数据，产生数据行
    for (ExcelReplaceDataVO data : datas) {
      //如果当前写入row不存在，则创建
      row = sheet.getRow(data.getRow());
      if (row == null) {
        row = sheet.createRow(data.getRow());
      }

      XSSFCell cell = row.createCell((short) data.getColumn());
      cell.setCellStyle(style2);
      // 获取单元格内容
      String str = data.getValue();
      // 写入单元格内容
      cell.setCellType(CellType.STRING);
      // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
      cell.setCellValue(str);
    }

    // 输出文件到OSS云存储上
    try {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      workbook.write(os);

      return os;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ByteArrayOutputStream();
  }

  private static Object getCellValue(XSSFCell cell) {

    Object value;

    switch (cell.getCellTypeEnum()) {

      case STRING:
        value = cell.getStringCellValue();
        break;
      case NUMERIC:
        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
          value = decimalFormat.format(cell.getNumericCellValue());
        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
          value = decimalFormatn.format(cell.getNumericCellValue());
        } else {
          value = simpleDateFormat.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
        }
        break;
      case BOOLEAN:
        value = cell.getBooleanCellValue();
        break;
      case BLANK:
        value = "";
        break;
      default:
        value = cell.toString();
    }

    return value;
  }

  private static Object getCellValue2003(HSSFCell cell) {
    Object value = null;

    switch (cell.getCellTypeEnum()) {
      case STRING:
        value = cell.getStringCellValue();
        break;
      case NUMERIC:
        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
          value = decimalFormat.format(cell.getNumericCellValue());
        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
          value = decimalFormatn.format(cell.getNumericCellValue());
        } else {
          value = simpleDateFormat.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
        }
        break;
      case BOOLEAN:
        value = cell.getBooleanCellValue();
        break;
      case BLANK:
        value = "";
        break;
      default:
        value = cell.toString();
    }

    return value;
  }
}
