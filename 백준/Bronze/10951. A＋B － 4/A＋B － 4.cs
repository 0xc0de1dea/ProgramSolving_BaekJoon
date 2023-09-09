using System;

class Program
{
    static void Main()
    {
        while (true)
        {
            string input = Console.ReadLine();

            if (input == null || input == "")
                break;

            int a = int.Parse(input[0].ToString());
            int b = int.Parse(input[2].ToString());

            Console.WriteLine(a + b);
        }
    }
}