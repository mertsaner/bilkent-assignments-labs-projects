	.text
	.globl __start
	
__start:
	la $a0, str
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	
	move $t0, $v0 
	li $t1, 1
	li $t2, 0
	sll $t1, $t1, 31
	while:
	beq $t2, 31, done
	addi $t2, $t2, 1
	and $t4, 
	srl
	done:
	
	l.s $f0, a # $f.. are floating point registers
	cvt.s.w $f12, $f0
	li $v0, 2
	syscall # Prints 10.0
.data
a: .word 10
str: .asciiz "Enter the number: "
