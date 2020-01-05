///https://www.codewars.com/kata/52b7ed099cdc285c300001cd/train/javascript
///Write a function called sumIntervals/sum_intervals() that accepts an 
//array of intervals, and returns the sum of all the interval lengths. 
//Overlapping intervals should only be counted once. 
//No guarantees are made about order, or duplicates

//sumIntervals([1,4], [3,5] [7, 10], [8, 9])
//=> (5-1) + (10-7)
//=> 4+3
//=> 7
//[1,4] and [3,5] merged into [1,5] as they partially overlap
//[8,9] was ignored, as it's 100% nested within the range [7,10]

//This is a BAD solution, as it mutates the input
//Also, it doesn't take advantage of things like .reduce or .total
function sumIntervals(intervals){  
  var removedSomething = false;
  //Keep reducing the list until no more elements are removed
  while(true)
  {
    removedSomething = false;
    //ensure that intervals are always sorted based where they start
    intervals.sort(function(a, b){return a[0]-b[0]});
  
    for(var i = 0; i < intervals.length-1; i++)
    {
      var current = intervals[i];
      var next = intervals[i+1];
      
      //if the 'next' range is 100% nested within 'current' range. Remove next without tracking it.
      if(next[0] >= current[0] && next[1] <= current[1])
      {    
        removedSomething = true;
        intervals.splice(i+1,1);
        continue;
      }
      
      //Check if the end of the current range would step into the next range. Then merge and remove next.
      if(current[1] >= next[0])
      {
        removedSomething = true;
        current[1] = next[1];
        intervals.splice(i+1,1);
      }
    }
      
    if(!removedSomething) break;
  }
  
  var sum = 0;
  for(var interval of intervals) sum += interval[1] - interval[0];
  return sum;
}


//A far more efficient verion - uses maths knowledge to avoid recursion OR iteration:
//function digital_root(n) {
//    return (n - 1) % 9 + 1;
//  }

//describe('sumIntervals', function(){
//  it('should return the correct sum for non overlapping intervals', function(){
//    var test1 = [[1,5]];
//    var test2 = [[1,5],[6,10]];
//    Test.assertEquals(sumIntervals(test1), 4);
//    Test.assertEquals(sumIntervals(test2), 8);
//  });
//  
//  it('should return the correct sum for overlapping intervals', function(){
//    var test1 = [[1,5],[1,5]];
//    var test2 = [[1,4],[7, 10],[3, 5]];
//    Test.assertEquals(sumIntervals(test1), 4);
//    Test.assertEquals(sumIntervals(test2), 7);
//  });
//});