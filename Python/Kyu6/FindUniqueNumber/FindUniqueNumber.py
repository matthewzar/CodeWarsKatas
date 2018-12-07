'''
https://www.codewars.com/kata/find-the-unique-number-1/train/python
Find the unique number in a list.
'''
def find_uniq(arr):
    #This isn't as suscinct as set(), but it is more efficient.
    nonUnique = None
    if(arr[0] == arr[1] or arr[0] == arr[2]):
        nonUnique = arr[0]
    elif(arr[1] == arr[2]):
        return arr[0]
     
    for i in arr:
        if(i != nonUnique):
            return i

import unittest

class TestStringMethods(unittest.TestCase):
    def test(self):
        self.assertEqual(find_uniq([ 1, 1, 1, 2, 1, 1 ]), 2)
        self.assertEqual(find_uniq([ 0, 0, 0.55, 0, 0 ]), 0.55)
        self.assertEqual(find_uniq([ 3, 10, 3, 3, 3 ]), 10)

if __name__ == '__main__':
    unittest.main()
