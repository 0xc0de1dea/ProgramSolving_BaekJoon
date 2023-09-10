using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[] sbj = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int max = 0;
        double sum = 0;

        for (int i = 0; i < sbj.Length; i++)
            if (max < sbj[i])
                max = sbj[i];

        for (int i = 0; i < sbj.Length; i++)
            sum += (double)sbj[i] / max * 100;

        Console.Write(sum / sbj.Length);
    }
}