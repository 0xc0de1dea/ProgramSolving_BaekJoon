using System;

class Program
{
    static System.Text.StringBuilder sb = new System.Text.StringBuilder();

    static void Hanoi(int n, int a, int b, int c)
    {
        if (n == 1)
        {
            sb.AppendFormat("{0} {1}\n", a, c);
            return;
        }

        Hanoi(n - 1, a, c, b);
        Hanoi(1, a, b, c);
        Hanoi(n - 1, b, a, c);
    }

    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        Console.WriteLine((int)Math.Pow(2, n) - 1);
        Hanoi(n, 1, 2, 3);
        Console.Write(sb);
    }
}