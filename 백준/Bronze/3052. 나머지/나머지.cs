using System;

class Program
{
    static void Main()
    {
        int[] check = new int[10] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int pnt = 0, cnt = 0;

        for (int i = 0; i < 10; i++)
        {
            int input = int.Parse(Console.ReadLine());
            bool isEqual = false;

            for (int j = 0; j <= pnt; j++)
            {
                if (input % 42 == check[j])
                {
                    isEqual = true;
                    break;
                }
            }

            if (!isEqual)
            {
                check[pnt] = input % 42;
                pnt++;
                cnt++;
            }
        }

        Console.Write(cnt);
    }
}