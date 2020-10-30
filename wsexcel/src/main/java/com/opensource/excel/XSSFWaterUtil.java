package com.opensource.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aaron on 25/11/2016.
 */
public class XSSFWaterUtil {
    @Test
    public void xssfWrite() throws Exception {

        Workbook finalWb = new XSSFWorkbook();
        XSSFSheet sheet = (XSSFSheet) finalWb.createSheet(System.currentTimeMillis()+"");
        sheet.setDefaultColumnWidth(20);
        SheetContent content=new SheetContent();
        content.setHeaders(Lists.newArrayList("title","content"));
        Map<String, Object> temp = Maps.newHashMap();
        temp.put("title","jdk8之前为空判断使业务代码读起来比较费劲,对整体业务逻辑的理解增加困惑;" + "jdk8支持了 Optional 之后 ,使用我们可以非常轻松的将原本一大块的判断代码块变成一句话;");
        temp.put("content","左侧是自动换行");
        Map<String, Object> temp2 = Maps.newHashMap();
        temp2.put("title","POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));");
        temp2.put("content","左侧是自动换行");
        content.setValues(Lists.newArrayList(temp,temp2));
        writeSheet(sheet,content);
        FileOutputStream bos=new FileOutputStream("/Users/Aaron/Documents/code/study/opensourcestudy/wsexcel/src/main/resources/异常数据.xlsx");
        finalWb.write(bos);


    }
    private void writeSheet(XSSFSheet sheet, SheetContent content) {
        Set<Object> last= Sets.newHashSet();
        for (int i = 0; i < content.getHeaders().size(); i++) {
            writeCell(sheet,0,i,null,content.getHeaders().get(i));
        }

        int row=0;
        for (int i = 0; i < content.getValues().size(); i++) {
            Map<String, Object> contents=content.getValues().get(i);
            ArrayList<Object> temp = new ArrayList<>(contents.values());
            last.add((temp.get(0)));
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
            color = new Color(162, 187, 185);
        }
        style.setFillForegroundColor(new XSSFColor(color));
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setWrapText(true);
        cell.setCellStyle(style);
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

}
