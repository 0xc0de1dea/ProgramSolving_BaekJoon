using System;

class Program
{
    static int Factorial(int n)
    {
        if (n < 2)
            return 1;

        return n * Factorial(n - 1);
    }

    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int factorial = Factorial(n);
        Console.Write(factorial);
    }
}