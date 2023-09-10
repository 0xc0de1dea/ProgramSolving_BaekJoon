using System;
using System.Collections.Generic;

class Program
{
    static bool[] checkPN;

    static int[] GetGoldbachPartition(int n)
    {
        List<int[]> partition = new List<int[]>();

        for (int i = 2; i <= n; i++)
            if (!checkPN[i] && !checkPN[n - i])
                partition.Add(new int[2] { i, n - i });

        int[] minGapPartiton = new int[2] { 0, int.MaxValue };

        foreach (var e in partition)
        {
            if (Math.Abs(minGapPartiton[1] - minGapPartiton[0]) > Math.Abs(e[1] - e[0]))
            {
                minGapPartiton[0] = e[0];
                minGapPartiton[1] = e[1];
            }
        }

        return minGapPartiton;        
    }

    static void SetPrimeNumbers(uint from, uint to)
    {
        checkPN = new bool[to + 1];
        checkPN[0] = true;
        checkPN[1] = true;

        int searchMax = (int)Math.Sqrt(to);

        for (int i = 2; i <= searchMax; i++)
            if (!checkPN[i])
                for (int j = i + i; j <= to; j += i)
                    checkPN[j] = true;
    }

    static void Main()
    {
        int t = int.Parse(Console.ReadLine());

        SetPrimeNumbers(1, 10000);

        for (int i = 0; i < t; i++)
        {
            int n = int.Parse(Console.ReadLine());
            int[] partition = GetGoldbachPartition(n);

            Console.WriteLine("{0} {1}", partition[0], partition[1]);
        }
    }
}