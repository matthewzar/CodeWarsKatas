'''
https://www.codewars.com/kata/find-the-unique-string/train/python
Find the string that uses a different charachter set (case insensitive).
'''
from functools import reduce
def transform(string):
    if(string == ""): return string
    return str(reduce( (lambda x, y: (x + y) if x[-1] != y else x), sorted(string.lower()) ))


def find_uniq(arr):
    arr2 = list(map(transform, arr))
    nonUnique = None
    if(arr2[0] == arr2[1] or arr2[0] == arr2[2]):
        nonUnique = arr2[0]
    elif(arr2[1] == arr2[2]):
        return arr[0]
     
    for i in range(len(arr)):
        if(arr2[i] != nonUnique):
            return arr[i]

import unittest

class TestStringMethods(unittest.TestCase):
    def test(self):
        self.assertEqual(find_uniq([ 'Aa', 'aaa', 'aaaaa', 'BbBb', 'Aaaa', 'AaAaAa', 'a' ]), 'BbBb')
        self.assertEqual(find_uniq([ 'abc', 'acb', 'bac', 'foo', 'bca', 'cab', 'cba' ]), 'foo')
        self.assertEqual(find_uniq([ '    ', 'a', '  ' ]), 'a')

if __name__ == '__main__':
    unittest.main()
