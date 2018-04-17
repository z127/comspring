<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <title>查询</title>
    <style type="text/css">
        login{
            width:400px;
            height:280px;
            position:absolute;
            left: 50%;
            top: 50%;
            border:1px;
            background-color:red;
            align:center;
        }

        form{
            width:300px;
            height:160px;
            position:relative;
            left:50%;
            top:50%;


        }
    </style>
</head>

<body >
<div class="login">
    <div class="form">
        <form action="displayresult.action" method="post" id="search"  runat="server" style="height: 100%">

            <br>搜索类型<br />
            <label><input name="Fruit" type="radio"   value="OWNER"  />OWNER</label>
            <label><input name="Fruit" type="radio" value="OTHEROWNER"  />OTHEROWNER </label>
            <label><input name="Fruit" type="radio" value="HOUSEPLACE"  />HOUSEPLACE </label>

            <br />
            <label><input name="search" type="radio"   value="fuzzy"  />模糊查询</label>
            <label><input name="search" type="radio" value="accurate"  />精确查询 </label>
            <br/>
            <td>输入内容:</td>
            <td>
                <input type="text" name="content" id="content">
                <br/>
                <button id="submit" type="submit">查询</button>
            </td>


        </form>
    </div>
</div>
</body>


</html>