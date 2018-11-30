'''
https://www.codewars.com/kata/the-office-i-outed/train/python
Find the average score and give a response based on whether its smaller than 5 (or not).
Bosses score is worth double.
'''
def outed(meet, boss):
    total = 0
    for name, score in meet.items():
        total += score*2 if name == boss else score
    return "Get Out Now!" if total/len(meet) <= 5 else "Nice Work Champ!"

import unittest

class TestStringMethods(unittest.TestCase):
    def test(self):
        self.assertEqual(outed({'tim':0, 'jim':2, 'randy':0, 'sandy':7, 'andy':0, 'katie':5, 'laura':1, 'saajid':2, 'alex':3, 'john':2, 'mr':0}, 'laura'), 'Get Out Now!')
        self.assertEqual(outed({'tim':1, 'jim':3, 'randy':9, 'sandy':6, 'andy':7, 'katie':6, 'laura':9, 'saajid':9, 'alex':9, 'john':9, 'mr':8}, 'katie'), 'Nice Work Champ!')
        self.assertEqual(outed({'tim':2, 'jim':4, 'randy':0, 'sandy':5, 'andy':8, 'katie':6, 'laura':2, 'saajid':2, 'alex':3, 'john':2, 'mr':8}, 'john'), 'Get Out Now!')

if __name__ == '__main__':
    unittest.main()
