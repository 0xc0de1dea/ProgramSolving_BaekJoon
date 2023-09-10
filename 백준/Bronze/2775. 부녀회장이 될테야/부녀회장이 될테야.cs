using System;

class Program
{
    static int[,] date = new int[15, 15];

    static int ApartResident(int k, int n)
    {
        if (date[k, n] != 0)
            return date[k, n];

        int sum = 0;

        for (int i = 1; i <= n; i++)
            sum += ApartResident(k - 1, i);

        date[k, n] = sum;

        return sum;
    }

    static void Main()
    {
        int t = int.Parse(Console.ReadLine());

        for (int i = 1; i <= 14; i++)
            date[0, i] = i;

        for (int i = 0; i < t; i++)
        {
            int k = int.Parse(Console.ReadLine());
            int n = int.Parse(Console.ReadLine());
            int cnt = ApartResident(k, n);

            Console.WriteLine(cnt);
        }
    }
}