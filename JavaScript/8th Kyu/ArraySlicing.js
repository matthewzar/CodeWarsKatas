//https://www.codewars.com/kata/545afd0761aa4c3055001386/train/javascript
//Write a funtion `take()` to return the first n elements of an array

const take = (a, n) => a.slice(0, n);

//TEST CASES
describe("Basic Tests", function(){
    it("should work for basic tests", function(){
      Test.assertDeepEquals(take([0, 1, 2, 3, 5, 8, 13], 3), [0, 1, 2], "should return the first 3 items");
      Test.assertDeepEquals(take([0, 1, 2, 3, 5, 8, 13], 0), [], "should return 0 items");
      Test.assertDeepEquals(take([], 3), [], "empty array should return empty array");
    });
  });
  
describe("Random Tests", function(){
it("should work for random tests", function(){
    function generateRandomArray() {
    let n = Math.floor(Math.random() * 100);
    let arr = [];
    for (let i = 0; i < n; i++) arr.push(Math.floor(-100 * Math.random() + 100 * Math.random()));
    return arr;
    }
    for (let i = 0; i < 100; i++) {
    let arr = generateRandomArray();
    let n = Math.floor(Math.random() * 100);
    let expected = arr.slice(0, n);
    let message = "";
    if (arr.length === 0) message = "empty array should return empty array";
    else if (n === 0) message = "should return 0 items";
    else if (n <= arr.length) message = "should return the first " + n + " items";
    else message = "should return the first " + arr.length + " items (because there are no " + n + " items in the array)";
    console.log("Testing with arr = [" + arr + "] and n = " + n + ": ");
    Test.assertDeepEquals(take(arr, n), expected, message);
    }  
});
});