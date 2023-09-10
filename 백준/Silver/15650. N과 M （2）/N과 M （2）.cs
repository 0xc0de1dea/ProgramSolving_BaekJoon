using System;

class Program
{
    static System.Text.StringBuilder sb = new System.Text.StringBuilder();
    static int[] sequence;

    static void nCr(int cur, int range, int to)
    {
        if (cur > to)
        {
            for (int i = 1; i <= to; i++)
                sb.AppendFormat("{0} ", sequence[i]);

            sb.AppendLine();
            return;
        }

        for (int i = 1; i <= range; i++)
        {
            if (sequence[cur - 1] < i)
            {
                sequence[cur] = i;
                nCr(cur + 1, range, to);
            }
        }
    }

    static void Main()
    {
        string[] input = Console.ReadLine().Split();
        int n = int.Parse(input[0]);
        int m = int.Parse(input[1]);

        sequence = new int[m + 1];

        nCr(1, n, m);
        Console.Write(sb);
    }
}