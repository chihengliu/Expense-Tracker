<?php
require "init.php";

$json = json_decode(file_get_contents('php://input'),true);

$id = $json["eid"];

$sql_query = "select * from EventSpending where eid='$id';";
$query = mysqli_query($con,$sql_query);

if ($query){
   while($row = mysqli_fetch_array($query)){
              $data[]=$row;
              }
              print(json_encode($data));
              }else{
                echo('fail');
                }

?>