<?php
require "init.php";
$name = $_POST["username"];

$sql="SELECT * FROM Personal WHERE Name LIKE '%$name%'";

$query=mysqli_query($con,$sql);

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