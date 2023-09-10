using System;

class Program
{
    static long[] memo = new long[101];

    static long DP(int n)
    {
        if (memo[n] != 0)
            return memo[n];

        return memo[n] = DP(n - 1) + DP(n - 5);
    }

    static void Main()
    {
        int t = int.Parse(Console.ReadLine());
        int n;
        memo[1] = memo[2] = memo[3] = 1;
        memo[4] = memo[5] = 2;

        for (int i = 0; i < t; i++)
        {
            n = int.Parse(Console.ReadLine());
            DP(n);
            Console.WriteLine(memo[n]);
        }
    }
}