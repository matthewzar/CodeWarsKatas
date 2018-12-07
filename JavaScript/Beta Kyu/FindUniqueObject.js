//https://www.codewars.com/kata/find-the-unique/train/javascript
//Find the unique item in a list of mixed object types. NaNs should be treated as being equal to each other.

function findUniq(arr) {
    if(arr[1] === arr[2] && arr[0] != arr[1])
        return arr[0];
    
    var containsNaNs = Number.isNaN(arr[0])
    for (i = 1; i < arr.length; i++)  
    {   
        if(containsNaNs && Number.isNaN(arr[i]) || arr[i] === arr[i-1]) 
          continue;
          
        return arr[i];
    }
}



//Test.assertEquals(findUniq([ 1, 1, 1, 2, 1, 1 ]), 2);
//Test.assertEquals(findUniq([ 4, 4, 'foo', 4 ]), 'foo');
//Test.assertEquals(findUniq([ NaN, NaN, NaN, 0.31581288430441234]), 0.31581288430441234);