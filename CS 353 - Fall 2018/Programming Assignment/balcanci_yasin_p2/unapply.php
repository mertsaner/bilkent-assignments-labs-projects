<html lang="en">
<body>
	<?php
	include 'connect.php';
	if(empty($_SESSION['sid'])){
		header("Location: http://dijkstra.cs.bilkent.edu.tr/~ybalcanci/index.php");
		die();
	}
	$cid = array_keys($_POST)[0];
	$sql = "DELETE FROM apply WHERE cid = \"" . $cid . "\" AND sid = \"" . $_SESSION['sid'] . "\";";
	$result = mysqli_query($conn, $sql);
	header("Location: ./home.php");
	die();
	?>
</body>
</html>
