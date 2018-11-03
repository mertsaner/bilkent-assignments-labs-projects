def foo(a = 5, b = 4, c = 3):
	print(a * b + c)
	
def bar(*nums):
	print(sum(nums))
	
def passBy(x, y):
	x = 5
	y.append(5)
	print("Inside passBy x = ",x,", y = ",y)

foo(10, 9, 8) #Positional Parameters / a = 10, b = 9, c = 8 / prints 720
foo(c = 1, a = 2, b = 3) #Keyword Parameters / a = 2, b = 3, c = 1 / prints 6
foo(10, c = 8, b = 9) #Positional and Keyword Parameters Together / a = 10, b = 9, c = 8 / prints 720
foo() # a, b and c as Default Paramaters / a = 5, b = 4, c = 3 / prints 60
foo(10) # b and c as Default Parameters / a = 10, b = 4, c = 3 / prints 120

bar()
bar(1)
bar(1, 2, 3, 4)

x = 3
y = [4]
passBy(x, y)
print("After passBy x = ",x,", y = ",y)
