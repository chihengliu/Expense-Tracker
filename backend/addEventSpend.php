<?php

require "init.php";

$json = json_decode(file_get_contents('php://input'),true);

$id = $json["id"];
$amount = $json["amount"];
$name = $json["name"];
$category = $json["category"];
$description = $json["description"];
$eid = $json["eid"];
$date = $json["date"];
$sql_query = "insert into EventSpending (eid,sid,Name,Category,Amount,Description,Date) values ('$eid','$id','$name','$category','$amount','$description','$date');";

$query = mysqli_query($con,$sql_query);

if ($query){
echo('success');
}
else {
echo('fail');
}

?>