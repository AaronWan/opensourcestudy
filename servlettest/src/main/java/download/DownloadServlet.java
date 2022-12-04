package download;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DownloadServlet extends HttpServlet {

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    //*告诉客户端这个文件不是解析 而是以附件的形式下载
    response.setHeader("Content-Disposition", "attachment;filename=测试大文件.xls");
    response.setHeader("Content-Length", "1024000000");
    //3.获得该文件的输入流
    //获得输出流---通过response获得的输出流  用于向客户端写内容
    ServletOutputStream out = response.getOutputStream();
    Workbook finalWb = new XSSFWorkbook();
    finalWb.write(out);
    for (int i = 0; i < 20; i++) {
      XSSFSheet sheet = (XSSFSheet) finalWb.createSheet("big_export_test"+i);
      SheetContent content=new SheetContent();
      content.setHeaders(Lists.newArrayList("测试序号"));
      content.addValue(Lists.newArrayList(i));
      writeSheet(sheet,content);
    }

    out.close();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doGet(request, response);
  }


  @Data
  public static class SheetContent {
    private String sheetName;
    private List<Object> headers = Lists.newArrayList();
    private List<Map<String, Object>> values = Lists.newArrayList();

    public void addValue(List<Object> cells) {
      Map<String, Object> value = Maps.newLinkedHashMap();
      for (int i = 0; i < headers.size(); i++) {
        value.put(headers.get(i) + "", cells.get(i));
      }
      values.add(value);
    }
  }

  private static void writeSheet(XSSFSheet sheet, SheetContent content) {
    ArrayList<Object> notblack = Lists.newArrayList(379662,
      465454,
      484358,
      494830,
      525958,
      536526,
      407023,
      424637,
      424737,
      537645,
      43497,
      409572,
      434686,
      30655,
      278354,
      439861,
      218026,
      549073,
      547103,
      549432,
      105273,
      491151,
      452990);
    Set<Object> last = Sets.newHashSet();
    Set<Object> files = Sets.newHashSet();
    for (int i = 0; i < content.getHeaders().size(); i++) {
      writeCell(sheet, 0, i, null, content.getHeaders().get(i));
    }

    int row = 0;
    for (int i = 0; i < content.getValues().size(); i++) {
      Map<String, Object> contents = content.getValues().get(i);
      ArrayList<Object> temp = new ArrayList<>(contents.values());
      if (!notblack.contains(Double.valueOf(temp.get(0) + "").intValue())) {
        continue;
      }
      last.add(((Double) temp.get(0)).intValue());
      files.add(temp.get(4));
      row++;
      for (int i1 = 0; i1 < temp.size(); i1++) {
        Object item = temp.get(i1);
        if (item instanceof Double) {
          if (((Double) item).intValue() == ((Double) item).doubleValue()) {
            item = ((Double) item).intValue();
          }
        }
        writeCell(sheet, row, i1, null, item);
      }
    }
  }

  private static void writeCell(XSSFSheet sheet, int r, int l, java.awt.Color color, Object value) {
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

}
