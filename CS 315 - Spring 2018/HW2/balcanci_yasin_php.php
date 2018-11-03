
<!DOCTYPE html>
<html>
<body>

<h2>CS315 HW1</h2>
<p>Yasin BALCANCI / 21501109 / Sec: 1</p>

<?php
	$x = 5;
	function foo($a = 5, $b = 4, $c = 3){
		echo $a * $b + $c;
		echo "\n";
	}
	
	function bar(){
		echo array_sum(func_get_args());
		echo "\n";
	}
	
	function passBy($x, &$y){
		$x = 5;		
		$y = 5;
	}
	
	foo(10, 9, 8); //Positional Parameters / a = 10, b = 9, c = 8 / prints 98
	foo(); // a, b and c as Default Paramaters / a = 5, b = 4, c = 3 / prints 23
	foo(10); // b and c as Default Parameters / a = 10, b = 4, c = 3 / prints 43
	
	bar(); 				// ************************************** / prints 0
	bar(10);			//  Variable Number of Actual Parameters  / prints 10
	bar(10, 20, 30);	// ************************************** / prints 60
	
	$x = 3;
	$y = 4;
	passBy($x, $y);
	echo $x; // Pass by value / Prints 3
	echo "\n";
	echo $y; // Pass by reference / Prints 5
?>
<p class="w3-large w3-center"><?php main();?></p>

<p id="code"></p>
<p id="out1"></p>
<p id="out2"></p>
<p id="out3"></p>
<p id="out4"></p>

</body>
</html> 
