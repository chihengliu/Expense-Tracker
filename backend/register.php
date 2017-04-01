<?php
require "init.php";

$username = $_POST["username"];
$password = $_POST["password"];
$sql_query = "insert into User_info (Name,Password) values('$username','$password');";
$query = mysqli_query($con,$sql_query); 
if ($query){
echo('success');
}
        else {
	echo ('fail');
	   }
	   ?>
	     