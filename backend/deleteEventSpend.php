<?php

require "init.php";

$json = json_decode(file_get_contents('php://input'), true);

$sid = $json["sid"];
$eid = $json["eid"];


$sql_query = "delete from EventSpending where eid = '$eid' and sid = '$sid';";

$query = mysqli_query($con,$sql_query);

if ($query){
echo('delete event spending success');
}
else {
echo('delete event spending fail');
}

?>