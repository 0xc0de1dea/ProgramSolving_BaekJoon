using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int[] input = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int[] arr = Console.ReadLine().Split().Select(int.Parse).ToArray();

        for (int i = 0; i < arr.Length; i++)
        {
            if (arr[i] < input[1])
                Console.Write("{0} ", arr[i]);
        }
    }
}