using System;

class Program
{
    static void Main()
    {
        string n = Console.ReadLine();
        int[] data = new int[n.Length];

        for (int i = n.Length - 1; i >= 0; i--)
            data[i] = int.Parse(n[i].ToString());

        Array.Sort(data, delegate (int A, int B)
        {
            if (A > B) return -1;
            else if (A < B) return 1;
            return 0;
        });

        foreach (var e in data)
            Console.Write(e);
    }
}