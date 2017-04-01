<?php
require "init.php";
$id = $_POST["id"];
$name = $_POST["name"];
$sql_query = "delete from Personal where sid='$id' and Name='$name';";
if (mysqli_query($con,$sql_query)){
   echo('success');
   }
   else {
   echo('fail');
   }
?>