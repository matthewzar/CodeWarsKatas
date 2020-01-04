///https://www.codewars.com/kata/sum-of-digits-slash-digital-root/train/javascript
///A digital root is the recursive sum of all the digits in a number. Given n, take 
///the sum of the digits of n. If that value has more than one digit, continue reducing 
///in this way until a single-digit number is produced. This is only applicable to the
///natural numbers.

//digital_root(942)
//=> 9 + 4 + 2
//=> 15 ...
//=> 1 + 5
//=> 6

function digital_root(n) {
    if (n < 10) return n;
    var sum = 0;
    while (n > 0)
    {    
      sum += n % 10;
      n = Math.floor(n/10);
    }  
    return digital_root(sum);
  }

//A far more efficient verion - uses maths knowledge to avoid recursion OR iteration:
//function digital_root(n) {
//    return (n - 1) % 9 + 1;
//  }

//Test.assertEquals( digital_root(16), 7 )
//Test.assertEquals( digital_root(456), 6 )