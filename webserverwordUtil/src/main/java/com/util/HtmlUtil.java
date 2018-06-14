package com.util;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.LoadOptions;
import com.aspose.words.SaveFormat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class HtmlUtil {

  //private static InputStream license;

  static {
    getLicense();
  }

  /**
   * 获取license
   *
   * @return
   */
  public static void getLicense() {
    InputStream is;
    try {
      is=HtmlUtil.class.getClassLoader().getResourceAsStream("license.xml");
      License license = new License();
      license.setLicense(is);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * html转pdf
   * @param fileName  输入文件名
   * @param outFileName 输出文件名
   */
  public void htmlToPdf(String fileName,String outFileName)
  {

    LoadOptions loadOptions = new LoadOptions();
    loadOptions.setLoadFormat(com.aspose.words.LoadFormat.HTML);
    Document doc = null;
    try {
      doc = new Document(fileName, loadOptions);//"E://index.html"
      doc.save(outFileName, SaveFormat.PDF);//"E://321.pdf"
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
