using System;
using System.Linq;

class TupleTest
{
    static void Main()
    {
        var tuple = Divide();
        Console.WriteLine(tuple.Item1 / (double)tuple.Item2);
    }

    static Tuple<byte, byte> Divide()
    {
        byte[] numbers = Console.ReadLine().Split(' ').Select((e) => byte.Parse(e)).ToArray();

        return new Tuple<byte, byte>(numbers[0], numbers[1]);
    }
}