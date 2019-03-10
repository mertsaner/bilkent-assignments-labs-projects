	.text		
	.globl __start 

__start:		# execution starts here
	li $a1, 0	# number of digits
	li $t2, 1	# summing index
	li $t0, 0	# current number to be added to sum	
	li $t1, 0	# temp
	li $v0, 0	# decimal sum
	la $a0,hexNo	# put string address into a0
	jal   convertHexToDec
	move 
	
	convertHexToDec:
	end:
		li $v0, 10
		syscall
	while1:	
		lb $t1, 0($a0)
		bgt $t1, 70, quit
		beq $t1, $0, done1 # while the current ascii is not "null"
		addi $a1, $a1, 1
		addi $a0, $a0, 1
		j while1
	done1:
	addi $a0, $a0, -1
	while2:	
		beq $a1, $0, done2 # while there is character to sum
		lb $t0, 0($a0)
		addi $t0, $t0, -48
		add $t0, $t0, $t3
		li $t3, 0
		ble $t0, 9, if1
		li $t3, 1
		addi $t0, $t0, -10
		if1:
		mult $t2, $t0
		mflo $t0
		add $v0, $v0, $t0
		mul $t2, $t2, 22
		#mflo $a2
		addi $a1, $a1, -1
		addi $a0, $a0, -1
		j while2
	done2:
		j end
	
	quit:
		li $v0, -1
	
	
	.data
hexNo:   .asciiz "T0"
