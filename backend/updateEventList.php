<?php
require "init.php";
//$name = $_POST["username"];
$json = json_decode(file_get_contents('php://input'), true);

$name = $json["username"];

$sql="select * from Eventlist, EventMember where Eventlist.EID=EventMember.Event_ID and EventMember.User_Name = '$name';";

$query = mysqli_query($con,$sql);

if($query){
    while($row=mysqli_fetch_array($query)){
          $data[]=$row;
          $tempEventId = $row[0];
          $tempsql = "select User_Name from EventMember where Event_ID = '$tempEventId';";
          $tempquery = mysqli_query($con, $tempsql);
          //$numForEvent = 0;
          if($tempquery){
    while($temprow = mysqli_fetch_array($tempquery)){
                               $data[] = $temprow;
    }
          }
  }
}else{
   echo('fail0');
}


$sql2="select Name from User_info;";
$query2 = mysqli_query($con,$sql2);

$totalNum = 0;

if($query2){
  while($row=mysqli_fetch_array($query2)){
          $data[]=$row;
          $totalNum = $totalNum + 1;
          }
  }else{
          echo('fail1');
          }
          $arr = array('totalNum'=>$totalNum);
          $data[] = $arr;
print(json_encode($data));

?>