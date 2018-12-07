'''
https://www.codewars.com/kata/removing-elements/train/python
Remove every second element from a list
'''
def remove_every_other(my_list):
    return my_list[::2]


import unittest

class TestStringMethods(unittest.TestCase):
    def test(self):
        self.assertEqual(remove_every_other([1, -15]),[1])
        self.assertEqual(remove_every_other(['Hello', 'Goodbye', 'Hello Again']), ['Hello', 'Hello Again'])
        #List of lists:
        self.assertEqual(remove_every_other([[1, 2]]), [[1, 2]])

if __name__ == '__main__':
    unittest.main()
