using System;
using System.Text;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        StringBuilder sb = new StringBuilder();

        for (; n > 0; n--)
            sb.Append(n + "\n");

        Console.Write(sb);
    }
}