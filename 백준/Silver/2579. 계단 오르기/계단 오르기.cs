using System;

class Program
{
    static int n;
    static int[] stair;
    static int[] dp;

    static int Max(int a, int b)
    {
        return a > b ? a : b;
    }

    static void Main()
    {
        n = int.Parse(Console.ReadLine());
        stair = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++)
            stair[i] = int.Parse(Console.ReadLine());

        if (n >= 1)
            dp[0] = stair[0];

        if (n >= 2)
            dp[1] = stair[0] + stair[1];

        if (n >= 3)
            dp[2] = Max(stair[0] + stair[2], stair[1] + stair[2]);

        for (int i = 3; i < n; i++)
            dp[i] = Max(stair[i] + dp[i - 2], stair[i] + stair[i - 1] + dp[i - 3]);

        Console.Write(dp[n - 1]);
    }
}