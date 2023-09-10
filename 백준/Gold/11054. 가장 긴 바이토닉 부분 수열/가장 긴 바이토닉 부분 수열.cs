using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[] arr = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int[] dp_Left = new int[n];
        int[] dp_Right = new int[n];
        dp_Left[0] = dp_Right[n - 1] = 1;
        int max = 0;

        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j])
                    if (max < dp_Left[j])
                        max = dp_Left[j];

            dp_Left[i] = max + 1;
            max = 0;
        }

        for (int i = n - 2; i >= 0; i--)
        {
            for (int j = n - 1; j > i; j--)
                if (arr[i] > arr[j])
                    if (max < dp_Right[j])
                        max = dp_Right[j];

            dp_Right[i] = max + 1;
            max = 0;
        }

        for (int i = 0; i < n; i++)
            if (max < dp_Left[i] + dp_Right[i])
                max = dp_Left[i] + dp_Right[i];

        Console.Write(max - 1);
    }
}