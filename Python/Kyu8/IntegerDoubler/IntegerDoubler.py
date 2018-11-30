'''
https://www.codewars.com/kata/53ee5429ba190077850011d4
Double an integer
'''
double_integer = lambda x : 2*x

import unittest

class TestStringMethods(unittest.TestCase):
    def test_positive(self):
        self.assertEqual(double_integer(0),0)
        self.assertEqual(double_integer(2),4)
        self.assertEqual(double_integer(13),26)

    def test_negative(self):
        self.assertEqual(double_integer(-3),-6)
        self.assertEqual(double_integer(-2),-4)
        self.assertEqual(double_integer(-1),-2)

if __name__ == '__main__':
    unittest.main()
