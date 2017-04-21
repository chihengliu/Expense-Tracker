<?php
require "init.php";
$json = json_decode(file_get_contents('php://input'),true);

$name = $json["username"];

$sql = "SELECT Password From User_info WHERE Name LIKE '$name'";

$query = mysqli_query($con,$sql);

if($query)
{
    while($row=mysqli_fetch_array($query))
      {
          $data[]=$row;
            }

                print(json_encode($data));
                }
                else
		{
                  echo('fail');
                  }

?>