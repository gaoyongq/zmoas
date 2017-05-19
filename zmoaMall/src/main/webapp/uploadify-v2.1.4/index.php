<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>wenjianshangchuan</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadify").uploadify({
		'uploader'       : 'swf/uploadify.swf',
		'script'         : 'uploadify.php',
		'cancelImg'      : 'cancel.png',
		'folder'         : 'uploads/<?=date("Ymd",strtotime("now"))?>/',
		'queueID'        : 'fileQueue',
		'fileExt'        : '*.rar;*.doc;*.tar', //允许文件上传类型,和fileDesc一起使用.
		'fileDesc'       : '*.rar;*.doc;*.tar',  //将不允许文件类型,不在浏览对话框的出现.
		'auto'           : true,
		'multi'          : false,
		'onComplete':function(event,queueId,fileObj,response,data){
			$('#info').text($('#info').text() + ''+response+'');//在页面上显示文件相对路径
		}
	});
});
</script>
</head>
<body>
<div id="container">
	<div id="fileQueue"></div>
	<div class="btn-group">
	<input type="file" name="uploadify" id="uploadify" />
	<a href="javascript:jQuery('#uploadify').uploadifyUpload()" class="btn">上传</a>
	<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()"class="btn">取消上传</a>
	</div>

	<div id="2info"><textarea name="comment" id="info" tabindex="4" rows="22" cols="46"></textarea></div>
</div>
</body>
</html>
