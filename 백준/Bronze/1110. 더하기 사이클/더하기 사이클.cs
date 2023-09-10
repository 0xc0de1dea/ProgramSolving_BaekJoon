using System;

class Program
{
    static void Main()
    {
        int ori_N = int.Parse(Console.ReadLine());
        int new_N = ori_N;
        int count = 0;

        do
        {
            new_N = new_N % 10 * 10 + (new_N / 10 + new_N % 10) % 10;
            count++;
        }
        while (ori_N != new_N);

        Console.Write(count);
    }
}