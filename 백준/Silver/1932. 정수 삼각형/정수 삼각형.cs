using System;

class Program
{
    static int[,] triangle;
    static int[,] dp;

    static int DP(int n)
    {
        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                if (j == 0)
                    dp[i, j] = dp[i - 1, j] + triangle[i, j];
                else if (j == i)
                    dp[i, j] = dp[i - 1, j - 1] + triangle[i, j];
                else
                    dp[i, j] = (dp[i - 1, j - 1] > dp[i - 1, j] ? dp[i - 1, j - 1] : dp[i - 1, j]) + triangle[i, j];

            }
        }

        int max = 0;

        for (int i = 0; i < n; i++)
            if (max < dp[n - 1, i])
                max = dp[n - 1, i];

        return max;
    }

    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        triangle = new int[n, n];
        dp = new int[n, n];

        for (int i = 0; i < n; i++)
        {
            string[] input = Console.ReadLine().Split();

            for (int j = 0; j <= i; j++)
                triangle[i, j] = int.Parse(input[j]);
        }

        dp[0, 0] = triangle[0, 0];

        if (n == 1)
            Console.Write(triangle[0, 0]);
        else
            Console.Write(DP(n));
    }
}