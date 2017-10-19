using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using CodeWarsCSharp.Kyu1;

namespace CodeWarsCSharp
{
    public class DemoClass
    {
        private string _privateField;

        public string PublicField = "None";

        public int LimitedProperty { get; private set; }

        public DemoClass(int startValue)
        {
            LimitedProperty = startValue;
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            //                        var clues = new[]
            //                        {
            //                            3, 2, 2, 3, 2, 1,
            //                            1, 2, 3, 3, 2, 2,
            //                            5, 1, 2, 2, 4, 3,
            //                            3, 2, 1, 2, 2, 4
            //                        };
            //
            //            var expected = new[]
            //            {
            //                new[] {2, 1, 4, 3, 5, 6},
            //                new[] {1, 6, 3, 2, 4, 5},
            //                new[] {4, 3, 6, 5, 1, 2},
            //                new[] {6, 5, 2, 1, 3, 4},
            //                new[] {5, 4, 1, 6, 2, 3},
            //                new[] {3, 2, 5, 4, 6, 1}
            //            };
            //
            //            var actual = SkyscrapersPuzzle6By6.SolvePuzzle(clues);


            //Check that it has the correct constructor signature
            Type magicType = typeof(DemoClass);
            ConstructorInfo magicConstructor = magicType.GetConstructor(new []{typeof(int)});
            object magicClassObject = magicConstructor.Invoke(new object[] { 24 });

            //check that it has the required private and public fields
            FieldInfo privateField = typeof(DemoClass).GetField("_privateField", BindingFlags.NonPublic | BindingFlags.Instance);

            if (privateField != null)
            {
                Console.WriteLine("Private field found");
            }
            else
            {
                Console.WriteLine("Private field NOT found");
            }

            var sut = new DemoClass(0);
            FieldInfo publicField = typeof(DemoClass).GetField("PublicField", BindingFlags.Public | BindingFlags.Instance);
            if (publicField != null)
            {
                Console.WriteLine(publicField.GetValue(sut));
                Console.WriteLine("Public field found");
            }
            else
            {
                Console.WriteLine("Public field NOT found");
            }

            PropertyInfo magicProperty = magicType.GetProperty("LimitedProperty", BindingFlags.Instance | BindingFlags.Public);
            if(magicProperty.GetGetMethod() != null)
                Console.WriteLine("Public Property exists");
            if (magicProperty.GetSetMethod(nonPublic:true) != null)
                Console.WriteLine("Private Property setter exists");
            MethodInfo magicMethod = magicProperty.GetSetMethod(true);
            magicMethod.Invoke(magicClassObject, new object[] { 100 });
            if(((DemoClass)magicClassObject).LimitedProperty == 100)
                Console.WriteLine("YEAH");
            
            Console.WriteLine(((DemoClass)magicClassObject).LimitedProperty);
            magicMethod.Invoke(magicClassObject, new object[] { 100 });
            Console.WriteLine(((DemoClass)magicClassObject).LimitedProperty);
            //            if (fi.GetValue() fi.GetCustomAttributes(typeof(SomeAttribute)) != null)
            //            {
            //
            //            }

            Console.WriteLine("Press any key to quit");
            Console.ReadKey();
        }
    }
}