using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int t = int.Parse(Console.ReadLine());
        int[] ab = new int[t];

        for (int i = 1; i <= t; i++)
        {
            ab = Console.ReadLine().Split().Select(e => int.Parse(e)).ToArray();
            Console.WriteLine("Case #{0}: {1} + {2} = {3}", i, ab[0], ab[1], ab[0] + ab[1]);
        }
    }
}