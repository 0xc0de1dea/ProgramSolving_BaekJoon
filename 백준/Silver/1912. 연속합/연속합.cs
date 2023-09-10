using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        string[] input = Console.ReadLine().Split();
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++)
            dp[i + 1] = int.Parse(input[i]);

        int max = int.MinValue;

        for (int i = 1; i <= n; i++)
        {
            if (dp[i - 1] + dp[i] > 0 && dp[i - 1] > 0)
                dp[i] += dp[i - 1];

            if (max < dp[i])
                max = dp[i];
        }

        Console.Write(max);
    }
}