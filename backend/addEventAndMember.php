<?php
require "init.php";

$json = json_decode(file_get_contents('php://input'), true);

$name = $json["name"];
$description = $json["description"];
$array = $json["members"];

$sql_query = "insert into Eventlist (Name,Description) values('$name','$description');";
mysqli_query($con,$sql_query);

//if (mysqli_query($con,$sql_query)){
//   // echo "success";
//    }
//    else {
//   // echo "fail";
//    }

$query = "select Max(EID) as maxeid from Eventlist;";

$result = mysqli_query($con, $query);

if ($result){
   while($row=mysqli_fetch_array($result)){
   $data[]=$row;
   }
}

$js = $data[0];
$eventId = $js[0];
//print($eventId);

 foreach($array as $item) {
        //      print($item);
      $insert = "insert into EventMember (Event_ID, User_Name) values('$eventId', '$item');";
      mysqli_query($con,$insert);
       }

?>