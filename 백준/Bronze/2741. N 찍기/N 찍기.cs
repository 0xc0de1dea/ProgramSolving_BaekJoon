using System;
using System.Text;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++)
            sb.Append(i + "\n");

        Console.Write(sb);
    }
}