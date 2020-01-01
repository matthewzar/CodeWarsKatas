//https://www.codewars.com/kata/floating-point-approximation-iii/train/javascript
//Solve for 1 of 2 values in a pythagorian equation. BUT:
//Values are extreme, and cause "catastrophic cancellation" - so go 
//and find a re-arranged equation to held mitigate floating-point errors.
//// Not an exact description, but a reasonable summary.

//Formula found here:
//https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html#5751

function quadratic(a, b, c) {
    return (2*c) / (-b-Math.sqrt(b**2 - 4*a*c))
  }




//A very different solution...
//const quadratic = (_, b, c) => -c / b;
//Easy explanation: Since the sum and product of roots are -b/a and c/a respectively, and we know one root is very close to 0, we just take divide the product from the sum to get -c/b.
//Rigorous explanation: Rewrite quadratic formula as (-b/2a) * (1-(1-4ac/b**2)**0.5), and do a binomial expansion on (1-4ac/b**2)**0.5. First term is just 1, which is cancelled. Because b is huge (remember that b >= 10**9), 4ac/b**2 is very small (around 10**-17 to 10**-18 at most), taking the first order term is accurate enough. Doing so gives us -b/2a * 1/2 * (4ac/b**2) = -c/b





//// //// //// //// //// //// //// //// //// //// 
//// TEST CODE:
//// //// //// //// //// //// //// //// //// //// 
function assertFuzzyEquals(a, b, c, msg="") {
    // max error
    let merr = 1e-12;
    console.log("Testing ", +a + ", " + b.toExponential(12) + ", " + c);
    let x = quadratic(a, b, c);
    let smallest = Math.abs(x) < 1.e-1;
    if (smallest === false) {
        console.log("This root is not the good one");
    }
    let actual = a * x * x + b * x + c;
    console.log("Actual f(x)" + actual.toExponential(12))
    let inrange = Math.abs(actual) <= merr;
    if (inrange === false) {
        msg = "Expected value near: 0 " +", got: " +actual.toExponential(12) + ". Expected error <= " +merr.toExponential(12);
        console.log(msg);
    }
    let correct = smallest && inrange;
    Test.expect(correct, "root is not good");
}

Test.describe("quadratic",function() {
Test.it("Basic tests",function() {  
    assertFuzzyEquals(7, 4.00e+13, 8);
    assertFuzzyEquals(9, 1.00e+14, 1);
    assertFuzzyEquals(3, 3.00e+09, 1);
    assertFuzzyEquals(7, 4.00e+09, 7);
    assertFuzzyEquals(5, 4.00e+14, 7);
    assertFuzzyEquals(10, 5.00e+13, 6);
    assertFuzzyEquals(1, 4.00e+10, 4);
    assertFuzzyEquals(8, 5.00e+11, 10);
    assertFuzzyEquals(12, 4.00e+11, 14);
    assertFuzzyEquals(2, 3.00e+11, 2);
    assertFuzzyEquals(5, 4.00e+15, 5);
    assertFuzzyEquals(10, 5.00e+12, 8);
    assertFuzzyEquals(9, 4.00e+09, 4);
    assertFuzzyEquals(1, 3.00e+12, 1);
    assertFuzzyEquals(9, 1.00e+13, 6);
    assertFuzzyEquals(2, 5.00e+15, 8);
    assertFuzzyEquals(7, 1.00e+09, 7);
    assertFuzzyEquals(16, 5.00e+11, 8);
    assertFuzzyEquals(3, 1.00e+09, 1);
    assertFuzzyEquals(5, 5.00e+13, 3);
})})

Test.describe("Random tests",function() {
    function randint(a, b) { 
        return Math.floor(Math.random() * (b - a + 1) + a); 
    }    //................
    for (var i = 0; i < 200; i++) {
        let a = randint(1, 20)
        let b = randint(1, 5) * (10 ** randint(9, 16))
        let c = randint(8, 25) 
        Test.it("Testing quadratic: ", function() {
            assertFuzzyEquals(a, b, c,"It should work for random tests");
        }
    )
    }
})