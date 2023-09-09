using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int a = int.Parse(Console.ReadLine());
        int b = int.Parse(Console.ReadLine());
        int c = int.Parse(Console.ReadLine());
        int[] cnt = new int[10];

        string result = (a * b * c).ToString();

        for (int i = result.Length - 1; i >= 0; i--)
            cnt[int.Parse(result[i].ToString())]++;

        for (int i = 0; i < 10; i++)
            Console.WriteLine(cnt[i]);
    }
}