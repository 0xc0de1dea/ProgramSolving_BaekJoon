using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[][] wire = new int[n][];
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 0;
        
        for (int i = 0; i < n; i++)
        {
            string[] input = Console.ReadLine().Split();
            wire[i] = new int[] { int.Parse(input[0]), int.Parse(input[1]) };
        }

        Array.Sort(wire, delegate (int[] a, int[] b)
        {
            return a[0] - b[0];
        });

        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j <= i - 1; j++)
                if (wire[i][1] > wire[j][1])
                    if (max < dp[j])
                        max = dp[j];

            dp[i] = max + 1;
            max = 0;
        }

        for (int i = 0; i < n; i++)
            if (max < dp[i])
                max = dp[i];

        Console.Write(n - max);
    }
}