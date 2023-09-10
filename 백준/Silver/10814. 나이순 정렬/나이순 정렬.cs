using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        List<Tuple<int, int, string>> customers = new List<Tuple<int, int, string>>(); // (No, Age, Name)
        string[] input;

        for (int i = 0; i < n; i++)
        {
            input = Console.ReadLine().Split();
            customers.Add(new Tuple<int, int, string>(i, int.Parse(input[0]), input[1])); 
        }

        customers.Sort((a, b) => a.Item2 == b.Item2 ? a.Item1 - b.Item1 : a.Item2 - b.Item2);
        
        System.Text.StringBuilder sb = new System.Text.StringBuilder();

        foreach (var e in customers)
        {
            sb.AppendFormat("{0} {1}", e.Item2, e.Item3).AppendLine();
        }

        Console.Write(sb);
    }
}