<?php
require "init.php";
$json = json_decode(file_get_contents('php://input'),true);

$id = $json["id"];
$name = $json["name"];
$description = $json["description"];

$sql_query = "delete from Personal where sid='$id' and Name='$name';";

if (mysqli_query($con,$sql_query)){
   echo('success');
      }
      else {
      echo('fail');
      }
?>