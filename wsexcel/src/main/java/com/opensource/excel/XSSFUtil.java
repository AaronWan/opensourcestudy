package com.opensource.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 25/11/2016.
 */
public class XSSFUtil {
    @Test
    public void xssfCreate1() throws Exception {
//        XSSFWorkbook wb = new XSSFWorkbook();
//        XSSFSheet sheet = wb.createSheet();
//        for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                writeCell(sheet, i, j,new Color(200+i,200+i,80+i), i*j);
//            }
//        }
//        ByteOutputStream bos=new ByteOutputStream();
//        wb.write(bos);
//
//        ByteInputStream bis=new ByteInputStream();
//        bis.setBuf(bos.getBytes());
        Map<Integer, SheetContent> sheetContents = readExcel(new FileInputStream
                ("/Users/Aaron/Documents/facishare/code/gitfirstshare/opensourcestudy/src/main/resources/CRM记录导出" +
                        ".xlsx"),true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(sheetContents));
    }

    private void writeCell(XSSFSheet sheet, int r, int l, Color color, Object value) {
        XSSFRow row = sheet.getRow(r);
        if (row == null) {
            row = sheet.createRow(r);
        }
        XSSFCell cell = row.getCell(l);
        if (cell == null) {
            cell = row.createCell(l);
        }
        cell.setCellValue(value.toString());
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        if (color == null) {
            color = new java.awt.Color(128, 0, 128);
        }
        style.setFillForegroundColor(new XSSFColor(color));
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(style);
    }

    private Map<Integer, SheetContent> readExcel(InputStream inp,boolean onlyHeader) throws Exception {
        Workbook wb = WorkbookFactory.create(inp);
        Map<Integer, SheetContent> sheetContents = Maps.newHashMap();
        int sheetCount = wb.getNumberOfSheets();
        for (int k = 0; k < sheetCount; k++) {
            sheetContents.put(k, readExcelFromSheed(wb.getSheetAt(k),onlyHeader));
        }
        return sheetContents;
    }


    public SheetContent readExcelFromSheed(Sheet sheet,boolean onlyHeader) {
        SheetContent sheetContent = new SheetContent();
        int lastRow = sheet.getLastRowNum();
        for (int i = 0; i < lastRow; i++) {
            Row row = sheet.getRow(i);
            int lastCellNum = row.getLastCellNum();
            Object value = "";
            List<Object> values = Lists.newArrayList();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        value = cell.getErrorCellValue();
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        value = cell.getArrayFormulaRange().formatAsString();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        value = cell.getNumericCellValue();
                        break;
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    default:
                        value = cell.getStringCellValue();

                }
                values.add(value);
            }
            if (i == 0) {
                sheetContent.setHeaders(values);
                if(onlyHeader) break;
            } else {
                sheetContent.addValue(values);
            }
        }
        return sheetContent;
    }
    @Data
    public static class SheetContent {
        private List<Object> headers= Lists.newArrayList();
        private List<Map<String,Object>> values=Lists.newArrayList();
        public void addValue(List<Object> cells){
            Map<String,Object> value=Maps.newHashMap();
            for (int i = 0; i < headers.size(); i++) {
                value.put(headers.get(i)+"",cells.get(i));
            }
            values.add(value);
        }
    }
}
