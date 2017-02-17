using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;
using CodeWarsCSharp.Kyu1;
using NUnit.Framework.Constraints;

namespace CodeWarsCSharpTests.Kyu1
{
    [TestFixture]
    class SkycraperPuzzle6By6Tests
    {
        #region Extension methods

        [TestCase(0, 1, 1)]
        [TestCase(1, 2, 2)]
        [TestCase(-1, 1, 5)] //flat row
        [TestCase(-3, 5, 1)] //tower
        [TestCase(-99, 5, 2)]
        public void MatrixGenerationFromIntTest(int defaultValue, int rows, int columns)
        {
            //Arrange
            int[,] sut = defaultValue.GetMatrixWithDefault(rows, columns);

            //Act

            //Assert
            if (rows > 0 && columns > 0)
            {
                //ensure the dimensions are correct and the defaults are present
                Assert.AreEqual(defaultValue, sut[rows - 1, columns - 1]);
                Assert.AreEqual(defaultValue, sut[0, 0]);
                Assert.AreEqual(rows*columns, sut.GetLength(0)*sut.GetLength(1));
            }
        }

        [Test]
        public void MatrixToJaggedTest()
        {
            //arrange
            var sut = 0.GetMatrixWithDefault(2, 6);
            var expected = new[]
            {
                new[] {0, 0, 0, 0, 0, 0},
                new[] {0, 0, 0, 0, 0, 0}
            };

            //act
            var actual = sut.ConvertMatrixToJaggedArray();

            //assert
            CollectionAssert.AreEqual(expected, actual);
        }

        [TestCase("0", 0, 1, 1)]
        [TestCase("0, 0", 0, 1, 2)]
        [TestCase("0, 0\r\n0, 0", 0, 2, 2)]
        [TestCase("1, 1\r\n1, 1\r\n1, 1", 1, 3, 2)]
        public void MatrixToStringTest(string expected, int defaultValue, int rows, int columns)
        {
            //Arrange
            var sut = defaultValue.GetMatrixWithDefault(rows, columns);

            //Act
            var actual = sut.ToGridString();

            //Assert
            Assert.AreEqual(expected, actual);
        }

        #endregion

        //Expected X4,        Index X1, totalClues
        [TestCase(0, 0, 0, 0, 0, 4)] //4 clues means that there is only 1 index (0)
        [TestCase(0, 0, 0, 0, 1, 4)] //4 clues means that there is only 1 index (0)
        [TestCase(0, 0, 0, 0, 2, 4)] //4 clues means that there is only 1 index (0)
        [TestCase(0, 0, 0, 0, 3, 4)] //4 clues means that there is only 1 index (0)
        [TestCase(0, 0, 1, 0, 0, 8)] //8 clues means 2X2, index 0 means top left to bottom left - down
        [TestCase(0, 1, 1, 1, 1, 8)] //8 clues means 2X2, index 1 means top right to bottom right  - down
        [TestCase(2, 0, 0, 0, 8, 12)] //12 clues means 3X3 - up
        [TestCase(2, 1, 0, 1, 7, 12)] //12 clues means 3X3 - up
        [TestCase(0, 0, 0, 1, 7, 8)] //move right
        [TestCase(1, 0, 1, 1, 6, 8)] //move right
        [TestCase(0, 2, 0, 0, 3, 12)] //left
        [TestCase(2, 2, 2, 0, 5, 12)] //left
        [TestCase(0, 2, 5, 2, 2, 6*4)] //6X6 down for col 3 (index 2)
        public void GetViewingDirectionTest(int expectedRowStart, int expectedColumnStart, int expectedRowEnd,
            int expectedColumnEnd, int targetIndex, int totalClues)
        {
            //arrange
            //For this test only clue dimensions matter, not their content, hence the blank declaration
            var clues = new int[totalClues];

            //act
            var sut = SkyscrapersPuzzle6By6.GetViewingDirection(clues, targetIndex);

            //assert
            Assert.AreEqual(expectedRowStart, sut.Item1, "for row start index");
            Assert.AreEqual(expectedColumnStart, sut.Item2, "for col start index");

            Assert.AreEqual(expectedRowEnd, sut.Item3, "for row end index");
            Assert.AreEqual(expectedColumnEnd, sut.Item4, "for col end index");
        }

        [Test] //6X6 down for col 3 (index 2)
        public void IsValidViewTest()
        {
            //arrange
            var correctClues = new[]
            {
                3, 2, 2, 2, 2, 1,
                1,
                2,
                3,
                3,
                2,
                6,
                3, 2, 2, 2, 2, 1,
                1, 3, 2, 4, 2, 6
            };

            var incorrectClues = new[]
            {
                4, 1, 3, 5, 6, 2,
                2,
                1,
                1,
                6,
                5,
                1,
                4, 5, 6, 3, 1, 2,
                2, 4, 3, 5, 6, 1
            };

            //not a valid grid, but for testing visibility it provides many cases
            int[,] grid =
            {
                {1, 2, 3, 4, 5, 6},
                {5, 6, 1, 2, 3, 4},
                {3, 4, 5, 6, 2, 1},
                {5, 6, 3, 2, 4, 1},
                {2, 1, 4, 3, 6, 5},
                {6, 5, 4, 3, 2, 1}
            };

            //act
            bool actual;

            //assert
            for (int i = 0; i < correctClues.Length; i++)
            {
                actual = SkyscrapersPuzzle6By6.IsValidView(grid, correctClues, i);
                Assert.AreEqual(true, actual,
                    $"Visible buildings don't match clue at index {i} for correct clues (clue says {correctClues[i]} should be visible)");
                actual = SkyscrapersPuzzle6By6.IsValidView(grid, incorrectClues, i);
                Assert.AreEqual(false, actual,
                    $"Visible buildings match clue at index {i} for incorrect clues (clue says {incorrectClues[i]} should be visible)");
            }
        }


        [TestCase(0, 0, 1, 2, 3)]//looking down and right all these low numbers avoid violating views (this isn't a check for duplicates)
        [TestCase(1, 1, 6)]
        [TestCase(2, 2, 6)]
        [TestCase(3, 3, 1, 2, 3, 4, 5)]
        [TestCase(4, 4, 1, 2, 3, 6)]
        [TestCase(5, 5, 1, 2)]
        public void GetNonViewViolatingSizesTest(int row, int col, params int[] expectedClues)
        {
            //arrange
            var clues = new[]
            {
                3, 2, 2, 3, 2, 1,
                1, 2, 3, 3, 2, 2,
                5, 1, 2, 2, 4, 3,
                3, 2, 1, 2, 2, 4
            };

            int[,] grid =
            {
                //clues:3, 2, 2, 3, 2, 1
                /*4*/  {0, 1, 4, 3, 5, 6}, //1
                /*2*/  {1, 0, 3, 2, 4, 5}, //2
                /*2*/  {4, 3, 0, 5, 1, 2}, //3
                /*1*/  {6, 5, 2, 0, 3, 4}, //3
                /*2*/  {5, 4, 1, 6, 0, 3}, //2
                /*3*/  {3, 2, 5, 4, 6, 0} //2  
                //clues:3, 4, 2, 2, 1, 5,
            };

            //act
            var actual = SkyscrapersPuzzle6By6.GetNonViewViolatingSizes(grid, clues, row, col).ToArray();

            //assert
            CollectionAssert.AreEqual(expectedClues, actual);
        }


        [TestCase(0, 0, 2)]
        [TestCase(1, 1, 6)]
        [TestCase(2, 2, 6)]
        [TestCase(3, 3, 1)]
        [TestCase(4, 4, 2)]
        [TestCase(5, 5, 1)]
        public void GetNonViewAndNonDuplicateViolatingSizesTest(int row, int col, params int[] expectedClues)
        {
            //arrange
            var clues = new[]
            {
                3, 2, 2, 3, 2, 1,
                1, 2, 3, 3, 2, 2,
                5, 1, 2, 2, 4, 3,
                3, 2, 1, 2, 2, 4
            };

            int[,] grid =
            {
                //clues:3, 2, 2, 3, 2, 1
                /*4*/  {0, 1, 4, 3, 5, 6}, //1
                /*2*/  {1, 0, 3, 2, 4, 5}, //2
                /*2*/  {4, 3, 0, 5, 1, 2}, //3
                /*1*/  {6, 5, 2, 0, 3, 4}, //3
                /*2*/  {5, 4, 1, 6, 0, 3}, //2
                /*3*/  {3, 2, 5, 4, 6, 0} //2  
                //clues:3, 4, 2, 2, 1, 5,
            };

            //act
            var actual = SkyscrapersPuzzle6By6.GetNonViewAndNonDuplicateViolatingSizes(grid, clues, row, col).ToArray();

            //assert
            CollectionAssert.AreEqual(expectedClues, actual);
        }


        #region default test cases

        [Test]
        public void SolvePuzzle1()
        {
            var clues = new[]
            {
                3, 2, 2, 3, 2, 1,
                1, 2, 3, 3, 2, 2,
                5, 1, 2, 2, 4, 3,
                3, 2, 1, 2, 2, 4
            };

            var expected = new[]
            {
                new[] {2, 1, 4, 3, 5, 6},
                new[] {1, 6, 3, 2, 4, 5},
                new[] {4, 3, 6, 5, 1, 2},
                new[] {6, 5, 2, 1, 3, 4},
                new[] {5, 4, 1, 6, 2, 3},
                new[] {3, 2, 5, 4, 6, 1}
            };

            var actual = SkyscrapersPuzzle6By6.SolvePuzzle(clues);
            CollectionAssert.AreEqual(expected, actual);
        }

        [Test]
        public void SolvePuzzle2()
        {
            var clues = new[]
            {
                0, 0, 0, 2, 2, 0,
                0, 0, 0, 6, 3, 0,
                0, 4, 0, 0, 0, 0,
                4, 4, 0, 3, 0, 0
            };

            var expected = new[]
            {
                new[] {5, 6, 1, 4, 3, 2},
                new[] {4, 1, 3, 2, 6, 5},
                new[] {2, 3, 6, 1, 5, 4},
                new[] {6, 5, 4, 3, 2, 1},
                new[] {1, 2, 5, 6, 4, 3},
                new[] {3, 4, 2, 5, 1, 6}
            };

            var actual = SkyscrapersPuzzle6By6.SolvePuzzle(clues);
            CollectionAssert.AreEqual(expected, actual);
        }

        [Test]
        public void SolvePuzzle3()
        {
            var clues = new[]
            {
                0, 3, 0, 5, 3, 4,
                0, 0, 0, 0, 0, 1,
                0, 3, 0, 3, 2, 3,
                3, 2, 0, 3, 1, 0
            };

            var expected = new[]
            {
                new[] {5, 2, 6, 1, 4, 3},
                new[] {6, 4, 3, 2, 5, 1},
                new[] {3, 1, 5, 4, 6, 2},
                new[] {2, 6, 1, 5, 3, 4},
                new[] {4, 3, 2, 6, 1, 5},
                new[] {1, 5, 4, 3, 2, 6}
            };

            var actual = SkyscrapersPuzzle6By6.SolvePuzzle(clues);
            CollectionAssert.AreEqual(expected, actual);
        }

        #endregion
    }
}