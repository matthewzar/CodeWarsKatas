'''
https://www.codewars.com/kata/is-he-gonna-survive/train/python
Check if the ratio of bullets to dragons is 2 is or more.
'''
def hero(bullets, dragons):
    return bullets >= dragons * 2

import unittest

class TestStringMethods(unittest.TestCase):
    def test_true(self):
        self.assertEqual(hero(10, 5), True)
        self.assertEqual(hero(100, 40), True)

    def test_false(self):
        self.assertEqual(hero(7, 4), False)
        self.assertEqual(hero(4, 5), False)
        self.assertEqual(hero(1500, 751), False)
        self.assertEqual(hero(0, 1), False)

if __name__ == '__main__':
    unittest.main()
