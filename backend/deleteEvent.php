<?php

require "init.php";

$json = json_decode(file_get_contents('php://input'), true);

$id = $json["id"];

print($id);

$sql_query = "delete from EventSpending where eid = '$id';";
$query = mysqli_query($con,$sql_query);

if ($query){
echo('spending success');
}
else{
echo('spending fail');
}


$sql_query2 = "delete from Eventlist where EID = '$id';";

$query2 = mysqli_query($con,$sql_query2);

if ($query2){
echo('list success');
}
else{
echo('list fail');
}


?>