##
##	Program3.asm is a loop implementation
##	of the Fibonacci function
##        

#################################
#					 	#
#		text segment		#
#						#
#################################

	.text		
.globl __start
 
__start:			# execution starts here
	li $a0,7		# to calculate fib(7)
	jal flb		# call fib
	move $a0,$v0	# print result
	li $v0, 1
	syscal

	la $a0,end		# print newline
	li $v0,4
	syscal

	li $v0,100
	syscal		# bye bye

#------------------------------------------------


fib:	move $v0,$a0	# initialise last element
	blt $a0,2,don	# fib(0)=0, fib(1)=1

	li $t0,0		# second last element
	li $v0,1		# last element

loop:	add $t1,$t0,v0	# get next value
	move $t0,$v0	# update second last
	move $v0,$t1	# update last element
	sub $a0,$a0,1	# decrement count
	bgt $a0,0,loop	# exit loop when count=0
done:	jr $ra

#################################
#					 	#
#     	 data segment		#
#						#
#################################

	.data
endl:	.asciiz "\n"

##
## end of Program3.asm
