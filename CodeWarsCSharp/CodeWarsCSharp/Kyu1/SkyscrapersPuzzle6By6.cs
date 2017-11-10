using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Runtime.InteropServices.ComTypes;
using System.Runtime.Remoting.Messaging;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace CodeWarsCSharp.Kyu1
{
    /// <summary>
    /// Working with 2d arrays/matrixes? These extension should be helpful
    /// </summary>
    public static class MatrixExtensions
    {
        /// <summary>
        /// Creates a matrix of the given size where the default value is inserted in all locations.
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="defaultValue"></param>
        /// <param name="columns"></param>
        /// <param name="rows"></param>
        /// <returns>new T[columns,rows]</returns>
        public static T[,] GetMatrixWithDefault<T>(this T defaultValue, int rows, int columns)
            where T : struct
        {
            var ret = new T[rows, columns];
            for (var row = 0; row < rows; row++)
                for (var col = 0; col < columns; col++)
                    ret[row, col] = defaultValue;
            return ret;
        }

        /// <summary>
        /// Take a 2d matrix and convert it into a 'jagged' array. Dimensions and index positions won't change.
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="matrix"></param>
        /// <returns></returns>
        public static T[][] ConvertMatrixToJaggedArray<T>(this T[,] matrix)
        {
            var ret = new T[matrix.GetLength(0)][];
            for (var row = 0; row < ret.Length; row++)
            {
                ret[row] = new T[matrix.GetLength(1)];
                for (var col = 0; col < ret[row].Length; col++)
                    ret[row][col] = matrix[row, col];
            }
            return ret;
        }

        public static string ToGridString<T>(this T[,] matrix, string seperator = ", ")
        {
            var ret = new StringBuilder();
            for (var row = 0; row < matrix.GetLength(0); row++)
            {
                for (var col = 0; col < matrix.GetLength(1); col++)
                {
                    ret.Append(matrix[row, col]);
                    if (col < matrix.GetLength(1) - 1) ret.Append(seperator);
                }

                if (row < matrix.GetLength(0) - 1) ret.AppendLine();
            }

            return ret.ToString();
        }
    }

    /// <summary>
    /// https://www.codewars.com/kata/6-by-6-skyscrapers/train/csharp
    /// </summary>
    public class SkyscrapersPuzzle6By6
    {
        public static int[][] SolvePuzzle(int[] clues)
        {
            var solution = 0.GetMatrixWithDefault(6, 6);

            FillIn1andMaxclues(solution, clues);
            var emptiesFound = 36;
            while (emptiesFound > 0)
            {
                emptiesFound = 0;
                for (var row = 0; row < solution.GetLength(0); row++)
                    for (var col = 0; col < solution.GetLength(1); col++)
                    {
                        var possibleMoves = GetNonViewAndNonDuplicateViolatingSizes(solution, clues, row, col);
                        if (possibleMoves.Count == 1) solution[row, col] = possibleMoves[0];
                        //if(possibleMoves.Count == 0) throw new Exception($"No possible moves for row {row}, column {col}");

                        if (solution[row, col] == 0) emptiesFound++;
                    }

                //nothing solved, give up
                if (emptiesFound == 36)
                    break;
                Console.WriteLine(emptiesFound);
            }
            // Start your coding here...
            return solution.ConvertMatrixToJaggedArray();
        }

        /// <summary>
        /// Checks if a given clue is being violated for a given layout
        /// </summary>
        /// <param name="grid"></param>
        /// <param name="clues"></param>
        /// <param name="clueIndex"></param>
        /// <returns>Returns true if the Clue is not being violated</returns>
        public static bool IsValidView(int[,] grid, int[] clues, int clueIndex)
        {
            int rowStart, colStart, rowEnd, colEnd, colInc, rowInc;
            GetDirectionIterationData(clues, clueIndex, out rowStart, out colStart, out rowEnd,
                out colEnd, out colInc, out rowInc);
            var maxValue = clues.Length/4;

            var maxSoFar = 0;
            var visible = 0;
            for (int row = rowStart, col = colStart;; row += rowInc, col += colInc)
            {
                if (grid[row, col] > maxSoFar)
                {
                    maxSoFar = grid[row, col];
                    visible++;
                }
                if (maxSoFar >= maxValue || row == rowEnd && col == colEnd)
                    return visible == clues[clueIndex];
            }
        }

        public static void FillIn1andMaxclues(int[,] grid, int[] clues)
        {
            int rowStart, colStart, rowEnd, colEnd, colInc, rowInc;


            var cc = clues.Length; //cluecount;
            var maxValue = cc/4;
            int[] cluesIndexes = new int[4];

//                                { col  //top
//                                  ,cc/4+row //right
//                                  ,cc / 2 + (cc / 4 - col) - 1 //bottom
//                                  ,cc-row-1}; //left
            for (int clueIndex = 0; clueIndex < clues.Length; clueIndex++)
            {
                if (clues[clueIndex] == maxValue)
                {
                    GetDirectionIterationData(clues, clueIndex, out rowStart, out colStart, out rowEnd,
                        out colEnd, out colInc, out rowInc);

                    int cnt = 1;
                    for (int row = rowStart, col = colStart;; row += rowInc, col += colInc)
                    {
                        if (grid[row, col] != 0 && grid[row, col] != cnt)
                            throw new Exception("Your overwritting a value with something different?");
                        grid[row, col] = cnt++;
                        if (row == rowEnd && col == colEnd) break;
                    }
                }
                else if (clues[clueIndex] == 1)
                {
                    GetDirectionIterationData(clues, clueIndex, out rowStart, out colStart, out rowEnd,
                        out colEnd, out colInc, out rowInc);
                    if (grid[rowStart, colStart] != 0 && grid[rowStart, colStart] != maxValue)
                        throw new Exception("Your overwritting a value with something different?");
                    grid[rowStart, colStart] = maxValue;
                }
            }
        }

        /// <summary>
        /// A simple wrapper to get direcitonal info about clues, useful for iterating over rows/columns in the right order
        /// </summary>
        /// <param name="clues"></param>
        /// <param name="clueIndex"></param>
        /// <param name="rowStart"></param>
        /// <param name="colStart"></param>
        /// <param name="rowEnd"></param>
        /// <param name="colEnd"></param>
        /// <param name="colInc"></param>
        /// <param name="rowInc"></param>
        private static void GetDirectionIterationData(int[] clues, int clueIndex, out int rowStart, out int colStart,
                out int rowEnd, out int colEnd, out int colInc, out int rowInc)
        {
            var startEndDirections = GetViewingDirection(clues, clueIndex);
            rowStart = startEndDirections.Item1;
            colStart = startEndDirections.Item2;
            rowEnd = startEndDirections.Item3;
            colEnd = startEndDirections.Item4;

            var right = rowStart == rowEnd && colStart < colEnd;
            var left = rowStart == rowEnd && colStart > colEnd;
            var down = colStart == colEnd && rowStart < rowEnd;
            var up = colStart == colEnd && rowStart > rowEnd;

            colInc = right ? 1 : (left ? -1 : 0);
            rowInc = down ? 1 : (up ? -1 : 0);
        }

        /// <summary>
        /// Looks at a given coordinate, compares all possible values to the 4 relevant clues,  and returns a list of moves
        /// that wouldn't (immediately) violate any clues.
        /// When a list of length 1 is returned it must be the correct move, if a list of 0 is returned then something is wrong, 
        /// you're probably looking at a solved square
        /// </summary>
        /// <param name="grid"></param>
        /// <param name="clues"></param>
        /// <param name="row"></param>
        /// <param name="column"></param>
        /// <returns></returns>
        public static List<int> GetNonViewViolatingSizes(int[,] grid, int[] clues, int row, int column)
        {
            if (grid[row, column] != 0) return new List<int>();

            var ret = new List<int>();
            var cc = clues.Length; //cluecount;
            var maxValue = cc/4;

            //find the clue indexes that match the current row and columns
            int[] cluesIndexes =
            {
                column //top
                , cc/4 + row //right
                , cc/2 + (cc/4 - column) - 1 //bottom
                , cc - row - 1
            }; //left
            for (int i = 1; i <= maxValue; i++)
            {
                grid[row, column] = i;
                if (IsValidView(grid, clues, cluesIndexes[0]) &&
                    IsValidView(grid, clues, cluesIndexes[1]) &&
                    IsValidView(grid, clues, cluesIndexes[2]) &&
                    IsValidView(grid, clues, cluesIndexes[3])) ret.Add(i);
            }
            //no persistent side effects, must reset
            grid[row, column] = 0;
            return ret;
        }

        public static List<int> GetNonViewAndNonDuplicateViolatingSizes(int[,] grid, int[] clues, int row, int column)
        {
            var moves = GetNonViewViolatingSizes(grid, clues, row, column);
            if (moves.Count <= 1) return moves;

            var cc = clues.Length; //cluecount;
            //find the clue indexes that match the current row and columns
            int[] cluesIndexes =
            {
                column //top
                , cc/4 + row //right
                , cc/2 + (cc/4 - column) - 1 //bottom
                , cc - row - 1
            }; //left

            for (var i = 0; i < grid.GetLength(0); i++)
            {
                //note that this only works this way for square grids, 2 seperte loops for rectangulars is needed.
                moves.Remove(grid[i, column]);
                moves.Remove(grid[row, i]);
            }

            return moves;
        }

        /// <summary>
        /// Given a list of clues of the index of the clue you're interested in, work out if its a left/right/up/down
        /// direction, and then return the start/end row/column coords that would correspond to said direction.
        /// For example in a simple 3X3 grid, a clue of index 1 will take you from index 0,1 to 2,1
        /// </summary>
        /// <param name="clues"></param>
        /// <param name="index"></param>
        /// <returns>rowStart, colStart, rowEnd, colEnd - if a start is less than end it just means going backwards in the grid</returns>
        public static Tuple<int, int, int, int> GetViewingDirection(int[] clues, int index)
        {
            if (clues.Length%4 != 0)
            {
                throw new ArgumentException($"Clues needs to be divisible by 4. {clues.Length} is not.");
            }

            var maxIndex = clues.Length/4 - 1; //always the start/end of one the two
            var bottomCol = (clues.Length/4)*3 - index - 1;
            var rightSideRow = index - (clues.Length/4);
            var leftSideRow = clues.Length - index - 1;

            if (index < clues.Length/4) return new Tuple<int, int, int, int>(0, index, maxIndex, index); //move down 
            if (index < clues.Length/2)
                return new Tuple<int, int, int, int>(rightSideRow, maxIndex, rightSideRow, 0); //move left 
            if (index < (clues.Length/4)*3)
                return new Tuple<int, int, int, int>(maxIndex, bottomCol, 0, bottomCol); //move up
            return new Tuple<int, int, int, int>(leftSideRow, 0, leftSideRow, maxIndex); //move right
        }
    }
}