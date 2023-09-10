using System;

class Program
{
    static void Main()
    {
        string num = Console.ReadLine();
        int[] idx = new int[26];

        for (int i = 0; i < 26; i++)
            idx[i] = -1;

        for (int i = num.Length - 1; i >= 0; i--)
            idx[Convert.ToInt32(num[i]) - 97] = i;

        for (int i = 0; i < 26; i++)
            Console.Write("{0} ", idx[i]);
    }
}