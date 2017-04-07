<?php
require "init.php";
$id = $_POST["id"];
$name = $_POST["name"];
$category = $_POST["category"];
$amount = $_POST["amount"];
$description = $_POST["description"];
$date = $_POST["date"];
$sql_query = "update Personal set Category='$category',Amount='$amount',Description='$description', Date='date' where sid='$id' and Name='$name';";
if (mysqli_query($con,$sql_query)){
   echo('success');
   }
   else {
   echo('fail');
   }
?>