'''
https://www.codewars.com/kata/volume-of-a-cuboid/train/python
Find the volume of a cuboid
'''
def getVolumeOfCubiod(length, width, height):
	return length*width*height

import unittest

class TestStringMethods(unittest.TestCase):
	def test(self):
		self.assertEqual(getVolumeOfCubiod(1, 2, 2), 4)
		self.assertEqual(getVolumeOfCubiod(6.3, 2, 5), 63)

if __name__ == '__main__':
	unittest.main()
