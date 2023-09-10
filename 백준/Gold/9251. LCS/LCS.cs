using System;

class Program
{
    static void Main()
    {
        string string1 = Console.ReadLine();
        string string2 = Console.ReadLine();
        int len1 = string1.Length;
        int len2 = string2.Length;
        int[,] dp = new int[len1 + 1, len2 + 1];
        
        for (int i = 1; i <= len1; i++)
        {
            for (int j = 1; j <= len2; j++)
            {
                if (string1[i - 1] == string2[j - 1])
                    dp[i, j] = dp[i - 1, j - 1] + 1;
                else
                    dp[i, j] = Math.Max(dp[i - 1, j], dp[i, j - 1]);
            }
        }

        Console.Write(dp[len1, len2]);
    }
}