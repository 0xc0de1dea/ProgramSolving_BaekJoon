using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        for (int i = 1; i <= n; i++)
        {
            int d_Sum = i;
            int tmp = i;

            while (tmp > 0)
            {
                d_Sum += tmp % 10;
                tmp /= 10;
            }

            if (d_Sum == n)
            {
                Console.Write(i);
                return;
            }
        }

        Console.Write(0);
    }
}