using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int t = int.Parse(Console.ReadLine());
        int sum;

        for (int i = 1; i <= t; i++)
        {
            sum = Console.ReadLine().Split().Select(e => int.Parse(e)).Sum();
            Console.WriteLine("Case #{0}: {1}", i, sum);
        }
    }
}