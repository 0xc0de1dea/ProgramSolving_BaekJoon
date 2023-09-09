using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int[] ab = Console.ReadLine().Split().Select((e) => int.Parse(e)).ToArray();

        if (ab[0] > ab[1])
            Console.Write(">");
        else if (ab[0] == ab[1])
            Console.Write("==");
        else
            Console.Write("<");
    }
}