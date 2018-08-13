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
</head>
<body>
<div class="container-fluid">
<form class="form-horizontal">
<div class="form-group">
<table class="table table-striped">
<tr>
  <td>#</td>
  <td>商户号</td>
  <td>商户名</td>
  <td>上传时间</td>
  </tr>
<c:forEach items="${dataList }" var="data">
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</c:forEach>
  
</table>
</div>
</form>
</div>
</body>
</html>