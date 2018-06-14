package com.controller;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class DownloadController
{
  @RequestMapping("/downloadFile")
  public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) throws IOException {
    String outFileName= (String) request.getSession().getAttribute("outFileName");//输出文件名
    System.out.println(outFileName);
    String outFilePath="E://upload"+File.separator+outFileName;
    File file=new File(outFilePath);
    byte[] body=null;
    InputStream inputStream=new FileInputStream(file);
    body=new byte[inputStream.available()];
    inputStream.read(body);
    HttpHeaders headers=new HttpHeaders();
   // headers.setContentDispositionFormData("attachment",outFileName);
    //针对中文乱码进行设置
    headers.add("Content-Disposition","attchement;filename="+new String(outFileName.getBytes("utf-8"),"iso-8859-1"));
    HttpStatus statusCode=HttpStatus.OK;
    ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>(body,headers,statusCode);
    return entity;
  }
}
