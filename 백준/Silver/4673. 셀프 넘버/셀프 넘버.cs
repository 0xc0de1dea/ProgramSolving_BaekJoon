using System;

class Program
{
    static int FindCtr(int ori_Num)
    {
        int new_Num = ori_Num;

        while (true)
        {
            if (ori_Num == 0)
                break;

            new_Num += ori_Num % 10;
            ori_Num /= 10;
        }

        return new_Num;
    }

    static void Main()
    {
        bool[] check = new bool[10001];
        System.Text.StringBuilder sb = new System.Text.StringBuilder();

        for (int i = 1; i <= 10000; i++)
        {
            int new_Num = FindCtr(i);

            if (new_Num <= 10000)
                check[new_Num] = true;
        }

        for (int i = 1; i <= 10000; i++)
            if (!check[i])
                sb.Append(i + "\n");

        Console.Write(sb);
    }
}