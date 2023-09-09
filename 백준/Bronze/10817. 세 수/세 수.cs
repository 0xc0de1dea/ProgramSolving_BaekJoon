using System;
using System.Linq;

class Program
{
    static void Main()
    {
        byte[] abc = Console.ReadLine().Split().Select((e) => byte.Parse(e)).ToArray();
        byte temp;

        if (abc[0] > abc[1])
        {
            temp = abc[0];
            abc[0] = abc[1];
            abc[1] = temp;
        }

        if (abc[1] > abc[2])
        {
            temp = abc[1];
            abc[1] = abc[2];
            abc[2] = temp;
        }

        if (abc[0] >= abc[1])
            Console.Write(abc[0]);
        else
            Console.Write(abc[1]);
    }
}