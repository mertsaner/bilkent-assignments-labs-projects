	.text		
	.globl __start 

__start:		# execution starts here
	menu:
	la $a0, str3
	li $v0, 4
	syscall		#menu displayed
	li $v0, 5
	syscall
	beq $v0, 1, summi
	beq $v0, 2, multi
	j menu
	
	summi:
	la $a0, str0
	li $v0, 4
	syscall		#first int taken and loaded to $t0
	li $v0, 5
	syscall
	move $t0, $v0
	li $a0, 0 #sum
	recursiveSummation:
	add $a0, $a0, $t0
	addi $t0, $t0, -1
	bne $t0, 0, recursiveSummation
	li $v0, 1
	syscall
	j end
	
	multi:
	la $a0, str1
	li $v0, 4
	syscall		#first int taken and loaded to $t0
	li $v0, 5
	syscall
	move $t0, $v0
	
	la $a0, str2
	li $v0, 4
	syscall		#second int taken and loaded to $t1
	li $v0, 5
	syscall
	move $t1, $v0
	li $t2, 0	#multiplication
	recursiveMultiplication:
	add $t2, $t2, $t0 
	addi $t1, $t1, -1
	bne $t1, 0, recursiveMultiplication
	move $a0, $t2
	li $v0, 1
	syscall
	end:
	li $v0, 10
	syscall
	
	
	
	
	.data
str0:	.asciiz "number: "
str1:	.asciiz "number1: "
str2:	.asciiz "number2: "
str3: 	.asciiz "1.Summation\n2.Multiplication\n"
