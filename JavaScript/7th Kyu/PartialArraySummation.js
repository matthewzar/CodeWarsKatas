//https://www.codewars.com/kata/58880c6e79a0a3e459000004/train/javascript
//Sum all numbers in an array before the first 0
//EG: [1,2,3,0,10] --> 6

function houseNumbersSum(inputArray) {
    var ret = 0;
    for(n of inputArray)
    {
      if(n == 0) return ret;
      ret += n;
    }
  }

//Interesting use of slices (albeit hard to read):
//function houseNumbersSum(inputArray) {
//    return inputArray.slice(0, inputArray.indexOf(0)).reduce((s, v) => s+v, 0);
//  }


//describe("Basic Tests", function(){
//it("It should works for basic tests.", function(){
//    Test.assertEquals( houseNumbersSum([5, 1, 2, 3, 0, 1, 5, 0, 2]),11)    
//    Test.assertEquals( houseNumbersSum([4, 2, 1, 6, 0]),13)    
//    Test.assertEquals( houseNumbersSum([4, 1, 2, 3, 0, 10, 2]),10)    
//    Test.assertEquals( houseNumbersSum([0, 1, 2, 3, 4, 5]),0)    
//    })})