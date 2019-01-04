<html lang="en">
<body>
	<?php
	session_start();
	if(empty($_SESSION['sid'])){
		header("Location: ./index.php");
		die();
	}
	$cid = $_POST["cid"];
	try{
		$sql = "INSERT INTO apply VALUES(\"" . $_SESSION['sid'] . "\",\"" . $cid . "\");";
		$result = mysqli_query($conn, $sql);
		if(!$result)
			throw new Exception($result);
	} catch(Exception $e){
		header("Location: ./error.php");
		die();
	}
	header("Location: ./home.php");
	die();
	?>
</body>
</html>
