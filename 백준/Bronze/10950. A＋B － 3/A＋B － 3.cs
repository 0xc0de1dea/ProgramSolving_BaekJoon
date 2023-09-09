using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int t = int.Parse(Console.ReadLine());
        int[] ab = new int[t];
        
        for (int i = 0; i < t; i++)
        {
            ab = Console.ReadLine().Split().Select(e => int.Parse(e)).ToArray();
            Console.WriteLine(ab[0] + ab[1]);
        }
    }
}