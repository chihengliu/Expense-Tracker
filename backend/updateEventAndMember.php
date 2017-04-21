<?php
require "init.php";

$json = json_decode(file_get_contents('php://input'), true);

$id = $json["id"];
//echo($id);
$name = $json["name"];
$description = $json["description"];
$array = $json["members"];

$sql_query = "update Eventlist set Name = '$name',Description = '$description' where EID = '$id';";
mysqli_query($con,$sql_query);

print($id);

//if (mysqli_query($con,$sql_query)){
//   // echo "success";
//    }
//    else {
//   // echo "fail";
//    }

$sql_delete = "delete from EventMember where Event_ID = '$id';";
mysqli_query($con,$sql_delete);

 foreach($array as $item) {
         //      print($item);
	       $insert = "insert into EventMember (Event_ID, User_Name) values('$id', '$item');";
                     mysqli_query($con,$insert);
                            }

?>