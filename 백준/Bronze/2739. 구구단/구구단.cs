using System;

class Program
{
    static void Main()
    {
        byte num = byte.Parse(Console.ReadLine());

        for (byte i = 1; i <= 9; i++)
            Console.WriteLine("{0} * {1} = {2}", num, i, num * i);
    }
}