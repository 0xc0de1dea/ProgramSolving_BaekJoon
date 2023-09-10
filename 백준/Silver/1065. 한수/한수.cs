using System;

class Program
{
    static bool HanNum(int num)
    {
        int prevSub = num / 10 % 10 - num % 10;

        while (num >= 100)
        {
            num /= 10;
            int curSub = num / 10 % 10 - num % 10;

            if (curSub != prevSub)
                return false;
        }

        return true;
    }

    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int cnt = 0;

        for (int i = 1; i <= n; i++)
        {
            if (i < 100)
            {
                cnt++;
            }
            else
            {
                bool isHanNum = HanNum(i);

                if (isHanNum) cnt++;
            }
        }

        Console.Write(cnt);
    }
}