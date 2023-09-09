using System;

class Program
{
    static void Main()
    {
        while (true)
        {
            string[] input = Console.ReadLine().Split();

            int a = int.Parse(input[0]);
            int b = int.Parse(input[1]);
            int c = int.Parse(input[2]);

            if (a == 0 && b == 0 && c == 0)
                break;

            if (a * a + b * b == c * c)
            {
                Console.WriteLine("right");
                continue;
            }

            if (a * a + c * c == b * b)
            {
                Console.WriteLine("right");
                continue;
            }

            if (b * b + c * c == a * a)
            {
                Console.WriteLine("right");
                continue;
            }

            Console.WriteLine("wrong");
        }
    }
}