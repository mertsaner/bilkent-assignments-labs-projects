<?php 
$servername = "<YOUR_SERVER_URL>";
$username = "<USERNAME_TO_CONNECT_TO_SERVER>";
$password = "<PASSWORD_TO_CONNECT_TO_SERVER>";
$dbname = "<USERNAME_FOR_DATABASE>";
$conn = mysqli_connect($servername, $username, $password, $dbname);
session_start();
?>
