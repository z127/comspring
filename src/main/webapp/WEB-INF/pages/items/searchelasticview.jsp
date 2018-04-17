<%--
  Created by IntelliJ IDEA.
  User: zqj
  Date: 2017/9/21
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>模糊查询</title>
</head>
<body>
<form action="displayelasticyresult.action" method="post" id="search" >
<td >输入内容:</td>
<td>
    <input type="text" name="content" id="content">
    <br/>
    <button id="submit" type="submit">查询</button>
</td>

</form>
</body>
</html>
