<%--
  Created by IntelliJ IDEA.
  User: zqj
  Date: 2017/9/21
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>文本查询</title>
</head>
<body>
    <table width="100%" border=1  cellspacing="20">
        <tr>
            <td align="center" >title</td>
            <td align="center"  nowrap >mongodb链接</td>
        </tr>
        <c:forEach items="${itemsList }" var="item">
        <tr>
            <td  >${ item.content}</td>
            <td align="center"  ><a href="${pageContext.request.contextPath}/controller/linktomongodb/6.D4.7.2004-029229.action" >陈剑</a></td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
