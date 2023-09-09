using System;

class Program
{
    static void Main()
    {
        short leapYear = short.Parse(Console.ReadLine());

        if (leapYear % 4 == 0 && (leapYear % 100 != 0 || leapYear % 400 == 0))
            Console.Write("1");
        else
            Console.Write("0");
    }
}