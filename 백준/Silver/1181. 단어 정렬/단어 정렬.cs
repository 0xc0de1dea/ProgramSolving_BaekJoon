using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        string[] words = new string[n];

        for (int i = 0; i < n; i++)
            words[i] = Console.ReadLine();

        Array.Sort(words, (a, b) =>
        {
            int a_Length = a.Length;
            int b_Length = b.Length;
            int pnt = 0;

            if (a_Length != b_Length)
                return a_Length - b_Length;
            else
            {
                while (a[pnt] == b[pnt] && pnt < a_Length - 1)
                    pnt++;
                
                return a[pnt] - b[pnt];
            }
        });
        
        System.Text.StringBuilder sb = new System.Text.StringBuilder();
        string prev = "";

        foreach (var e in words)
        {
            if (prev != e)
            {
                sb.AppendLine(e);

                prev = e;
            }
        }

        Console.Write(sb);
    }
}