package download;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UploadServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    RequestContext req = new ServletRequestContext(request);
    if (FileUpload.isMultipartContent(req)) {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload fileUpload = new ServletFileUpload(factory);
      fileUpload.setFileSizeMax(1024 * 1024 * 1024);

      List items = new ArrayList();
      try {
        items = fileUpload.parseRequest(request);
      } catch (Exception e) {

      }
      Iterator it = items.iterator();
      while (it.hasNext()) {
        FileItem fileItem = (FileItem) it.next();
        if (fileItem.getName() != null && fileItem.getSize() != 0) {
            try {
              System.out.println(fileItem.getFieldName() + " "
                + fileItem.getName() + " " + fileItem.isInMemory()
                + " " + fileItem.getContentType() + " "
                + fileItem.getSize());
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
      }
    }


}

public void upload(HttpEntity httpEntity) throws IOException{
  HttpPost request = new HttpPost("http://localhost:8080/sdkServlet");
  request.setEntity(httpEntity);
  request.addHeader("Content-Type","multipart/form-data; boundary=--------------------HV2ymHFg03ehbqgZCaKO6jyH");

  DefaultHttpClient httpClient = new DefaultHttpClient();
  HttpResponse response =httpClient.execute(request);
  InputStream is = response.getEntity().getContent();
  BufferedReader in = new BufferedReader(new InputStreamReader(is));
  StringBuffer buffer = new StringBuffer();
  String line = "";
  while ((line = in.readLine()) != null) {
    buffer.append(line);
  }
  System.out.println("发送消息收到的返回："+buffer.toString());

}

}
