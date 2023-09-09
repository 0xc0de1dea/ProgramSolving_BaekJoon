using System;

class Program
{
    static long BreakEvenPoint(int fixedCost, int variableCost, int createCost)
    {
        if (variableCost >= createCost)
        {
            return -1;
        }

        long searchStart = 10;
        long searchEnd = int.MaxValue;
        long searchInc = searchStart;

        for (long i = searchStart; i <= searchEnd; i *= searchInc)
        {
            if (fixedCost + variableCost * i < createCost * i)
            {
                searchStart = i / 10;
                searchEnd = i;
                searchInc = searchStart;

                break;
            }
            else
            {
                searchStart = 1000000000;
                searchEnd = 3000000000;
                searchInc = searchStart;
            }
        }

        while (searchInc > 0)
        {
            for (long i = searchStart; i <= searchEnd; i += searchInc)
            {
                if (fixedCost + variableCost * i < createCost * i)
                {
                    searchStart = i - searchInc;
                    searchEnd = i;
                    searchInc /= 10;

                    break;
                }
            }
        }

        return searchEnd;
    }

    static void Main()
    {
        string[] cost = Console.ReadLine().Split();

        int fixedCost = int.Parse(cost[0]);
        int variableCost = int.Parse(cost[1]);
        int createCost = int.Parse(cost[2]);

        long result = BreakEvenPoint(fixedCost, variableCost, createCost); // 10진(?) 탐색
        Console.Write(result);
    }
}