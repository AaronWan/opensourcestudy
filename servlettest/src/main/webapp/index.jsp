<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello World!</h2>

<h1>download file</h1>
<a href="down/big_excel_output_stream.do">a.txt</a>
<br>
<form method="post" action="/upload/upload.do" enctype="multipart/form-data">
  选择一个文件:
  <input type="file" name="uploadFile" />
  <br/><br/>
  <input type="submit" value="上传" />
</form>
</body>
</html>
