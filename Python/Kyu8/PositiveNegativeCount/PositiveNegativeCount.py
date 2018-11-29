'''
https://www.codewars.com/kata/count-of-positives-slash-sum-of-negatives/train/python
Count positive numbers, and sum negatives.
'''
def count_positives_sum_negatives(arr):
    if(arr == []):
        return []
    return [sum([1 if x > 0 else 0 for x in arr]),
            sum([x if x < 0 else 0 for x in arr])]

import unittest

class TestStringMethods(unittest.TestCase):
    def test_counts(self):
        self.assertEqual(count_positives_sum_negatives([1, -15]),[1,-15])
        self.assertEqual(count_positives_sum_negatives([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15]),[10,-65])
        self.assertEqual(count_positives_sum_negatives([0, 2, 3, 0, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14]),[8,-50])
        self.assertEqual(count_positives_sum_negatives([1]),[1,0])

    def test_empty(self):
        self.assertEqual(count_positives_sum_negatives([]),[])

if __name__ == '__main__':
    unittest.main()
