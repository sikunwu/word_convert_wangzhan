package com.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resource {
  /**
   * 数据源
   * @return
   */
  public Map<String,Object> resourcesTest()
  {

    Map<String,Object> JBQK=new HashMap<String, Object>();
    JBQK.put("DYH","2018918293891283912");
    JBQK.put("DJJG","晋江");
    JBQK.put("IMG_1","E://303.jpg");


    return JBQK;
  }

  public Map<String,Object> resourcesTest2()
  {
    Map<String,Object> mapMaxBig=new HashMap<String, Object>();

    List<Map<String,Object>> BHQK=new ArrayList<Map<String, Object>>();

    Map<String,Object> row1=new HashMap<String, Object>();
    row1.put("BHYY","变化原因");
    row1.put("BHNR","变化内容是什么呢");
    row1.put("DJSJ","2017-01-01 12:30:45");
    row1.put("DBR","甘建峰");

    Map<String,Object> row2=new HashMap<String, Object>();
    row2.put("BHYY","变化原因");
    row2.put("BHNR","变化内容是什么呢");
    row2.put("DJSJ","2017-01-01 12:30:45");
    row2.put("DBR","甘建峰");

    Map<String,Object> row3=new HashMap<String, Object>();
    row3.put("BHYY","变化原因");
    row3.put("BHNR","变化内容是什么呢");
    row3.put("DJSJ","2017-01-01 12:30:45");
    row3.put("DBR","甘建峰");

    BHQK.add(row1);
    BHQK.add(row2);
    BHQK.add(row3);
    mapMaxBig.put("BHQK",BHQK);

    List<Map<String,Object>> ZRZK=new ArrayList<Map<String, Object>>();

    Map<String,Object> zRow1=new HashMap<String, Object>();
    zRow1.put("LX",1);
    zRow1.put("LB","类别");
    zRow1.put("MJ",120);

    Map<String,Object> zRow2=new HashMap<String, Object>();
    zRow2.put("LX",1);
    zRow2.put("LB","类别");
    zRow2.put("MJ",120);

    Map<String,Object> zRow3=new HashMap<String, Object>();
    zRow3.put("LX",1);
    zRow3.put("LB","类别");
    zRow3.put("MJ",120);

    ZRZK.add(zRow1);
    ZRZK.add(zRow2);
    ZRZK.add(zRow3);
    mapMaxBig.put("ZRZK",ZRZK);



    return mapMaxBig;
  }
}
