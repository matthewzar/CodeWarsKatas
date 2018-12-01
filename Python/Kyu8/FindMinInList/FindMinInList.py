'''
https://www.codewars.com/kata/find-the-smallest-integer-in-the-array/train/python
Make a function that finds the smallest number in a list...
'''

#So just re-assign the existing method that does exactly that :P
find_smallest_int = min

import unittest

class TestStringMethods(unittest.TestCase):
    def test_counts(self):
        self.assertEqual(find_smallest_int([1, -15]),[1,-15])
        self.assertEqual(find_smallest_int([78, 56, 232, 12, 11, 43]), 11)
		self.assertEqual(find_smallest_int([78, 56, -2, 12, 8, -33]), -33)
		self.assertEqual(find_smallest_int([0, 1-2**64, 2**64]), 1-2**64)

if __name__ == '__main__':
    unittest.main()
