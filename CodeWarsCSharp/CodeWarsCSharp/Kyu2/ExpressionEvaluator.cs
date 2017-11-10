using System;
using System.CodeDom.Compiler;
using System.Reflection;
using Microsoft.CSharp;

namespace CodeWarsCSharp.Kyu2
{
    /// <summary>
    /// https://www.codewars.com/kata/evaluate-mathematical-expression/
    /// 
    /// Given a maths expression as a string, calculate and return the answer.
    /// Expected operations include: /*-+
    /// Order of operations is standard BODMAS
    /// 
    /// This is not an efficient way to write a small calculator. In fact it slows
    /// tests down considerably compared to a simple parse.
    /// However it is much more fun to make the .Net compiler do the work for you.
    /// AND it gives way more than just 4 operations (ternatries and LINQ are some 
    /// examples of what could be calculated this way.
    /// 
    /// An unmodified version of this exists in my 2014 MSC project, that  
    /// infers Types, and publicly exposes more than the 'trivial' double version
    /// </summary>
    public class ExpressionEvaluator
    {
        private static string dynamicCalc(string theEquation)
        {
            CompilerParameters CompilerParams = new CompilerParameters
            {
                GenerateInMemory = true,
                TreatWarningsAsErrors = false,
                GenerateExecutable = false,
                CompilerOptions = "/optimize"
            };

            string[] references = { "System.dll" };
            CompilerParams.ReferencedAssemblies.AddRange(references);
            
            CSharpCodeProvider provider = new CSharpCodeProvider();
            CompilerResults compile;
            int attemptCount = 0;
            do
            {
                if (attemptCount > 1)
                {
                    theEquation = theEquation.Substring(2, theEquation.Length - 4);//remove the extra quotation marks that were added
                    Console.WriteLine($"We where unable to compile your statement of <{theEquation}>");
                    Console.WriteLine("So we are going to assume it is a string in its simplest form and return that to you");
                    theEquation = theEquation.Remove(theEquation.IndexOf('"'), 1);
                    theEquation = theEquation.Remove(theEquation.LastIndexOf('"'), 1);
                    return '"' + theEquation + '"';
                }

                string[] code ={("using System;\n namespace DynaCore\n{\npublic class DynaCore\n{" +
                                   "  static public string Main()\n" +
                                   "  {\ntry\n{\n" +
                                  $"           object x = {theEquation};\n"+
                                   "           Type theType = x.GetType();\n"+
                                   "           string typeName = theType.Name;\n"+
                                   "           if (typeName == \"String\")\n"+
                                  $"               return '\"' + ({theEquation}).ToString() + '\"';\n"+
                                   "           else\n"+
                                  $"               return ({theEquation}).ToString();\n"+
                                   "       }\n" +
                                   "       catch (Exception e)\n" +
                                   "       {\nreturn \"Your Input To The Calculator Was Malformed and threw this exception:\"+e.ToString();\n}\n" +
                                   "}\n}\n}\n")};

                compile = provider.CompileAssemblyFromSource(CompilerParams, code); //this is where the code is actually compiled

                if (compile.Errors.HasErrors)
                {
                    Console.WriteLine("CODE CONTAINED ERRORS, ARE YOU PERHAPS ATTEMPTING TO COMPILE A 'PLAIN' STRING WITHOUT QUOTES?");
                    Console.WriteLine("We will attempt to correct the error by adding quotes to the begining and end of your input");
                    theEquation = '"' + theEquation + '"';
                    attemptCount++;
                }

            } while (compile.Errors.HasErrors);

            Module module = compile.CompiledAssembly.GetModules()[0];
            Type mt = module?.GetType("DynaCore.DynaCore");
            MethodInfo methInfo = mt?.GetMethod("Main");
            
            return methInfo?.Invoke(null, new object[] { }).ToString();
        }

        private static string CalculateToString(string theEquation)
        {
            if (string.IsNullOrEmpty(theEquation)) return "";

            try
            {
                return dynamicCalc(theEquation);
            }
            catch
            {
                Console.WriteLine("WARNING: That Appears to be a malformed expression we're assuming its a string");
                return '"' + theEquation + '"';
            }
        }

        public static double Evaluate(string equation)
        {
            //Force a double cast of the first element to discourage accidental integer division - this is a hack
            return double.Parse(CalculateToString("(double)"+equation));
        }
    }
}
