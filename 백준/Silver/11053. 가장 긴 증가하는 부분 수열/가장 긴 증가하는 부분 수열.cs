using System;
using System.Linq;

class Program
{
    static int LowerBound(int[] arr, int start, int end, int target)
    {
        int mid;

        while (start != end)
        {
            mid = (end + start) / 2;

            if (arr[mid] >= target)
                end = mid;
            else
                start = mid + 1;
        }

        return end;
    }

    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[] arr = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int[] lis = new int[n + 1];
        int len = 0;

        for (int i = 0; i < n; i++)
        {
            if (arr[i] > lis[len])
                lis[++len] = arr[i];
            else
                lis[LowerBound(lis, 1, len, arr[i])] = arr[i];
        }

        Console.Write(len);
    }
}