using System;

class Program
{
    static int n, cnt;
    static bool[] col, digonal1, digonal2;

    static void N_Queen(int row)
    {
        if (row > n)
        {
            cnt++;
            return;
        }

        for (int i = 1; i <= n; i++)
        {
            if (col[i])
                continue;

            if (digonal1[row + i] || digonal2[n + row - i])
                continue;

            col[i] = digonal1[row + i] = digonal2[n + row - i] = true;
            N_Queen(row + 1);
            col[i] = digonal1[row + i] = digonal2[n + row - i] = false;
        }
    }

    static void Main()
    {
        n = int.Parse(Console.ReadLine());
        cnt = 0;
        col = new bool[n + 1];
        digonal1 = new bool[(n + 1) * 2];
        digonal2 = new bool[(n + 1) * 2];

        N_Queen(1);
        Console.Write(cnt);
    }
}