using System;
using System.Linq;

class Program
{
    static void Main()
    {
        while (true)
        {
            int[] input = Console.ReadLine().Split().Select(int.Parse).ToArray();

            if (input[0] == 0 && input[1] == 0)
                break;

            Console.WriteLine(input[0] + input[1]);
        }
    }
}