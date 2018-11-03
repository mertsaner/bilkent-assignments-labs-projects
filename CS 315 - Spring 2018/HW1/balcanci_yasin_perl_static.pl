$x = 5;
sub foo(){
	$x = 6;
	print $x; # 1st print
	print "\n";
}
sub bar(){
	sub cet(){
		$x = 4;
		print $x; # 2nd print
		print "\n";
	}
	my $x = 7;
	foo(); 
	cet();
	print $x; # 3rd print
	print "\n";
}
bar(); # assigns x to 7. calls foo() and cet() respectively. then prints x.
print $x; # last print
print "\n";
