'''
https://www.codewars.com/kata/57ee2a1b7b45efcf700001bf/train/python
Take a string containing "m", "C", and "D".
Check that "m" and "C" are within "j" steps of each other and
whether or not "D" is in between the two.
'''

def cat_mouse(x, j):
    cat, dog, mouse = [x.find(c) for c in 'CDm']
    if -1 in [cat, dog, mouse]:
        return 'boring without all three'
    if abs(mouse - cat) > j: 
        return 'Escaped!'
    if cat < dog < mouse or cat > dog > mouse 
		return 'Protected!' 
	return 'Caught!'
    

#Test.describe("Basic tests")
#Test.assert_equals(cat_mouse('..D.....C.m', 2), "Caught!")
#Test.assert_equals(cat_mouse('............C.............D..m...', 8), "Escaped!")
#Test.assert_equals(cat_mouse('m.C...', 5), "boring without all three")
#Test.assert_equals(cat_mouse('.CD......m.', 10), "Protected!")
#Test.assert_equals(cat_mouse('.CD......m.', 1), "Escaped!")

