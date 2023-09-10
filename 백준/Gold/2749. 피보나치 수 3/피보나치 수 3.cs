using System;

class Program
{
    static int GetPisanoPeriod(int mod)
    {
        int cur = 1, prev = 0;
        int period = 0;

        do
        {
            int tmp = prev;
            prev = cur;
            cur = (cur + tmp) % mod;

            period++;
        }
        while (!(cur == 1 && prev == 0));

        return period;
    }

    static void Main()
    {
        long n = long.Parse(Console.ReadLine());
        int mod = 1000000;
        int period = GetPisanoPeriod(mod);

        int[] periodList = new int[period + 1];
        periodList[0] = 0;
        periodList[1] = 1;

        for (int i = 2; i < periodList.Length; i++)
            periodList[i] = (periodList[i - 1] + periodList[i - 2]) % mod;

        Console.Write(periodList[n % period]);
    }
}