//https://www.codewars.com/kata/is-n-divisible-by-x-and-y/train/javascript
//Create a function isDivisible(n, x, y) that checks if a number n is divisible by 
//two numbers x AND y. All inputs are positive, non-zero digits.

const isDivisible = (n, x, y) => n % x === 0 && n % y === 0;

//Test.assertSimilar(isDivisible(3,3,4),false);
//Test.assertSimilar(isDivisible(12,3,4),true);
//Test.assertSimilar(isDivisible(8,3,4),false);
//Test.assertSimilar(isDivisible(48,3,4),true);