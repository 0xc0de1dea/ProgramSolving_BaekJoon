using System;

class Program
{
    static int n;
    static string start, end;
    static int[,] dp;
    static int[,] cost;

    static int DP(int idx, int turn)
    {
        if (idx >= n)
            return 0;

        if (dp[idx, turn] != -1)
            return dp[idx, turn];

        int leftTurn = (10 + (end[idx] - 48) - (start[idx] - 48 + turn) % 10) % 10;
        int rightTurn = (10 + (start[idx] - 48 + turn) % 10 - (end[idx] - 48)) % 10;

        int leftSumCost = DP(idx + 1, (turn + leftTurn) % 10) + leftTurn;
        int rightSumCost = DP(idx + 1, turn) + rightTurn;

        if (leftSumCost >= rightSumCost)
        {
            dp[idx, turn] = rightSumCost;
            cost[idx, turn] = ~rightTurn + 1;

            return rightSumCost;
        }
        else
        {
            dp[idx, turn] = leftSumCost;
            cost[idx, turn] = leftTurn;

            return leftSumCost;
        }
    }

    static void PrintCost(int idx, int turn)
    {
        if (idx >= n)
            return;

        Console.WriteLine("{0} {1}", idx + 1, cost[idx,turn]);

        if (cost[idx, turn] >= 0)
            PrintCost(idx + 1, (turn + cost[idx, turn]) % 10);
        else
            PrintCost(idx + 1, turn);
    }

    static void Main()
    {
        n = int.Parse(Console.ReadLine());
        start = Console.ReadLine();
        end = Console.ReadLine();
        dp = new int[n, 10];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < 10; j++)
                dp[i, j] = -1;

        cost = new int[n, 10];

        Console.WriteLine(DP(0, 0));
        PrintCost(0, 0);
    }
}