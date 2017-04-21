<?php
require "init.php";
$json = json_decode(file_get_contents('php://input'),true);

$id = $json["id"];
$name = $json["name"];
$category = $json["category"];
$amount = $json["amount"];
$description = $json["description"];
$date = $json["date"];

$sql_query = "update Personal set Category='$category',Amount='$amount',Description='$description', Date='$date' where sid='$id' and Name='$name';";

if (mysqli_query($con,$sql_query)){
   echo('success');
   }
   else {
   echo('fail');
   }
?>