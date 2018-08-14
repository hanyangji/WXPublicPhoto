<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
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
<%-- 	<div>用户昵称${info.nickname}</div>--%>
	<%-- <div ><input id="openid" value="${info.openid}">用户openid:${info.openid}</div>  --%>
	
	<input type="hidden" id="openid" value="${info.openid}"/>
<div class="container">
  <form class="form-horizontal" id="myform">
  <div class="form-group">
    <label for="inputEmail3" class="col-xs-12 control-label"></label>
    <div class="col-xs-9">
      <input type="text" class="form-control input-lg"  onchange="chadnge(this.value)"  placeholder="商户号" id="mchtid" name="mchtid" maxlength="15" onkeyup="value=value.replace(/[\W]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
      
    </div>
   <button type="button" id="find" class="col-xs-2 btn btn-info btn-lg"><a href="queryMchtInfo.do?openId=${info.openid}">查看</a></button>
  </div>
  <div class="form-group">
    <div class="col-xs-12">
      <input type="text" class="form-control input-lg" onchange="chadnge2(this.value)" placeholder="商户名称" id="mchtname" name="mchtname" maxlength="100">
    </div>
  </div>
  <!--  <div style="float:right;margin:1px">-->
  <table >
  <tr style="text-align: center;">
  <td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px"  id="wxlogo" onclick='chadngeimg1("1")'></td>
  <td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px" id="door" onclick='chadngeimg2("1")'></td>
  <td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px" id="activity" onclick='chadngeimg3("1")'></td>
  <td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px" id="alilogo" onclick='chadngeimg4("1")'></td>
  </tr>
   <tr >
  <td class="col-md-3"><p><font size="1px">带微信LOGO的收银台照片</font></p></td>
  <td class="col-md-3"><p><font size="1px">带地推人员和门头的合照</font></p></td>
  <td class="col-md-3"><p><font size="1px">地推人员和摇摇乐活动照</font></p></td>
  <td class="col-md-3"><p><font size="1px">支付宝LOGO收银台照片</font></p></td>
  </tr> 
  <tr style="text-align: center;">
    <td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px" id="platform" onclick='chadngeimg5("1")'></td>  
  	<td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px" id="wxbusiness" onclick='chadngeimg6("1")'></td>
    <td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px" id="wxdoor" onclick='chadngeimg7("1")'></td>
    <td class="col-md-3" ><img src="./img/timg.jpeg" alt="..." class="img-thumbnail" style="width: 100%; height: 100%; max-height: 60px;max-width: 60px; min-width: 60px; min-height: 60px" id="wxinside" onclick='chadngeimg8("1")'></td>
  </tr>
    <tr style="padding:1px">
  <td class="col-md-3" ><p><font size="1px">入驻主流餐饮平台展示照</font></p></td>
  <td class="col-md-3" style="text-align: center;"><p><font size="1px">营业执照</font></p></td>
  <td class="col-md-3" style="text-align: center;"><p><font size="1px">门头照片</font></p></td>
  <td class="col-md-3" style="text-align: center;"><p><font size="1px">内部经营照片</font></p></td>
  </tr>
  </table>
  
<!--     <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <button class="ui-btn-lg-nowhole ui-btn-primary" onclick="upload()">拍照</button>
        </label>
      </div>
    </div>
  </div> -->  
</form>
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button class="btn btn-primary btn-lg btn-block" onclick="saveImageToDisk()" id="sub" disabled>确定</button>
    </div>
  </div>
</div>
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
 <p ><span style="color:red;">图片要求:1、带支付宝LOGO收银台照片:要求能看到收单机构的POS机具或二维码牌,必须找事具有支付宝LOGO和"推荐使用支付宝"露出的物料和红包码物料,红包码物料必须是规范的张贴在收银台或者墙上,不允许摆拍。
  2、带微信LOGO收银台照片:要求能看到收单机构的POS机具或二维码牌,必须展示具有微信支付LOGO露出的物料</span></p>
</div>
</div>
<!-- <div class="ui-form ui-border-t">
        <div class="ui-form-item ui-border-b">
            <label>
                商户号
            </label>
            <input type="text" placeholder="商户号" id="mchtid" name="mchtid">
        </div>
        <div class="ui-form-item ui-form-item-link ui-border-b">
            <label>
                商户名称
            </label>
            <input type="text" placeholder="商户名称" id="mchtname" name="mchtname">
        </div>
        <div class="ui-btn-wrap">
        <button class="ui-btn-lg-nowhole ui-btn-primary" onclick="upload()">拍照</button>
        </div>
        <div class="ui-btn-wrap">
            <button class="ui-btn-lg-nowhole ui-btn-primary" onclick="saveImageToDisk()">
                确定
            </button>
        </div>
        <div id="alert"></div>
</div> -->



<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript">
var serverMap={};


$("#wxlogo").click(function(){
	choosePic();
})
$("#door").click(function(){
	 wx.chooseImage({
	        count: 1, // 默认9
	        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	            wxUploadImg(localIds,"door");//调用上传递归函数
	            	$("#door").attr("src",localIds);
	        }
	    });
})
$("#activity").click(function(){
	 wx.chooseImage({
	        count: 1, // 默认9
	        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	            wxUploadImg(localIds,"activity");//调用上传递归函数
	            	$("#activity").attr("src",localIds);
	        }
	    });
})
$("#alilogo").click(function(){
	 wx.chooseImage({
	        count: 1, // 默认9
	        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	            wxUploadImg(localIds,"alilogo");//调用上传递归函数
	            	$("#alilogo").attr("src",localIds);
	        }
	    });
})
$("#platform").click(function(){
	 wx.chooseImage({
	        count: 1, // 默认9
	        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	            wxUploadImg(localIds,"platform");//调用上传递归函数
	            	$("#platform").attr("src",localIds);
	        }
	    });
})
$("#wxbusiness").click(function(){
	 wx.chooseImage({
	        count: 1, // 默认9
	        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	            wxUploadImg(localIds,"wxbusiness");//调用上传递归函数
	            	$("#wxbusiness").attr("src",localIds);
	        }
	    });
})
$("#wxdoor").click(function(){
	 wx.chooseImage({
	        count: 1, // 默认9
	        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	            wxUploadImg(localIds,"wxdoor");//调用上传递归函数
	            	$("#wxdoor").attr("src",localIds);
	        }
	    });
})
$("#wxinside").click(function(){
	 wx.chooseImage({
	        count: 1, // 默认9
	        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	            /* lrz(localIds);
	            .then(function (rst){
	            	alert(rst); */
	            	wxUploadImg(localIds,"wxinside");//调用上传递归函数
	            	$("#wxinside").attr("src",localIds);
	            /* })
	            .catch(function(err){
	            	alert("err")
	            })
	            .always(function(){
	            	
	            }) */
	        }
	    });
})
var localIds = null;
// 上传序号
var idx = 0;
var serverIds='';

function choosePic() {
    wx.chooseImage({
        count: 1, // 默认9
        sizeType: [ 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            wxUploadImg(localIds,"wxlogo");//调用上传递归函数
            	$("#wxlogo").attr("src",localIds);
        }
    });
}
function wxUploadImg(localIds,imgFlag){
    wx.uploadImage({//获取图片媒体ID
        localId: localIds.toString(),  // 需要上传的图片的本地ID
        isShowProgressTips: 1, // 默认为1，显示进度提示
        success: function (res) {//获取成功
            // 上传序号，上传一张 累计 +1 
            /* idx++ */
            //存储图片媒体ID，用，号分割
            serverIds+=res.serverId+',';
        		serverMap[imgFlag]=res.serverId;
            
        },
        fail: function(res){//获取多媒体id失败 返回错误代码
            alert("上传失败，msg："+JSON.stringify(res));
        }
    });
}
function saveImageToDisk(){
	var str='';
	str = JSON.stringify(serverMap); 
	$.ajax({
        url: "saveImageToDisk.do",
        type: 'POST',
        dataType: 'json',
        data: {
        		mediaIdStr:serverIds,
        		mchtid:$("#mchtid").val(),
        		mchtname:$("#mchtname").val(),
        		openId:$("#openid").val(),
        		servermap:str,
        	},
        	success:function(data){
        		serverIds='';
        		alert("保存成功!");
        		window.location.href = "/WXPublicPhoto/wxLogin"
        	},
        	error:function(data){
        		alert("保存失败!")
        	}
    })
}


$(document).ready(function () { 
	/* function upload(){ */
	var currurl = decodeURIComponent(location.href.split('#')[0]);
    $.ajax({
        url: "jsapisign.do",
		dataType : 'json',
        type: "post",
        data: {
            'url': currurl
        },
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        async: true,
        success: function (data) {
            wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: data.appid, // 必填，公众号的唯一标识
                timestamp: data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.nonceStr, // 必填，生成签名的随机串
                signature: data.signature,// 必填，签名，见附录1
                jsApiList: ["chooseImage","uploadImage"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                
            });
            wx.ready(function() {
            		/* choosePic(); */
			}); // end ready
			
			wx.error(function(data) {
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
				alert("config信息验证失败");
			});
			
        },
    })
/* } */
 });
 	var a = '';
	var b = '';
	var c = '';
	var d = '';
	var f = '';
	var g = '';
	var h = '';
	var i = '';
	var j = '';
	var k = '';
	function chadnge(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		a = e;
		if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
			$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
			if(x!=15){
			alert("商户号格式错误!请输入15位商户号");
			}
		}
	}

	function chadnge2(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		b = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg1(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		c = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg2(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		d = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg3(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		f = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg4(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		g = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg5(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		h = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg6(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		i = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg7(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		j = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	function chadngeimg8(e) {
		var x=document.getElementById("mchtid").value.length;
		var val=$("#mchtid").val();
		k = e;
			if(a != '' && b != '' && c != ''&& d != ''&& f != ''&& g != ''&& h != ''&&x==15&& i != ''&& j != ''&& k != '') {
				$("#sub").removeAttr("disabled");
		}else{
			$("#sub").prop("disabled","disabled");
		}
	}
	

</script>
</body>
</html>