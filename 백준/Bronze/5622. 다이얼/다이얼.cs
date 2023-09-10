using System;
using System.Linq;

class Program
{
    static int AlphaToNum(char c)
    {
        int tmp = c - 64;

        if (tmp <= 3)
            return 2;
        else if (tmp <= 6)
            return 3;
        else if (tmp <= 9)
            return 4;
        else if (tmp <= 12)
            return 5;
        else if (tmp <= 15)
            return 6;
        else if (tmp <= 19)
            return 7;
        else if (tmp <= 22)
            return 8;
        else
            return 9;
    }

    static void Main()
    {
        string dial = Console.ReadLine();
        int sum = 0;

        for (int i = 0; i < dial.Length; i++)
            sum += AlphaToNum(dial[i]) + 1;

        Console.Write(sum);
    }
}