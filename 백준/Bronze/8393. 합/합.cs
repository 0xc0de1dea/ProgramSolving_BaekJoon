using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int sum = 0;
        
        while (n > 0)
        {
            sum += n;
            n--;
        }

        Console.Write(sum);
    }
}