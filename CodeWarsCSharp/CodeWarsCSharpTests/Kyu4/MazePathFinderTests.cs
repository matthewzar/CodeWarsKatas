using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CodeWarsCSharp.Kyu4;
//using Microsoft.VisualStudio.TestTools.UnitTesting;
using NUnit.Framework;

namespace CodeWarsCSharpTests.Kyu4
{
    public class MazePathFinderTests
    {
        [Test]
        public void sampleTests()
        {

            string a = ".W.\n" +
                       ".W.\n" +
                       "...",

                b = ".W.\n" +
                    ".W.\n" +
                    "W..",

                c = "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......",

                d = "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    ".....W\n" +
                    "....W.";

            Assert.AreEqual(true, MazePathFinder.PathFinder(a));
            Assert.AreEqual(false, MazePathFinder.PathFinder(b));
            Assert.AreEqual(true, MazePathFinder.PathFinder(c));
            Assert.AreEqual(false, MazePathFinder.PathFinder(d));
        }
    }
}
