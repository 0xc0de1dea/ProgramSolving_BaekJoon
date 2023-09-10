using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int score = 0, combo = 0;

        for (int i = 0; i < n; i++)
        {
            string result = Console.ReadLine();

            for (int j = 0; j < result.Length; j++)
            {
                if (result[j] == 'O')
                {
                    score += ++combo;
                }
                else
                    combo = 0;
            }

            Console.WriteLine(score);
            score = combo =0;
        }
    }
}