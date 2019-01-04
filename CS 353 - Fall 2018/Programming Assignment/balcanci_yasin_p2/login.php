 <!DOCTYPE html>
<html>
<body>
<?php
include 'connect.php';
// Check connection
if (!$conn) {
	die("Connection failed: " . mysqli_connect_error());
}

$sql = "SELECT * FROM student WHERE sname = \"" . strtolower($_POST["sname"]) . "\" AND sid = \"" . $_POST["sid"] . "\";";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) <= 0) {
	echo "<h1>LOGIN FAILED</h1>
		  <div>You are being directed to login page in 2 seconds.</div>";
	header( "refresh:2;url=index.php" );
	die();
} else {
	$_SESSION['sid'] = $_POST['sid'];
	header("Location: ./home.php");
	die();
}

mysqli_close($conn);
?> 
	<p><?php main();?></p>
</body>
</html> 
