<?php
require "init.php";
$json = json_decode(file_get_contents('php://input'),true);

$username = $json["username"];
$password = $json["password"];

$sql_query = "insert into User_info (Name,Password) values('$username','$password');";
$query = mysqli_query($con,$sql_query);
if ($query){
echo('success');
}
        else {
        echo ('fail');
           }
           ?>
	     