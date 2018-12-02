'''
https://www.codewars.com/kata/mysterious-pattern/train/python
A pattern based on the mod of each fibonacci number
'''
def mysterious_pattern(m, n):
    #get the fibonacci sequence:
    fib = [1,1]
    for i in range(m-2): fib.append(fib[-1]+fib[-2])
    #get a 2d array of chars based on the fibonacci mod results:
    ret = [["o" if fib[x]%n == y else " " for x in range(m)] for y in range(n)]
    #convert the 2d array to string form:
    return "\n".join("".join(row).rstrip() for row in ret).strip("\n")

import unittest

class TestStringMethods(unittest.TestCase):
    def test(self):
        self.assertEqual(mysterious_pattern(5, 5), 
"""
    o
oo
  o
   o
""".strip("\n"))

        self.assertEqual(mysterious_pattern(12, 4), 
"""
     o     o
oo  o oo  o
  o     o
   o     o
""".strip("\n"))

        self.assertEqual(mysterious_pattern(4, 5), 
"""
oo
  o
   o
""".strip("\n"))

        self.assertEqual(mysterious_pattern(10, 1), "oooooooooo")

if __name__ == '__main__':
    unittest.main()
