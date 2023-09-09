using System;
using System.Linq;

class Program
{
    static void Main()
    {
        sbyte[] ab = Console.ReadLine().Split().Select((e) => sbyte.Parse(e)).ToArray();

        if (ab[1] < 45)
        {
            ab[0]--;
            ab[1] += 15;
        }
        else
            ab[1] -= 45;

        if (ab[0] < 0)
            ab[0] += 24;

        Console.Write(ab[0] + " " + ab[1]);
    }
}