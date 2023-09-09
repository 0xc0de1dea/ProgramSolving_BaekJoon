using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[] arr = Console.ReadLine().Split().Select((e) => int.Parse(e)).ToArray();
        int min = int.MaxValue, max = arr[0];

        for (int i = 0; i < n; i++)
        {
            if (arr[i] < min)
                min = arr[i];

            if (arr[i] > max)
                max = arr[i];
        }

        Console.Write(min + " " + max);
    }
}