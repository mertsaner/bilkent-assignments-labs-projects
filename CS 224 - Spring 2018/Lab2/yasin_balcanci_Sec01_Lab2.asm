#Yasin Balcanci / 21501109 / Sec:1 / Lab: 2
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

	.text		
	.globl __start 

__start:
	readArray:
	la $a0,str2
	li $v0,4	# system call to print
	syscall
	li $v0, 5
	syscall
	move $a2, $v0 # t0 and a2: number of integers
	move $t0, $a2
	mul $a0, $t0, 4 # a0: required space
	li $v0, 9
	syscall
	move $a3, $v0 # adress of the array is in a3 and t1 now
	move $t1, $a3
	
	while1:
	beq $t0, 0, done1
	li $v0, 42  # 42 is system call code to generate random int
	li $a1, 100 # upper bound
	syscall     # your generated number will be at $a0
	sw $a0, ($t1)
	addi $t0, $t0, -1
	addi $t1, $t1, 4
	j while1
	done1:
	
	display:
	addi $t1, $t1, -4
	while2:
	beq $t0, $a2, done2
	la $a0,str	# put string address into a0
	li $v0,4	# system call to print
	syscall	#   out a string
	lw $a0, ($t1)
	li $v0, 1  # 42 is system call code to generate random int
	li $a1, 100 # upper bound
	syscall     # your generated number will be at $a0
	addi $t0, $t0, 1
	addi $t1, $t1, -4
	j while2
	done2:
	addi $a0, $t1, 4
	move $a1, $a2


.data
str:   .asciiz "\nItem: "
str2: .asciiz "Enter size: "

	.text		
	.globl __start 

__start:
	la $a0, array
	li $a1, 6
	bubbleSort: # $a0 = array adress, $a1 = num of elements
	
	for2:
	move $t0, $a0
	move $t1, $a1
	addi $t4, $t1, -1 # t4 = numOfElements - 1 
	li $t2, 0 # t2: counter
	
	for1: # t0 = i
	beq $t2, $t4, done1
		lw $t5, 0($t0)
		lw $t6, 4($t0)
		ble $t5, $t6, pass
		move $t3, $t5
		sw $t6, 0($t0)
		sw $t3, 4($t0)
		li $t7, 1
		pass:
		addi $t2, $t2, 1
		addi $t0, $t0, 4
		j for1
	done1:
	beqz $t7 ,done2 # if work = 0, done
	li $t7, 0
	j for2
	done2:
	

	
	
	.data
str:   .asciiz "\nItem: "
array: .word 6, 5, 4, 3, 2, 1
	.text		
	.globl __start 

__start:
	la $a0, array
	li $a1, 6
	minMax: # $a0 = array adress, $a1 = num of elements
	
	lw $v0, ($a0) # min
	lw $v1, ($a0) # max
	move $t0, $a0
	move $t1, $a1
	while1:
	beq $t1, 0, done1
	lw $t3, ($t0)
	
	bge $t3, $v0, notLess
	move $v0, $t3
	notLess:
	
	ble $t3, $v1, notGreater
	move $v1, $t3
	notGreater:
	
	
	addi $t1, $t1, -1
	addi $t0, $t0, 4
	j while1
	done1: # v0 = min / v1 = max
	move $a0, $v0
	li $v0, 1
	syscall
	move $a0, $v1
	li $v0, 1
	syscall
	
	.data
	array: .word 4, 7, 1, 9, 5, 2
	.text		
	.globl __start 

__start:		# execution starts here
	la $a0,menu
	li $v0,4	# system call to print
	syscall	#   out a string
	li $v0, 5  # system call to input
	syscall	
	
	addi $t0, $v0, 0
	beq $t0, 1, readArray
	beq $t0, 2, bubbleSort
	beq $t0, 3, minMax
	beq $t0, 4, quit
	
	
	readArray:
	la $a0,str2
	li $v0,4	# system call to print
	syscall
	li $v0, 5
	syscall
	move $a2, $v0 # t0 and a2: number of integers
	move $t0, $a2
	mul $a0, $t0, 4 # a0: required space
	li $v0, 9
	syscall
	move $a3, $v0 # adress of the array is in a3 and t1 now
	move $t1, $a3
	
	while1:
	beq $t0, 0, done1
	li $v0, 42  # 42 is system call code to generate random int
	li $a1, 100 # upper bound
	syscall     # your generated number will be at $a0
	sw $a0, ($t1)
	addi $t0, $t0, -1
	addi $t1, $t1, 4
	j while1
	done1:
	
	display:
	addi $t1, $t1, -4
	while2:
	beq $t0, $a2, done2
	la $a0,str	# put string address into a0
	li $v0,4	# system call to print
	syscall	#   out a string
	lw $a0, ($t1)
	li $v0, 1  # 42 is system call code to generate random int
	li $a1, 100 # upper bound
	syscall     # your generated number will be at $a0
	addi $t0, $t0, 1
	addi $t1, $t1, -4
	j while2
	done2:
	addi $a0, $t1, 4
	move $a1, $a2

	bubbleSort: # $a0 = array adress, $a1 = num of elements
	
	for2:
	move $t0, $a0
	move $t1, $a1
	addi $t4, $t1, -1 # t4 = numOfElements - 1 
	li $t2, 0 # t2: counter
	
	for1: # t0 = i
	beq $t2, $t4, donefor1
		lw $t5, 0($t0)
		lw $t6, 4($t0)
		ble $t5, $t6, pass
		move $t3, $t5
		sw $t6, 0($t0)
		sw $t3, 4($t0)
		li $t7, 1
		pass:
		addi $t2, $t2, 1
		addi $t0, $t0, 4
		j for1
	donefor1:
	beqz $t7 ,donefor2 # if work = 0, done
	li $t7, 0
	j for2
	donefor2:

	minMax: # $a0 = array adress, $a1 = num of elements
	
	lw $v0, ($a0) # min
	lw $v1, ($a0) # max
	move $t0, $a0
	move $t1, $a1
	while3:
	beq $t1, 0, done3
	lw $t3, ($t0)
	
	bge $t3, $v0, notLess
	move $v0, $t3
	notLess:
	
	ble $t3, $v1, notGreater
	move $v1, $t3
	notGreater:
	
	
	addi $t1, $t1, -1
	addi $t0, $t0, 4
	j while3
	done3: # v0 = min / v0 = max
	
	quit:
	li $v0, 10  # exit
	syscall	


	.data
menu:	.asciiz "1. Input size to form a random array.\n2. Bubble Sort.\n3. Min-Max.\n4. Quit.\n"
str:   .asciiz "\nItem: "
str2: .asciiz "Enter size: "