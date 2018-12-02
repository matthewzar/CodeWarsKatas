'''
https://www.codewars.com/kata/flatten-and-sort-an-array/train/python
Take a 2D array, flatten it and sort it.
'''
def flatten_and_sort(array):
    out = []
	#There are better ways to do this (sorted() is a big one), I wanted to show in-comprehension method calls
    [[out.append(item) for item in sub] for sub in array]
    out.sort()
    return out

import unittest

class TestStringMethods(unittest.TestCase):
    def test(self):
        self.assertEqual(flatten_and_sort([]), [])
        self.assertEqual(flatten_and_sort([[], [1]]), [1])
        self.assertEqual(flatten_and_sort([[3, 2, 1], [7, 9, 8], [6, 4, 5]]), [1, 2, 3, 4, 5, 6, 7, 8, 9])
        self.assertEqual(flatten_and_sort([[1, 3, 5], [100], [2, 4, 6]]), [1, 2, 3, 4, 5, 6, 100])
	
if __name__ == '__main__':
    unittest.main()
