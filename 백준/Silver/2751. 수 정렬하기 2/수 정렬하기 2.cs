using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        bool[] check = new bool[2000001];

        for (int i = 0; i < n; i++)
            check[int.Parse(Console.ReadLine()) + 1000000] = true;

        System.Text.StringBuilder sb = new System.Text.StringBuilder();
        int length = check.Length;

        for (int i = 0; i < length; i++)
            if (check[i])
                sb.Append(i - 1000000 + "\n");

        Console.Write(sb);
    }
}