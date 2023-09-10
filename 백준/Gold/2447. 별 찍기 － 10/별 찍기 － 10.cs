using System;

class Program
{
    static char[,] data;

    static void Fractal(int n, int xAxis, int yAxis, char c)
    {
        if (n == 1)
        {
            data[xAxis, yAxis] = c;
            return;
        }

        for (int i = 0; i <= 2; i++)
        {
            for (int j = 0; j <= 2; j++)
            {
                int new_N = n / 3;
                char new_C;

                if ((i == 1 && j == 1) || c == ' ')
                    new_C = ' ';
                else
                    new_C = '*';

                Fractal(new_N, xAxis + new_N * i, yAxis + new_N * j, new_C);
            }
        }
    }

    static void Main()
    {
        System.Text.StringBuilder sb = new System.Text.StringBuilder();

        int n = int.Parse(Console.ReadLine());
        data = new char[n + 1, n + 1];

        Fractal(n, 1, 1, '*');
        
        for (int i = 1; i < n + 1; i++)
        {
            for (int j = 1; j < n + 1; j++)
            {
                sb.Append(data[i, j]);
            }

            sb.AppendLine();
        }

        Console.Write(sb);
    }
}