using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[] counting = new int[8001];

        int mean = 0;
        double median = 0;
        int mode = 0;
        List<int> modeList = new List<int>();
        int range = 0;

        double sum = 0;
        int half = n / 2 + 1;
        int min = 4000;
        int max = -4000;

        for (int i = 0; i < n; i++)
        {
            int input = int.Parse(Console.ReadLine());
            counting[input + 4000]++;
            sum += input;

            if (min > input)
                min = input;

            if (max < input)
                max = input;
        }

        for (int i = 0; i < 8001; i++)
        {
            if (mode < counting[i])
                mode = counting[i];

            if (half > 0)
            {
                half -= counting[i];
                median = i - 4000;
            }
        }

        for (int i = 0; i < 8001; i++)
            if (mode == counting[i])
                modeList.Add(i - 4000);

        modeList.Sort();

        mean = (int)Math.Round(sum / n, MidpointRounding.AwayFromZero);
        mode = modeList.Count == 1 ? modeList[0] : modeList[1];
        range = max - min;

        Console.WriteLine(mean);
        Console.WriteLine(median);
        Console.WriteLine(mode);
        Console.WriteLine(range);
    }
}