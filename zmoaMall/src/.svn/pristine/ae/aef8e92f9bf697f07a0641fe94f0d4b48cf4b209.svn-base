<?php
if (!empty($_FILES)) {
	$tempFile = $_FILES['Filedata']['tmp_name'];
	$targetPath = $_SERVER['DOCUMENT_ROOT'] . $_REQUEST['folder'] . '/';
	$new_file_name = new_name( $_FILES['Filedata']['name']);
	$targetFile =  str_replace('//','/',$targetPath) . $new_file_name;
        //mkdir(str_replace('//','/',$targetPath), 0777, true);

		//防止中文文件名乱码
		move_uploaded_file($tempFile,iconv('utf-8','gbk', 'uploads/'.$new_file_name));

		//返回文件相对地址

echo get_relative_path($targetFile);



}


 function new_name($filename){
	$ext = pathinfo($filename);
	$ext = $ext['extension'];
	if ($ext=='rar'||$ext=='doc'||$ext=='zip') 
	{
	$name = basename($filename,$ext); 
	$name = md5($name.time()).'.'.$ext;
	return $name;
	}
 }


 function get_relative_path($path,$dir = 'uploads'){
	//return substr($path,strpos($path,$dir),strlen($path ));
	
	echo $path;
	
 }
?>