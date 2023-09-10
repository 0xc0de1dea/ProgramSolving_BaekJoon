using System;

class Program
{
    static void Main()
    {
        string[] input1 = Console.ReadLine().Split();
        string[] input2;

        int n = int.Parse(input1[0]);
        int k = int.Parse(input1[1]);
        int[,] item = new int[n + 1, 2];
        int[,] dp = new int[n + 1, k + 1];

        for (int i = 1; i <= n; i++)
        {
            input2 = Console.ReadLine().Split();
            item[i, 0] = int.Parse(input2[0]);
            item[i, 1] = int.Parse(input2[1]);
        }

        for (int i = 1; i <= n; i++)
        {
            for (int j = 0; j <= k; j++)
            {
                dp[i, j] = dp[i - 1, j];

                if (j - item[i, 0] >= 0)
                    dp[i, j] = Math.Max(dp[i - 1, j], dp[i - 1, j - item[i, 0]] + item[i, 1]);
            }
        }

        Console.Write(dp[n, k]);
    }
}
