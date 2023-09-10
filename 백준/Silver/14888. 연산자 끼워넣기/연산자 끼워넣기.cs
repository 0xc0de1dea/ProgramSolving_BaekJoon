using System;
using System.Linq;

class Program
{
    static int n;
    static int[] numbers;
    static int[] operators;
    static int result = 0, max = -int.MaxValue, min = int.MaxValue;

    static void Dfs(int idx)
    {
        if (idx == n)
        {
            if (max < result)
                max = result;

            if (min > result)
                min = result;

            return;
        }

        for (int i = 0; i < 4; i++)
        {
            if (operators[i] > 0)
            {
                int save = result;

                switch (i)
                {
                    case 0:
                        result += numbers[idx];
                        break;

                    case 1:
                        result -= numbers[idx];
                        break;

                    case 2:
                        result *= numbers[idx];
                        break;

                    case 3:
                        result /= numbers[idx];
                        break;
                }

                operators[i]--;
                Dfs(idx + 1);
                operators[i]++;
                result = save;
            }
        }
    }

    static void Main()
    {
        n = int.Parse(Console.ReadLine());
        numbers = Console.ReadLine().Split().Select(int.Parse).ToArray();
        operators = Console.ReadLine().Split().Select(int.Parse).ToArray();
        result = numbers[0];

        Dfs(1);

        Console.WriteLine(max);
        Console.WriteLine(min);
    }
}