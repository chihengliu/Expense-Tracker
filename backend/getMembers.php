<?php
require "init.php";
$eventId = $_POST["eventId"];

$sql="select EventMember.User_Name from EventMember where EventMember.Event_ID = '$eventId';";

$query = mysqli_query($con,$sql);

if($query)
{
    while($row=mysqli_fetch_array($query))
          {
	      $data[]=$row;
          }
	  print(json_encode($data));
}else
{
	echo('fail');
}
?>