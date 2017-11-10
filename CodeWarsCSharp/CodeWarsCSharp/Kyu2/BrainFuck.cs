using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace CodeWarsCSharp.Kyu2
{
    /// <summary>
    /// https://www.codewars.com/kata/526156943dfe7ce06200063e
    /// </summary>
    public class BrainFuckInterpreter
    {
        string instructions;
        string inputStream;
        int pntr;
        List<int> mem = new List<int>() {0};
        string outputString = "";
        int instPointer;

        private Dictionary<char, Action> ops;

        public BrainFuckInterpreter(string program, string userInput)
        {
            instructions = program;
            inputStream = userInput;
            ops = new Dictionary<char, Action>()
            {
                { '>', incPntr},
                { '<', decPntr},
                { '+', incByte},
                { '-', decByte},
                { '.', output},
                { ',', input},
                { '[', jmpIfZero},
                { ']', jmpIfNonZero},
            };
        }

        public string Run()
        {
            char op;
            while (instPointer < instructions.Length)
            {
                op = instructions[instPointer];
                if (ops.ContainsKey(op))
                    ops[op]();
                else
                    instPointer++;
            }
            return outputString;
        }

        private void incPntr()
        {
            pntr++;
            if (pntr >= mem.Count)
            {
                mem.Add(0);
            }
            instPointer++;
        }

        private void decPntr()
        {
            pntr--;
            if (pntr < 0)
            {
                pntr = 0;
                mem.Insert(0,0);
            }
            instPointer++;
        }

        private void incByte()
        {
            mem[pntr] = (mem[pntr] + 1)%256;
            instPointer++;
        }

        private void decByte()
        {
            mem[pntr]--;
            if (mem[pntr] == -1)
            {
                mem[pntr] = 255;
            }
            instPointer++;
        }

        private void output()
        {
            outputString += (char)mem[pntr];
            instPointer++;
        }

        private void input()
        {
            mem[pntr] = inputStream[0];
            inputStream = inputStream.Substring(1);
            instPointer++;
        }

        private void jmpIfZero()
        {
            if (mem[pntr] == 0)
            {
                var matches = 1;
                do
                {
                    instPointer++;
                    if (instructions[instPointer] == '[')
                        matches++;
                    if (instructions[instPointer] == ']')
                        matches--;
                } while (matches != 0);
            }
            instPointer++;
        }

        private void jmpIfNonZero()
        {
            if (mem[pntr] != 0)
            {
                var matches = 1;
                do
                {
                    instPointer--;
                    if (instructions[instPointer] == '[')
                        matches--;
                    if (instructions[instPointer] == ']')
                        matches++;
                } while (matches != 0);
            }

            instPointer++;
        }
    }
}
