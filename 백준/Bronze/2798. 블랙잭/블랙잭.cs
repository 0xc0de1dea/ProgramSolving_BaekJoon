using System;
using System.Linq;

class Program
{
    static void Main()
    {
        string[] input = Console.ReadLine().Split();

        int n = int.Parse(input[0]);
        int m = int.Parse(input[1]);
        int[] cards = Console.ReadLine().Split().Select(int.Parse).ToArray();

        int max = 0;

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                for (int k = j + 1; k < n; k++)
                {
                    int sum = cards[i] + cards[j] + cards[k];

                    if (sum <= m && sum > max)
                        max = sum;
                }
            }
        }

        Console.Write(max);
    }
}