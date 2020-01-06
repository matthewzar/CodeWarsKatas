///https://www.codewars.com/kata/find-the-unknown-digit/train/javascript
//Find the lowest number to replace '?'s in an equation, 
//such that the equation is correct, and no leading-zeroes are permitted.

//solveExpression('?*11=??')
// => 2
//2*11 === 22
//0 is not returned as ?? would become 00, which is not a valid number.
//1 is not returned (despite being smaller, as 1 is already present)

function solveExpression(exp) { 
  for(var i = 0; i < 10; i++)
  {    
      //No rune is present twice.
      if(exp.includes(`${i}`)) continue;
      
      //No leading zeroes
      if(i == 0 && exp.match(/[=*+-]\?[\d\?]/)) continue;
      
      var subbed = exp.split("?").join(i).replace("--", "- -").replace("=", "===")
      if(eval(subbed)) return i;  
  }
  
  return -1;
}