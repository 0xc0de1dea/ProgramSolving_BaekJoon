using System;
using System.Linq;

class Program
{
    static int n;
    static int[] team1, team2;
    static int[,] abillity;
    static int min = int.MaxValue;

    static void Dfs(int idx1, int idx2)
    {
        if (idx1 + idx2 == n)
        {
            int sum1 = 0, sum2 = 0;

            for (int i = 0; i < n / 2 - 1; i++)
            {
                for (int j = i + 1; j <= n / 2 - 1; j++)
                {
                    sum1 += abillity[team1[i], team1[j]];
                    sum1 += abillity[team1[j], team1[i]];
                    sum2 += abillity[team2[i], team2[j]];
                    sum2 += abillity[team2[j], team2[i]];
                }
            }

            if (min > Math.Abs(sum1 - sum2))
                min = Math.Abs(sum1 - sum2);

            return;
        }

        if (idx1 == n / 2)
        {
            team2[idx2] = idx1 + idx2;
            Dfs(idx1, idx2 + 1);
        }
        else if (idx2 == n / 2)
        {
            team1[idx1] = idx1 + idx2;
            Dfs(idx1 + 1, idx2);
        }
        else
        {
            for (int i = 1; i <= 2; i++)
            {
                switch (i)
                {
                    case 1:
                        team1[idx1] = idx1 + idx2;
                        Dfs(idx1 + 1, idx2);
                        break;

                    case 2:
                        team2[idx2] = idx1 + idx2;
                        Dfs(idx1, idx2 + 1);
                        break;
                }
            }
        }
    }

    static void Main()
    {
        n = int.Parse(Console.ReadLine());
        team1 = new int[n / 2];
        team2 = new int[n / 2];
        abillity = new int[n, n];

        for (int i = 0; i < n; i++)
        {
            int[] input = Console.ReadLine().Split().Select(int.Parse).ToArray();

            for (int j = 0; j < n; j++)
                abillity[i, j] = input[j];
        }

        Dfs(0, 0);
        Console.Write(min);
    }
}