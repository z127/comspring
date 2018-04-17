<%--
  Created by IntelliJ IDEA.
  User: zqj
  Date: 2017/9/12
  Time: 16:08
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询结果</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/personform.action" method="post">
<a>查询结果：</a>
    <table width="100%" border=1>
        <tr>
            <td>OWNER</td>
            <td>OTHEROWNER</td>
            <td>OLDOWNER</td>
            <td>HOUSEPLACE</td>
            <td>GATHERDATE</td>
            <td>PIGEONHOLEDATE</td>
            <td>png文件索引</td>
            <td>tar文件索引</td>
            <td>rtf文件</td>
        </tr>
        <c:forEach items="${itemsList }" var="item">
            <tr>
                <td>${item.OWNER }</td>
                <%--<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                <td>${item.OTHEROWNER }</td>
                <td>${item.OLDOWNER}</td>
                <td>${item.HOUSEPLACE }</td>
                <td>${item.GATHERDATE }</td>
                <td>${item.PIGEONHOLEDATE }</td>
                <td><a href="http://10.141.211.146:8888/group1/M00/00/00/Co3TklrTZVCAEhJvABQGCGl_h9U874.png">png文件</a></td>
                <td> <a href="http://10.141.211.146:8888/group1/M00/00/00/Co3TklrTSKeAcpR5AAUkKwe5sE4_big.tar.gz">tar文件</a></td>
                <td><a href="http://10.141.211.146:8888/group1/M00/00/00/Co3TklrUWBaAbOI-AAAUgqsp8uA567_big.rtf">rtf文件</a></td>>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
