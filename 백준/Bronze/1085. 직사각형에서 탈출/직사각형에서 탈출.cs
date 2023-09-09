using System;

class Program
{
    static void Main()
    {
        string[] input = Console.ReadLine().Split();

        int x = int.Parse(input[0]);
        int y = int.Parse(input[1]);
        int w = int.Parse(input[2]);
        int h = int.Parse(input[3]);

        int minDist = int.MaxValue;

        if (minDist > x)
            minDist = x;

        if (minDist > y)
            minDist = y;

        if (minDist > w - x)
            minDist = w - x;

        if (minDist > h - y)
            minDist = h - y;

        Console.Write(minDist);
    }
}