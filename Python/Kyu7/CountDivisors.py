'''
https://www.codewars.com/kata/542c0f198e077084c0000c2e/train/python
Counts the divisors of a positive number 'n'

>divisors(1)
1
>divisors(5)
2
>divisors(4)
3  
'''

# Time: 2126ms
# Despite looking simplistic, this is a better version than all those shown below for 2 reasons:
#  - Almost half the run-time (NOT due to avoiding lists, instead due to `n//2`).
#  - Much easier to understand and alter.
def divisors(n):
    count = 1
    for i in range(1, n//2 + 1):
        if n % i == 0: 
            count += 1
    return count
	
# Time: 2146ms
# This is my improved version of polyglotm's fastest version below - halving his time
divisors = lambda n: sum([n % x == 0 for x in range(1, n//2+1)]) + 1


#User polyglotm suggests several alternatives, and compares performance:

# Time: 11724ms
# it's slow because use isinstance
def divisors6(n):
    return len(list(filter(lambda e: isinstance(e, int), [x if n % x == 0 else None for x in range(1, n + 1)])))


# Time: 7546ms
# it's little fast because just directly check boolean
def divisors5(n):
    return len(list(filter(lambda e: e, [True if n % x == 0 else False for x in range(1, n + 1)])))


# Time: 4731ms
# in python True is evaluate as 1
# so when prime factorization just set True and sum will return count
def divisors4(n):
    return sum([True if n % x == 0 else False for x in range(1, n + 1)])


# Time: 3675ms
# even don't need return true, cause comparison operator will return boolean
def divisors3(n):
    return sum([n % x == 0 for x in range(1, n + 1)])


# same time with above but make short code via lambda expression
divisors2 = lambda n: sum([n % x == 0 for x in range(1, n + 1)])