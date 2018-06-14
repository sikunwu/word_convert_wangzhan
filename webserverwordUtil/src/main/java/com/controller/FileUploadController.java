package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController
{
  @RequestMapping("toIndex")
  public String toIndex()
  {
    return "index.jsp";
  }

  /**
   * 处理上传文件逻辑
   * @param file  上传文件
   * @param request
   * @param convertType  格式转换类型
   * @return
   * @throws IllegalStateException
   * @throws IOException
   */
  @RequestMapping("/saveFile")
  public String saveFile(@RequestParam("filedata")MultipartFile file, HttpServletRequest request,Integer convertType) throws IllegalStateException,IOException
  {
    if (!file.isEmpty())
    {
      System.out.println(convertType);
      request.getSession().setAttribute("convertType",convertType);//保存转换类型，1为word+数据转pdf,2为word+数据转pdf,3为word转pdf,4为html转pdf
      System.out.println("获取上传文件的原名="+file.getOriginalFilename());
      String fileName=file.getOriginalFilename();
      //定义一个上传文件的目录
      String uploadfilePath="E://upload";//设置保存文件夹
      System.out.println(uploadfilePath+File.separator+fileName);
      //创建目标文件的对象
      File destFile=new File(uploadfilePath+File.separator+fileName);
      if (!destFile.getParentFile().exists())
      {
        destFile.mkdir();
      }
      file.transferTo(destFile);
      request.getSession().setAttribute("fileName",fileName);
      return "convertFile";
    }

    return "toIndex";
  }



}
