using System;

class Program
{
    static void Main()
    {
        string[] input = Console.ReadLine().Split();
        int n = int.Parse(input[0]);
        int k = int.Parse(input[1]);
        int[] coin = new int[n];
        int cnt = 0;

        for (int i = 0; i < n; i++)
            coin[i] = int.Parse(Console.ReadLine());

        Array.Sort(coin, (a, b) => { return b - a; });
        int pnt = 0;

        while (k > 0)
        {
            while (k - coin[pnt] >= 0)
            {
                k -= coin[pnt];
                cnt++;
            }

            pnt++;
        }

        Console.Write(cnt);
    }
}