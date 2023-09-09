using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        string num = Console.ReadLine();
        int sum = 0;

        for (int i = num.Length - 1; i >= 0; i--)
            sum += int.Parse(num[i].ToString());

        Console.Write(sum);
    }
}