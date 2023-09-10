using System;

class Program
{
    static void Main()
    {
        byte n = byte.Parse(Console.ReadLine());
        byte[,] info = new byte[n, 3];

        for (int i = 0; i < n; i++)
        {
            string[] input = Console.ReadLine().Split();
            info[i, 0] = byte.Parse(input[0]);
            info[i, 1] = byte.Parse(input[1]);
        }
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == j)
                    continue;

                if (info[i, 0] < info[j, 0] && info[i, 1] < info[j, 1])
                    info[i, 2]++;
            }
        }

        for (int i = 0; i < n; i++)
            Console.Write("{0} ", info[i, 2] + 1);
    }
}