package cn.zhiyingyun.zone.common;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by zyb on 7/4/17.
 */
public abstract class ExcelReader implements Runnable {

  private int firstRow;
  private int lastRowOffset;
  private int[] columns;

  private Workbook wb;
  private Iterator<Sheet> sheets;

  private Sheet currentSheet;
  private int currentRow;

  public ExcelReader(InputStream xls, int[] columns, int firstRow,
                     int lastRowOffset) throws IOException, InvalidFormatException {
    this.columns = columns;
    this.firstRow = firstRow;
    this.lastRowOffset = lastRowOffset;

    this.wb = WorkbookFactory.create(xls);
    this.sheets = wb.sheetIterator();

    this.currentSheet = sheets.next();
    this.currentRow = firstRow;
  }

  public abstract void foreach(String sheetName, int sheetRowNum,
                               String[] rowInfo);

  public abstract void preProcess();

  public abstract void postProcess(boolean isSuccess);

  @Override
  public void run() {
    preProcess();
    boolean isSuccess = false;
    try {
      while (true) {
        while (currentRow > currentSheet.getLastRowNum() - lastRowOffset) {
          if (!sheets.hasNext()) {
            isSuccess = true;
            return;
          }
          currentSheet = sheets.next();
          currentRow = firstRow;
        }

        Row row = currentSheet.getRow(currentRow);
        String[] rowInfo = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
          Cell cell = row.getCell(columns[i]);
          cell.setCellType(CellType.STRING);
          rowInfo[i] = cell.getStringCellValue();
        }

        foreach(currentSheet.getSheetName(), currentRow, rowInfo);
        currentRow++;
      }
    } finally {
      try {
        if (null != wb) wb.close();
      } catch (IOException e) {
      }
      postProcess(isSuccess);
    }
  }
}
