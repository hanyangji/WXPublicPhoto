<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>商户上传信息</title>
<!-- Bootstrap -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="shortcut icon" href="demo/images/favicon.ico">
<link rel="stylesheet" href="demo/styles/main.css">
<link rel="stylesheet" href="lib/themes/default.css" id="theme_base">
<link rel="stylesheet" href="lib/themes/default.date.css" id="theme_date">
<link rel="stylesheet" href="lib/themes/default.time.css" id="theme_time">

<style>
.col-center-block {
    float: none;
    display: block;
    margin-left: 10px;
    margin-right: auto;
}
.col-center-btn {
    float:right;
    padding-right:5px
}
.container{
	padding-top:5px;
}
</style>

</head>
<body>
<div class="container">
<form class="form-horizontal" action="queryMchtInfo.do" >

<div class="form-group">
<div class="col-center-block">
<input id="date_demo__formats--a" class="fieldset__input" style="width:30%;" name="start" type=text placeholder="起始时间">
-- <input id="date_demo__formats--aa" class="fieldset__input" style="width:30%" name="stop" type=text placeholder="结束时间">
<div class="col-center-btn">
<input type="hidden" id="openid" name="openId" value="${openid}"/>

<button type="submit" id="find" class="btn btn-default btn-lg">
  <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询
</button>
</div>
</div>
<table class="table table-striped">
<tr style="text-align: center">
  <td>#</td>
  <td><font size="2px">商户号</font></td>
  <td><font size="2px">商户名</font></td>
  <td><font size="2px">上传时间</font></td>
  </tr>
<c:forEach items="${dataList}" var="data" varStatus="j">
<tr>
<td><font size="2px">${j.count}</font></td>
<td><font size="2px">${data.mchtid}</font></td>
<td><font size="2px">${data.mchtname}</font></td>
<td><font size="2px">${data.updatetime}</font></td>

</tr>
</c:forEach>
</table>
</div>
</form>
</div>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script>jQuery||document.write('<script src="tests/jquery.2.0.0.js"><\/script>')</script>
    <script src="lib/picker.js"></script>
    <script src="lib/picker.date.js"></script>
    <script src="lib/picker.time.js"></script>
    <script src="lib/legacy.js"></script>
    <script src="demo/scripts/main.js"></script>
<script type="text/javascript">

</script>
</body>
</html>