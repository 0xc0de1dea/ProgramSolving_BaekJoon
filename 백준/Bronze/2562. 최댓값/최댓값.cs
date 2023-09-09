using System;

class Program
{
    static void Main()
    {
        int num, max = 0, idx = 0;

        for (int i = 1; i <= 9; i++)
        {
            num = int.Parse(Console.ReadLine());

            if (num > max)
            {
                max = num;
                idx = i;
            }
        }

        Console.WriteLine(max);
        Console.Write(idx);
    }
}