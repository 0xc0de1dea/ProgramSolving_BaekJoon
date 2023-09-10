using System;

class Program
{
    static void Main()
    {
        int t = int.Parse(Console.ReadLine());

        for (int i = 0; i < t; i++)
        {
            string test_Case = Console.ReadLine();
            int repeat = int.Parse(test_Case[0].ToString());

            for (int a = 2; a < test_Case.Length; a++)
            {
                for (int b = 0; b < repeat; b++)
                {
                    Console.Write(test_Case[a]);
                }
            }

            Console.WriteLine();
        }
    }
}