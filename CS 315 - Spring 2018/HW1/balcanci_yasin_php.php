
<!DOCTYPE html>
<html>
<body>

<h2>CS315 HW1</h2>
<p>Yasin BALCANCI / 21501109 / Sec: 1</p>

<?php
	$x = 5;
	function foo(){
		$x = 6;
		echo $x;
		echo "\n";
	}
	function bar(){
		function cet(){
			$x = 4;
			echo $x;
			echo "\n";
		}
		$x = 7;
		foo();
		cet();
		echo $x;
		echo "\n";
	}
	bar();
	echo $x;
	echo "\n";

?>
<p class="w3-large w3-center"><?php main();?></p>

<p id="code"></p>
<p id="out1"></p>
<p id="out2"></p>
<p id="out3"></p>
<p id="out4"></p>

</body>
</html> 
