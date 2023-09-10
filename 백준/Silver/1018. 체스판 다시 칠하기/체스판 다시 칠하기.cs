using System;

class Program
{
    static void Main()
    {
        string[] scale = Console.ReadLine().Split();

        byte n = byte.Parse(scale[0]);
        byte m = byte.Parse(scale[1]);
        string[] board = new string[n];

        for (int i = 0; i < n; i++)
            board[i] = Console.ReadLine();

        char[] cur = new char[] { ' ', ' ' };
        int[] cnt = new int[] { 0, 0 };
        int min = int.MaxValue;

        for (byte row = 0; row <= n - 8; row++)
        {
            for (byte col = 0; col <= m - 8; col++)
            {
                for (byte i = row; i < row + 8; i++)
                {
                    if (i % 2 == 0)
                    {
                        cur[0] = 'W';
                        cur[1] = 'B';
                    }
                    else
                    {
                        cur[0] = 'B';
                        cur[1] = 'W';
                    }

                    for (byte j = col; j < col + 8; j++)
                    {
                        if (board[i][j] != cur[0])
                            cnt[0]++;

                        if (board[i][j] != cur[1])
                            cnt[1]++;

                        char tmp = cur[0];
                        cur[0] = cur[1];
                        cur[1] = tmp;
                    }
                }

                int minPartition = cnt[0] > cnt[1] ? cnt[1] : cnt[0];
                cnt[0] = 0;
                cnt[1] = 0;

                if (min > minPartition)
                    min = minPartition;
            }
        }

        Console.Write(min);
    }
}