<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <title>...</title>
    <!--在IE浏览器中运行最新的渲染模式-->
    <meta http-equiv="X-UA-Compatible" content="IE-Edge">
    <!--初始化移动浏览器显示-->
    <meta name="viewport" content="width-device-width,inital-scale=1">
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="index.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
      <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
      <script type="text/javascript">
      var p = 101;
          var stop = 1;
          function run() {
              p += 4;
              $("div[class=bar]").css("width", p + "%");
              var timer = setTimeout("run()", 500);
              if (p >100&&stop<1) {         
                  p = 0;
              }
          }
          $('#BtnSubmit').click(function () {
              $('#myModal1').modal('show');
              p = 0;
              stop = 0;
              run();
              $('#UpLoad').submit();
              
          });
      </script>
</head>
<body>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" style="width:300px"> 
   <div class="modal-dialog">
       <div class="modal-content" >
           <span style="text-align:center;color:red">文件正在上传请勿刷新页面！</span><br />
          
           <div class="progress progress-striped active">
               <div class="bar">
               </div>
           </div>
       </div>
   </div>
</div>

         
</body>
</html>