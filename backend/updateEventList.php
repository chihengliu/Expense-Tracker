<?php
require "init.php";
$name = $_POST["username"];

$sql="select * from Eventlist, EventMember where Eventlist.EID=EventMember.Event_ID and EventMember.User_Name = '$name';";

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