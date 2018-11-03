x = 5 # x is assigned to 5 first.
def foo():
	x = 6
	print(x) # first print
def bar():
	def cet():
		x = 4
		print(x) # 2nd print
	x = 7
	foo()
	cet() 
	print(x) # 3rd print
bar() # assigns x to 7. calls foo() and cet() respectively. then prints x.
print(x) # last print
