using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int[] n = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int a = n[0] % 10 * 100 + n[0] / 10 % 10 * 10 + n[0] / 100 % 10;
        int b = n[1] % 10 * 100 + n[1] / 10 % 10 * 10 + n[1] / 100 % 10;

        if (a > b)
            Console.Write(a);
        else
            Console.Write(b);
    }
}