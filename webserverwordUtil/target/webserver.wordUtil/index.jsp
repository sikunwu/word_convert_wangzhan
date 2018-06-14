<%@page language="java" pageEncoding="utf-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Test</title>
  <style>
      #nav{
        position: absolute;
      }
      div ul li {
        float: left;
        list-style-type: none;
        padding-left: 10px;
      }
      div ul li a {
        display:block;
        width: 200px;
        height: 40px;
        color: darkgray;
        text-align: center;
        line-height: 40px;
        background: aliceblue;
        text-decoration: none;
      }
      div ul li a:hover{
        background: aquamarine;
      }
  </style>
</head>
<body>
<header style="background-color: darkgray;height: 170px">
  <div id="header" style="height: 10px;">
    <div id="logo_text" style="width: 600px;">
      <h1 style="padding-top: 75px;margin-left: 100px;color: white;font-size: 26px">格式转换</h1>
    </div>
    <div id="nav" style="margin-top: 50px;padding: 0px">
      <ul>
        <li><a href="word_shuju_word.jsp">word+数据转word</a></li>
        <li><a href="word_shuju_pdf.jsp">word+数据转pdf</a></li>
        <li><a href="word_to_pdf.jsp">word转pdf</a></li>
        <li><a href="html_to_pdf.jsp">html转pdf</a></li>
      </ul>
    </div>
  </div>
</header>

</body>
</html>
