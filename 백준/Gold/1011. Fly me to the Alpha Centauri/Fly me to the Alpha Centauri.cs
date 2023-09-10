using System;

class Program
{
    static int PyramidSequence(int x, int y)
    {
        int dist = y - x;
        long sum = 1, cnt = 1, top = 1;

        while (true)
        {
            if (sum - dist > 0)
            {
                top--;
                sum = top * top;
                cnt = 2 * top - 1;

                break;
            }
            else if (sum - dist == 0)
                break;

            top++;
            sum = top * top;
            cnt = 2 * top - 1;
        }

        while (true)
        {
            if (sum - dist == 0)
                break;

            if (sum + top > dist)
            {
                top--;
            }
            else if (sum + top <= dist)
            {
                sum += top;
                cnt++;
            }
        }

        return (int)cnt;
    }

    static void Main()
    {
        int t = int.Parse(Console.ReadLine());

        for (int i = 0; i < t; i++)
        {
            string[] testCase = Console.ReadLine().Split();
            int x = int.Parse(testCase[0]);
            int y = int.Parse(testCase[1]);

            Console.WriteLine(PyramidSequence(x, y));
        }
    }
}