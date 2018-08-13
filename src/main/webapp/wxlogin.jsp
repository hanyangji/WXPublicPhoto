<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>商户图片采集</title>

<!-- Bootstrap -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<button class="btn btn-primary btn-lg btn-block"   onclick="showActionSheet()">主要按钮</button>
<script type="text/javascript">
//点击事件，弹出选择摄像头和相册的选项  
function showActionSheet() {    
    var bts = [{    
        title: "拍照"    
    }, {    
        title: "从相册选择"    
    }];    
    plus.nativeUI.actionSheet({    
            cancel: "取消",    
            buttons: bts    
        },    
        function(e) {    
            if (e.index == 1) {    
                getImage();    
            } else if (e.index == 2) {    
                galleryImgs();    
            }    
        }    
    );    
}  
</script>
</body>
</html>