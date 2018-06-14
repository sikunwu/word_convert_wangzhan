package com.util;

import com.aspose.words.*;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataSet;
import com.aspose.words.net.System.Data.DataTable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;


public class WordUtil {

  public static Document oDoc;
  double _imgH;
  double _imgW;

  static {
    getLicense();
  }
  /**
   * 获取注册文件
   */
  public static void getLicense() {
    InputStream is;
    try {

      is=WordUtil.class.getClassLoader().getResourceAsStream("license.xml");
      License license = new License();
      license.setLicense(is);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  /**
   * 配合数据源，word模板+数据集合并成word
   * @param resource 散列的数据
   * @param resource2  子表数据
   * @param fileName  输入文件名
   * @param outFileName  输出文件名
   * @throws Exception
   */
  public void convertWord(Map<String,Object> resource,Map<String,Object> resource2,String fileName,String outFileName) throws Exception {
    OpenWithTemplate(fileName);
    DataSet ds=new DataSet();
    if (!resource.isEmpty())
    {
      DataTable table=new DataTable("JBQK");
      for (Map.Entry<String,Object> tableentry:resource.entrySet())
      {
        table.getColumns().add(tableentry.getKey());
      }
      DataRow dr=table.newRow();
      for (Map.Entry<String,Object> tableentry:resource.entrySet())
      {
        dr.set(tableentry.getKey(),tableentry.getValue());
      }
      table.getRows().add(dr);
      oDoc.getMailMerge().execute(table);
      InsertImg(oDoc,table);
    }
    if (!resource2.isEmpty())
    {
      for (Map.Entry<String,Object> tableentry:resource2.entrySet())
      {
        DataTable table=new DataTable(tableentry.getKey());
        System.out.println(tableentry.getKey());
        ArrayList<Map<String,Object>> rows= (ArrayList<Map<String, Object>>) tableentry.getValue();
        Iterator iterator=rows.iterator();
        while (iterator.hasNext())
        {
          Map<String,Object> map= (Map<String, Object>) iterator.next();
          for (Map.Entry<String,Object> row:map.entrySet())
          {
            table.getColumns().add(row.getKey());
          }
          DataRow dr=table.newRow();
          for (Map.Entry<String,Object> row:map.entrySet())
          {
            dr.set(row.getKey(),row.getValue());
          }
          table.getRows().add(dr);

        }
        //System.out.println(table.toString());
        ds.getTables().add(table);
        oDoc.getMailMerge().executeWithRegions(ds);
      }
    }


    oDoc.save(outFileName);//"E://自然资源登记簿NEW.docx"
    //oDoc.save(outFileName,SaveFormat.PDF);//"E://自然资源登记簿NEW.pdf"

  }

  /**
   * 配合数据源，word模板+数据集转pdf
   * @param resource 散列的数据
   * @param resource2  子表数据
   * @param fileName  输入文件名
   * @param outFileName  输出文件名
   * @throws Exception
   */
  public void convertPdf(Map<String,Object> resource,Map<String,Object> resource2,String fileName,String outFileName) throws Exception {
    OpenWithTemplate(fileName);
    DataSet ds=new DataSet();
    if (!resource.isEmpty())
    {
      DataTable table=new DataTable("JBQK");
      for (Map.Entry<String,Object> tableentry:resource.entrySet())
      {
        table.getColumns().add(tableentry.getKey());
      }
      DataRow dr=table.newRow();
      for (Map.Entry<String,Object> tableentry:resource.entrySet())
      {
        dr.set(tableentry.getKey(),tableentry.getValue());
      }
      table.getRows().add(dr);
      oDoc.getMailMerge().execute(table);
      InsertImg(oDoc,table);
    }
    if (!resource2.isEmpty())
    {
      for (Map.Entry<String,Object> tableentry:resource2.entrySet())
      {
        DataTable table=new DataTable(tableentry.getKey());
        System.out.println(tableentry.getKey());
        ArrayList<Map<String,Object>> rows= (ArrayList<Map<String, Object>>) tableentry.getValue();
        Iterator iterator=rows.iterator();
        while (iterator.hasNext())
        {
          Map<String,Object> map= (Map<String, Object>) iterator.next();
          for (Map.Entry<String,Object> row:map.entrySet())
          {
            table.getColumns().add(row.getKey());
          }
          DataRow dr=table.newRow();
          for (Map.Entry<String,Object> row:map.entrySet())
          {
            dr.set(row.getKey(),row.getValue());
          }
          table.getRows().add(dr);

        }
        //System.out.println(table.toString());
        ds.getTables().add(table);
        oDoc.getMailMerge().executeWithRegions(ds);
      }
    }


    //oDoc.save(outFileName);//"E://自然资源登记簿NEW.docx"
    oDoc.save(outFileName,SaveFormat.PDF);

  }

  /**
   * 无数据源，word转pdf
   * @param fileNmae  输入文件名
   * @param outFileName  输出文件名
   */
  public void wordToPdf(String fileNmae,String outFileName) {
    File file = new File(outFileName);
    FileOutputStream os = null;
    try {
      os = new FileOutputStream(file);
      Document doc = new Document(fileNmae);//"E://18.doc"

      doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      if (os!=null){
        try {
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }



  /**
   * 检验文件是否存在
   * @param strFileName  输入文件名
   */
  public static void OpenWithTemplate(String strFileName)
  {
    if (!strFileName.trim().equals("")&&strFileName.length()>0)
    {
      try {
        oDoc = new Document(strFileName);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 插入图片
   * @param doc
   * @param dt
   * @throws Exception
   */
  public void InsertImg(Document doc, DataTable dt) throws Exception {

    String imgPath = "";
    for (int i = 0; i < doc.getRange().getBookmarks().getCount() ; i++)
    {

      Bookmark mark = doc.getRange().getBookmarks().get(i);
      DocumentBuilder builder = new DocumentBuilder(doc);

      if (mark.getName().substring(0,4).equals("IMG_"))
      {

        imgPath=(String) dt.getRows().get(0).get(mark.getName());
        System.out.println(imgPath);
        if (imgPath != "")
        {
          File imgFile=new File(imgPath);
          if (imgFile.exists())
          {
            getImgSize(imgPath);
            builder.moveToBookmark(mark.getName());
            builder.insertImage(imgPath, RelativeHorizontalPosition.MARGIN,10,RelativeHorizontalPosition.MARGIN,1,_imgW,_imgH, WrapType.SQUARE);
          }
        }
      }
    }
  }

  /**
   * 获取图片大小
   * @param img 图片路径
   */
  public void getImgSize(String img) {

    try {
      BufferedImage image= ImageIO.read(new File(img));
      _imgW=image.getWidth();
      _imgH=image.getHeight();
      double rate=_imgH/_imgW;
      if (_imgW > 500)
      {
        _imgW = 500;
        _imgH = 500 * rate;
      }
      if (_imgH > 830)
      {
        _imgH = 830;
        _imgW = 830 / rate;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }




  }

  public void VerticalCellMerge(Document doc, int tableIndex, int rowIndex, int colIndex, int rowNum) throws Exception {
    NodeCollection tables =doc.getChildNodes(NodeType.TABLE,true);
    Table table = (Table) tables.get (tableIndex);
    String tmp = "", cellval = "";



    for (Row row:table.getRows())
    {
      int rIndex =table.getRows().indexOf(row);
      if (rIndex > rowIndex + rowNum)
        break;
      else if (rIndex > rowIndex)
      {
        Cell cell =row.getCells().get(colIndex);
        cellval = cell.toString(SaveFormat.TEXT).trim();

        if (tmp == "")
        {
          tmp = cellval;
        }
        if (tmp == cellval)
        {
          cell.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
        }
        else
        {
          cell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
        }
        tmp = cellval;
      }

    }
  }

  /*public static void main(String[] args) {
    Test test=new Test();
    Map<String,Object> map=test.resourcesTest();
    Map<String,Object> map2=test.resourcesTest2();
    try {
      test.readMap(map,map2,"E://自然资源登记簿.docx","E://自然资源登记簿NEW.docx");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }*/
}
