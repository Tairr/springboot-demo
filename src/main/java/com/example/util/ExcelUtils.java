package com.example.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/5/3.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
public class ExcelUtils {
    private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";

    /***
     * <pre>
     * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
     *   xls:HSSFWorkbook
     *   xlsx：XSSFWorkbook
     * @param filePath
     * @return
     * @throws IOException
     * </pre>
     */
    private static Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook = null;
        InputStream is = new FileInputStream(filePath);
        if (filePath.endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (filePath.endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    /**
     * 文件检查
     */
    private static void preReadCheck(String filePath) throws FileNotFoundException, FileFormatException {
        // 常规检查
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("传入的文件不存在：" + filePath);
        }

        if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
            throw new FileFormatException("传入的文件不是excel");
        }
    }

    /**
     * 读取excel文件内容
     *
     * @param filePath
     * @throws FileNotFoundException
     * @throws FileFormatException
     */
    public static List<Map<String, String>> readExcel(String filePath) throws FileNotFoundException, FileFormatException {
        List<Map<String, String>> resultList = new ArrayList<>();
        // 检查
        preReadCheck(filePath);
        // 获取workbook对象
        Workbook workbook = null;

        try {
            workbook = getWorkbook(filePath);
            // 读文件 一个sheet一个sheet地读取
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                System.out.println("=======================" + sheet.getSheetName() + "=========================");

                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();

                // 读取首行 即,表头
                Row firstRow = sheet.getRow(firstRowIndex);
                for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
                    Cell cell = firstRow.getCell(i);
                    String cellValue = getCellValue(cell, true);
                    System.out.print(" " + cellValue + "\t");
                }
                System.out.println("");

                // 读取数据行
                for (int rowIndex = firstRowIndex + 2; rowIndex <= lastRowIndex; rowIndex++) {
                    Row currentRow = sheet.getRow(rowIndex);// 当前行
                    int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                    Map<String, String> contentMap = new HashMap<>();
                    for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
                        Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                        String currentCellValue = getCellValue(currentCell, true);// 当前单元格的值
                        contentMap.put(columnIndex + "", currentCellValue);
                        System.out.print(currentCellValue + "\t");
                    }
                    resultList.add(contentMap);
                    System.out.println("");
                }
                System.out.println("======================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 取单元格的值
     *
     * @param cell       单元格对象
     * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
     * @return
     */
    private static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }

        if (treatAsStr) {
            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
            // 加上下面这句，临时把它当做文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }


    public static void main(String[] args) throws IOException {


        System.out.println(ExcelUtils.readExcel("C:/Users/dell/Desktop/2.xlsx"));
    }
}

