<%--
  Created by IntelliJ IDEA.
  User: zqj
  Date: 2017/11/8
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>查询资料(merge)</title>
    <style type="text/css">
        form{
            width:400px;
            height:280px;
            position:absolute;
            left: 50%;
            top: 50%;
            margin-left:-200px;
            margin-top:-140px;
            border:1px;
            line-height: 2em;
            align:center;
        }
    </style>
</head>
<body>
<div class="form">
<form action="${pageContext.request.contextPath}/controller/displaytextandmongocontent.action" method="post" id="searchalltable" >
    <div style="text-align: center;">
        <h2>搜索类型</h2>
    <label><input name="search" type="radio"   value="mongodb"  />查询mongodb数据</label>
    <label><input name="search" type="radio" value="text"  />查询文本数据</label>
    <br/>
    <td>输入内容:</td>
    <td>
        <input type="text" name="content" id="content">
        <br/><br>
        <button id="submitcontentAll" type="submit" >查询</button>
    </td>
</div>
</form>
</div >





</body>
</html>
