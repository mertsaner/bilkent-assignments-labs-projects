(define (contains? list item)
	(cond 
		( (null? list) #f) 
		( (equal? item (car list) ) #t )
		(#t (contains? (cdr list) item) ) 
	)
)

(define (setifyhelp list1 out)
    (if (null? list1) 
      out
      (begin 
        (if (not (contains? out (car list1))) 
			(setifyhelp (cdr list1) (append out (list (car list1))))    
				(setifyhelp (cdr list1) out)))))

(define (setify list1) (setifyhelp list1 '()))