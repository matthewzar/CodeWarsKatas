//https://www.codewars.com/kata/absent-vowel/train/javascript
//Your job is to figure out the index of which SINGLE vowel is not present in a string.
//Indexes: {A: 0, E: 1, I: 2, O: 3, U: 4}
//EG absentVowel("Hello you, my name's bob.") --> 2 (only 'i' isn't present)

function absentVowel(sentence){
    vowels = "aeiou";
    for(var i = 0; i < vowels.length; i++)
    {
        if(!sentence.includes(vowels[i])) return i;
    }
}

//Clever version using a Lambda and LINQ-like notation:
//const absentVowel = string =>
//  [...'aeiou'].findIndex(letter => !string.includes(letter))


//Test.assertEquals(absentVowel("John Doe hs seven red pples under his bsket"), 0);
//Test.assertEquals(absentVowel("Bb Smith sent us six neatly arranged range bicycles"), 3);