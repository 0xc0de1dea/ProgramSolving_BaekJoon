using System;

class Program
{
    static void Main()
    {
        int[] m = new int[6];
        m[0] = short.Parse(Console.ReadLine());
        m[1] = short.Parse(Console.ReadLine());
        m[2] = m[0] * (m[1] % 10);
        m[3] = m[0] * (m[1] / 10 % 10);
        m[4] = m[0] * (m[1] / 100 % 10);
        m[5] = m[0] * m[1];

        for (int i = 2; i <= 5; i++)
            Console.WriteLine(m[i]);
        Console.ReadLine();
    }
}