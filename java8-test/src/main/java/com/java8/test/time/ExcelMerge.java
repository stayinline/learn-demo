package com.java8.test.time;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelMerge {
    public static void main(String[] args) throws IOException {
        Workbook workbookToRead = WorkbookFactory.create(new File("/Users/hemaoling/Desktop/服务器SP3-全模块用例.xlsx"));
        Workbook workbookToWrite = WorkbookFactory.create(new File("/Users/hemaoling/Desktop/resultfile.xlsx"));
        CreationHelper createHelper = workbookToWrite.getCreationHelper();
        Sheet sheetToWrite = workbookToWrite.createSheet("newsheet");
        int rowCount = 0;
        for (Sheet sheet : workbookToRead) {
            Row rowToWrite = sheetToWrite.createRow(rowCount);
            Cell cellToWrite = rowToWrite.createCell(0);
            cellToWrite.setCellValue(createHelper.createRichTextString(sheet.getSheetName()));
            rowCount++;

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row rowToRead = rowIterator.next();

                rowToWrite = sheetToWrite.createRow(rowCount);

                Iterator<Cell> cellIterator = rowToRead.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cellToRead = cellIterator.next();



                    cellToWrite = rowToWrite.createCell(cellToRead.getColumnIndex());

                    switch (cellToRead.getCellType()) {
                        case STRING:
                            cellToWrite.setCellValue(cellToRead.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cellToRead)) {
                                cellToWrite.setCellValue(cellToRead.getDateCellValue());
                            } else {
                                cellToWrite.setCellValue(cellToRead.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellToWrite.setCellValue(cellToRead.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellToWrite.setCellValue(cellToRead.getCellFormula());
                            break;
                        default:
                            cellToWrite.setCellValue("");
                    }
                }
                rowCount++;
            }
            rowCount++; 
        }
        FileOutputStream fileOutputStream = new FileOutputStream("resultfile.xlsx");
        workbookToWrite.write(fileOutputStream);
        fileOutputStream.close();
        workbookToRead.close();
        workbookToWrite.close();
    }

    private static boolean isRowBlank(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
