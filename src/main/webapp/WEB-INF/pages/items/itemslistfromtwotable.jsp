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
<h1>查询结果：</h1>
<br/>
<h2>案卷集</h2>
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
            <td>ocr文件索引</td>
        </tr>
        <c:forEach items="${itemsListAnjuanji }" var="item">
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
                <td><a href="http://10.141.211.146:8888/group1/M00/00/00/Co3TklrUWBaAbOI-AAAUgqsp8uA567_big.rtf">ocr文件</a></td>>
            </tr>
        </c:forEach>
    </table>

<table width="100%" border=1>
    <h2>文件集</h2>
    <tr>
        <td>title</td>
        <td>filePath</td>
        <td>cdPath</td>
        <td>file1code</td>
        <td>person</td>
        <td>jpg文件索引</td>
        <td>tif文件索引</td>
        <td>ocr文件索引</td>
    </tr>
    <c:forEach items="${itemsListwenjianji }" var="item1">
        <tr>
            <td>${item1.title }</td>
                <%--<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
            <td>${item1.filePath }</td>
            <td>${item1.cdPath}</td>
            <td>${item1.fileCode }</td>
            <td>${item1.person }</td>
            <td><a href="http://10.141.211.142:8000/group1/M00/00/00/Co3Tj1m6MS6Afq-yAAMRgo7yI1I027.jpg">jpg文件</a></td>
            <td> <a href="http://10.141.211.142:8000/group1/M00/00/00/Co3Tj1nEf4WAKjZiAACjhGFI2ro885.tif">tif文件</a></td>
            <td><a href="http://10.141.211.142:8000/group1/M00/00/00/Co3Tj1nEgBSAFYaiAAAUgqsp8uA205.rtf">ocr文件</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
