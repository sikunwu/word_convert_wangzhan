package com.controller;

import com.db.Resource;
import com.util.HtmlUtil;
import com.util.WordUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

@Controller
public class ConvertController
{
  @RequestMapping("/convertFile")
  public String convertFile(HttpServletRequest request)
  {
    Integer convertType= (Integer) request.getSession().getAttribute("convertType");
    WordUtil wordUtil=new WordUtil();
    if (convertType==1)//根据转换类型标志进行转换
    {
      Resource resource=new Resource();//模拟数据源
      Map<String,Object> map=resource.resourcesTest();
      Map<String,Object> map2=resource.resourcesTest2();
      String fileName= (String) request.getSession().getAttribute("fileName");
      System.out.println(fileName.toString());
      String filePath="E://upload"+File.separator+fileName;
      String outFileName=getOutFileName(fileName);
      request.getSession().setAttribute("outFileName",outFileName);
      String outFilePath="E://upload"+File.separator+outFileName;
      try {
        wordUtil.convertWord(map,map2,filePath,outFilePath);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else if (convertType==2)
    {
      Resource resource=new Resource();
      Map<String,Object> map=resource.resourcesTest();
      Map<String,Object> map2=resource.resourcesTest2();
      String fileName= (String) request.getSession().getAttribute("fileName");
      System.out.println(fileName.toString());
      String filePath="E://upload"+File.separator+fileName;
      String outFileName=getOutFileName2(fileName);
      request.getSession().setAttribute("outFileName",outFileName);
      String outFilePath="E://upload"+File.separator+outFileName;
      try {
        wordUtil.convertPdf(map,map2,filePath,outFilePath);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else if (convertType==3)
    {
      String fileName= (String) request.getSession().getAttribute("fileName");
      System.out.println(fileName.toString());
      String filePath="E://upload"+File.separator+fileName;
      String outFileName=getOutFileName2(fileName);
      request.getSession().setAttribute("outFileName",outFileName);
      String outFilePath="E://upload"+File.separator+outFileName;
      try {
        wordUtil.wordToPdf(filePath,outFilePath);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else if (convertType==4)
    {
      HtmlUtil htmlUtil=new HtmlUtil();
      String fileName= (String) request.getSession().getAttribute("fileName");
      System.out.println(fileName.toString());
      String filePath="E://upload"+File.separator+fileName;
      String outFileName=getOutFileName2(fileName);
      request.getSession().setAttribute("outFileName",outFileName);
      String outFilePath="E://upload"+File.separator+outFileName;
      try {
        htmlUtil.htmlToPdf(filePath,outFilePath);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    //request.getRequestDispatcher("/DownloadServlet").forward(request,response);
    return "downloadFile";
  }



  public String getOutFileName(String fileName)
  {
    String fileNameNew=fileName.substring(0,fileName.lastIndexOf("."));
    String suffix=fileName.substring(fileName.lastIndexOf("."),fileName.length());
    fileNameNew=fileNameNew+"New";
    String outFileName=fileNameNew+suffix;

    return outFileName;
  }

  public static String getOutFileName2(String fileName)
  {
    String fileNameNew=fileName.substring(0,fileName.lastIndexOf("."));
    String suffix=".pdf";
    fileNameNew=fileNameNew+"New";
    String outFileName=fileNameNew+suffix;

    return outFileName;
  }

}
