package com.opensource.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.junit.Test;

import java.awt.Color;
import java.io.*;
import java.util.*;

/**
 * Created by Aaron on 25/11/2016.
 */
public class XSSFUtil {
    @Test
    public void xssfCreate1() throws Exception {

        Workbook wb = WorkbookFactory.create(new FileInputStream
                ("/Users/Aaron/Desktop/异常数据.xlsx"));

        Map<Integer, SheetContent> sheetContents = readExcel(wb,false);

        Workbook finalWb = new XSSFWorkbook();
        sheetContents.forEach((index, content)->{
            XSSFSheet sheet = (XSSFSheet) finalWb.createSheet(content.getSheetName()+"filter");
            writeSheet(sheet,content);
        });
        FileOutputStream bos=new FileOutputStream("/Users/Aaron/Desktop/异常数据3.xlsx");
        finalWb.write(bos);


    }

    private void writeSheet(XSSFSheet sheet, SheetContent content) {
        ArrayList<Object> notblack = Lists.newArrayList(379662,465454,484358,494830,525958,536526,407023,424637,424737,537645,43497,409572,434686,30655,278354,439861,218026,549073,547103,549432,105273,491151,452990);
        Set<Object> last= Sets.newHashSet();
        Set<Object> files=Sets.newHashSet();
        for (int i = 0; i < content.getHeaders().size(); i++) {
            writeCell(sheet,0,i,null,content.getHeaders().get(i));
        }

        int row=0;
        for (int i = 0; i < content.getValues().size(); i++) {
            Map<String, Object> contents=content.getValues().get(i);
            ArrayList<Object> temp = new ArrayList<>(contents.values());
            if(!notblack.contains(Double.valueOf(temp.get(0)+"").intValue())){
                continue;
            }
            last.add(((Double)temp.get(0)).intValue());
            files.add(temp.get(4));
            row++;
            for (int i1 = 0; i1 < temp.size(); i1++) {
                Object item=temp.get(i1);
                if(item instanceof Double){
                    if(((Double) item).intValue()==((Double) item).doubleValue()){
                        item=((Double) item).intValue();
                    }
                }
                writeCell(sheet,row,i1,null,item);
            }
        }
        System.out.println(last.size()+","+last);
        System.out.println(files.size()+","+files);

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
            color = new java.awt.Color(255, 255, 255);
        }
        style.setFillForegroundColor(new XSSFColor(color));
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(style);
    }

    private Map<Integer, SheetContent> readExcel(Workbook wb,boolean onlyHeader) throws Exception {

        Map<Integer, SheetContent> sheetContents = Maps.newHashMap();

        int sheetCount = wb.getNumberOfSheets();
        for (int k = 0; k < sheetCount; k++) {
            sheetContents.put(k, readExcelFromSheed(wb.getSheetAt(k),onlyHeader));
        }
        return sheetContents;
    }


    public SheetContent readExcelFromSheed(Sheet sheet,boolean onlyHeader) {
        SheetContent sheetContent = new SheetContent();
        sheetContent.setSheetName(sheet.getSheetName());
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
        private String sheetName;
        private List<Object> headers= Lists.newArrayList();
        private List<Map<String,Object>> values=Lists.newArrayList();
        public void addValue(List<Object> cells){
            Map<String,Object> value=Maps.newLinkedHashMap();
            for (int i = 0; i < headers.size(); i++) {
                value.put(headers.get(i)+"",cells.get(i));
            }
            values.add(value);
        }
    }
}
