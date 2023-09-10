using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[] amount = new int[n + 4];
        int[] dp = new int[n + 4];
        int case1, case2;

        for (int i = 3; i < n + 3; i++)
        {
            amount[i] = int.Parse(Console.ReadLine());
            case1 = amount[i] + dp[i - 2];
            case2 = amount[i] + amount[i - 1] + dp[i - 3];
            case1 = case1 > case2 ? case1 : case2;
            case2 = dp[i - 1];
            dp[i] = case1 > case2 ? case1 : case2;
        }

        case1 = dp[n + 1];
        case2 = amount[n + 2] + dp[n];
        case1 = case1 > case2 ? case1 : case2;
        case2 = dp[n + 2];
        dp[n + 3] = case1 > case2 ? case1 : case2;
        Console.Write(dp[n + 3]);
    }
}