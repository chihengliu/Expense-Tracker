<?php
require "init.php";
$id = $_POST["id"];
$name = $_POST["name"];
$category = $_POST["category"];
$amount = $_POST["amount"];
$description = $_POST["description"];
$sql_query = "insert into Personal (sid,Name,Category,Amount,Description) values('$id','$name','$category','$amount','$description');";
if (mysqli_query($con,$sql_query)){
  // echo "success";
   }
   else {
  // echo "fail";
   }
?>