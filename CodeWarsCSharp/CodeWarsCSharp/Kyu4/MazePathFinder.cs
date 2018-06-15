using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodeWarsCSharp.Kyu4
{
    /// <summary>
    /// Kata: https://www.codewars.com/kata/path-finder-number-1-can-you-reach-the-exit/csharp
    /// Brief: Write a function to determine if there is a path between 2 points in a maze.
    /// </summary>
    public class MazePathFinder
    {
        private int N;
        private bool[,] unvisitedSpaces;
        private List<Tuple<int, int>> moves;
        private string[] maze;
        public static bool PathFinder(string maze)
        {
            MazePathFinder f = new MazePathFinder(maze.Split('\n'));
            return f.isSolvable();
        }

        private MazePathFinder(string[] maze)
        {
            N = maze.Length;
            unvisitedSpaces = new bool[N, N];
            moves = new List<Tuple<int, int>> { new Tuple<int, int>(0, 0) };
            this.maze = maze;
        }

        /// <summary>
        /// A breadth-first non-recursive traversal of the maze.
        /// </summary>
        /// <returns>True if an unobstructed path exists between point [0,0] and [N,N]</returns>
        private bool isSolvable()
        {
            var nextMoveIndex = 0;
            int row, col;
            while (true)
            {
                row = moves[nextMoveIndex].Item1;
                col = moves[nextMoveIndex++].Item2;

                getNextSteps(row, col);
                if (row == N - 1 && col == N - 1) return true;
                if (nextMoveIndex >= moves.Count) return false;
            }
        }

        private static readonly int[][] directions = { new[] { -1, 0 }, new[] { 1, 0 }, new[] { 0, -1 }, new[] { 0, 1 } };
        private void getNextSteps(int row, int col)
        {
            foreach (var pair in directions)
            {
                int newR = row + pair[0];
                int newC = col + pair[1];
                if (newR < 0 || newC < 0 || newR >= N || newC >= N) continue;
                if (maze[newR][newC] != '.' || unvisitedSpaces[newR, newC]) continue;

                unvisitedSpaces[newR, newC] = true;
                moves.Add(new Tuple<int, int>(newR, newC));
            }
        }
    }
}
