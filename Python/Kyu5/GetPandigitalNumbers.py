'''
https://www.codewars.com/kata/5ac69d572f317bdfc3000124/train/python

In mathematics, a pandigital number is a number that in a given base has among its significant digits each digit used in the base at least once. For example, 1234567890 is a pandigital number in base 10. For simplicity, here pandigital numbers will be bound to exactly 1 occurence of each digit (i.e. nothing over 9876543211).

Calculate a sorted sequence of pandigital numbers, starting at a certain minimum value, and up to a target number of elements.
'''

def isPandigital(a):
    count = 10*[0]
    while a != 0:
        if count[a%10] == 1: return False
        count[a%10] += 1
        a //= 10
    return True

def get_sequence(minimum_value, target_length):
    pandigitals = []
    minimum_value = max(minimum_value, 1023456789)
    for a in range(minimum_value, 9876543211):
        if not isPandigital(a): continue
        pandigitals.append(a)
        
        if(len(pandigitals) >= target_length):
            break;
    return pandigitals