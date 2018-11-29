using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodeWarsCSharp.Kyu8
{
    //Kata: https://www.codewars.com/kata/is-he-gonna-survive/train/csharp
    public class BulletSurvival
    {
        public static bool Hero(int bullets, int dragons)
        {
            return bullets / (double)dragons >= 2;
        }
    }
}
