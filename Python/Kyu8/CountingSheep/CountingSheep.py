'''
https://www.codewars.com/kata/count-of-positives-slash-sum-of-negatives/train/python
Count positive numbers, and sum negatives.
'''
def count_sheep(n):
    return ''.join(f"{i} sheep..." for i in range(1,n+1))

import unittest

class TestStringMethods(unittest.TestCase):
    def test(self):
        self.assertEqual(count_sheep(1),"1 sheep...")
        self.assertEqual(count_sheep(2),"1 sheep...2 sheep...")
        self.assertEqual(count_sheep(3),"1 sheep...2 sheep...3 sheep...")


if __name__ == '__main__':
    unittest.main()
