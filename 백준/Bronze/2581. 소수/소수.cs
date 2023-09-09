using System;
using System.Linq;

class Program
{
    static Tuple<uint, uint> PrimeNumbers(uint from, uint to)
    {
        bool[] check = new bool[to + 1];
        check[0] = true;
        check[1] = true;

        uint sum = 0;
        uint min = int.MaxValue;

        int searchMax = (int)Math.Sqrt(to);

        for (int i = 2; i <= searchMax; i++)
            if (!check[i])
                for (int j = i + i; j <= to; j += i)
                    check[j] = true;

        for (uint i = from; i <= to; i++)
        {
            if (!check[i])
            {
                sum += i;

                if (min == int.MaxValue)
                    min = i;
            }
        }

        return new Tuple<uint, uint>(sum, min);
    }

    static void Main()
    {
        uint from = uint.Parse(Console.ReadLine());
        uint to = uint.Parse(Console.ReadLine());

        Tuple<uint, uint> result = PrimeNumbers(from, to);

        if (result.Item1 != 0)
        {
            Console.WriteLine(result.Item1);
            Console.WriteLine(result.Item2);
        }
        else
            Console.WriteLine(-1);
    }
}