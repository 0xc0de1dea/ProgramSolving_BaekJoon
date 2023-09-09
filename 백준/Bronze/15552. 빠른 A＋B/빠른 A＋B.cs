using System;
using System.Text;

class Program
{
    public static void Main(string[] args)
    {
        StringBuilder sb = new StringBuilder();

        int n = int.Parse(Console.ReadLine());

        for (int i =0; i < n; i++)
        {
            string[] str = Console.ReadLine().Split(' ');

            sb.Append(int.Parse(str[0]) + int.Parse(str[1]) + "\n");
        }

        Console.Write(sb);
    }
}