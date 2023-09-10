using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        for (int i = 0; i < n; i++)
        {
            int[] class_Score = Console.ReadLine().Split().Select(int.Parse).ToArray();
            float sum = 0, avr = 0, cnt = 0;

            for (int j = 1; j <= class_Score[0]; j++)
                sum += class_Score[j];

            avr = sum / class_Score[0];

            for (int j = 1; j <= class_Score[0]; j++)
                if (class_Score[j] > avr)
                    cnt++;

            Console.WriteLine(Math.Round(cnt / class_Score[0] * 100, 3) + "%");
        }
    }
}