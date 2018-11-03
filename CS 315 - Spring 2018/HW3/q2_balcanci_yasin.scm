(define (join list1 list2) 
	(if (null? list1) 
	list2
		(if (null? list2) 
		list1 
			(if (<= (car list1) (car list2)) 
				(setify (append (list (car list1)) (join (cdr list1) list2))) 
					(setify (append (list (car list2)) (join list1 (cdr list2)))))
)))