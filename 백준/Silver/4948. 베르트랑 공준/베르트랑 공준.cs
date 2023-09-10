using System;

class Program
{
    static void PrimeNumbers(uint from, uint to)
    {
        bool[] check = new bool[to + 1];
        check[0] = true;
        check[1] = true;

        int cnt = 0;

        int searchMax = (int)Math.Sqrt(to);

        for (int i = 2; i <= searchMax; i++)
            if (!check[i])
                for (int j = i + i; j <= to; j += i)
                    check[j] = true;

        for (uint i = from; i <= to; i++)
            if (!check[i])
                cnt++;

        Console.WriteLine(cnt);
    }

    static void Main()
    {
        while (true)
        {
            uint from = uint.Parse(Console.ReadLine());
            uint to = from * 2;

            if (from != 0)
                PrimeNumbers(from + 1, to);
            else
                break;
        }
    }
}