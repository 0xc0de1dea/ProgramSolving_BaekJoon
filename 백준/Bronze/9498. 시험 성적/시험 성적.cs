using System;

class Program
{
    static void Main()
    {
        byte n = (byte)(byte.Parse(Console.ReadLine()) / 10);

        switch (n)
        {
            case 10:
                Console.Write("A");
                break;

            case 9:
                Console.Write("A");
                break;

            case 8:
                Console.Write("B");
                break;

            case 7:
                Console.Write("C");
                break;

            case 6:
                Console.Write("D");
                break;

            default:
                Console.Write("F");
                break;
        }
    }
}