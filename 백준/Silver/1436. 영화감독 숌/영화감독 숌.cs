using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        for (int i = 666; ; i++)
        {
            int target = i;
            int combo = 0;

            while (target > 0 && combo < 3)
            {
                if (target % 10 == 6)
                    combo++;
                else
                    combo = 0;

                target /= 10;
            }

            if (combo >= 3)
                n--;

            if (n == 0)
            {
                Console.WriteLine(i);
                break;
            }
        }
    }
}