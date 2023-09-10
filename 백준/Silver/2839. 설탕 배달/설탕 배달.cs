using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        for (int i = n / 5 * 5; i >= 0; i -= 5)
        {
            if ((n - i) % 3 == 0)
            {
                Console.Write((n - i) / 3 + i / 5);
                return;
            }
        }

        Console.Write(-1);
    }
}