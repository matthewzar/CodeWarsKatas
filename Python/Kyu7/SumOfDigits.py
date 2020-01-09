'''
https://www.codewars.com/kata/52f3149496de55aded000410/solutions/python
Write a function named sumDigits which takes a number as input and returns the sum of 
the absolute value of each of the number's decimal digits. 
'''

def sum_digits(number):
    sum = 0
    number = abs(number)
    while(number > 0):
        sum += number % 10
        number //= 10
    return sum

#test.assert_equals(sum_digits(10), 1)
#test.assert_equals(sum_digits(99), 18)
#test.assert_equals(sum_digits(-32), 5)

##A bad solution, that people seem to think is either good practice, or clever:
#def sumDigits(number):
#    return sum(int(d) for d in str(abs(number)))

#As a once-off, this is okay. But converting an integer to a string
#an expensive operation compared to simple modding and division.

