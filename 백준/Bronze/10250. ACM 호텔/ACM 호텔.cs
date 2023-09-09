using System;

class Program
{
    static void Main()
    {
        int t = int.Parse(Console.ReadLine());

        for (int i = 0; i < t; i++)
        {
            string[] testCase = Console.ReadLine().Split();
            int h = int.Parse(testCase[0]);
            int w = int.Parse(testCase[1]);
            int n = int.Parse(testCase[2]);
            int x = 1, y = 1;

            while (true)
            {
                if (n > h)
                {
                    n -= h;
                    y++;
                }
                else
                {
                    x = n;
                    break;
                }
            }

            Console.WriteLine("{0}{1:00}", x, y);
        }
    }
}