using System;

class Program
{
    static void Main()
    {
        int t = int.Parse(Console.ReadLine());

        for (int i = 0; i < t; i++)
        {
            string[] input = Console.ReadLine().Split();

            int x1 = int.Parse(input[0]);
            int y1 = int.Parse(input[1]);
            int r1 = int.Parse(input[2]);
            int x2 = int.Parse(input[3]);
            int y2 = int.Parse(input[4]);
            int r2 = int.Parse(input[5]);

            double midPointDist = Math.Sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

            if (midPointDist > r1 + r2 || (midPointDist < Math.Abs(r1 - r2) && r1 != r2)) Console.WriteLine(0);
            else if (midPointDist == r1 + r2 || midPointDist == Math.Abs(r1 - r2) && r1 != r2) Console.WriteLine(1);
            else if (midPointDist < r1 + r2 && midPointDist > Math.Abs(r1 - r2)) Console.WriteLine(2);
            else Console.WriteLine(-1);
        }
    }
}