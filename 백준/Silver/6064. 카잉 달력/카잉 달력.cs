using System;
using System.Collections.Generic;

class Program
{
    class CalenderData
    {
        public int x, y, k;

        public CalenderData(int x, int y, int k)
        {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    static List<CalenderData> cd = new List<CalenderData>();

    static int GetYear(int m, int n, int x, int y)
    {
        int correction;
        int findX, findY;

        if (m <= n)
        {
            correction = n - y;
            findX = x + correction;
            findY = y + correction;

            while (findX > m)
                findX -= m;
        }
        else
        {
            correction = m - x;
            findX = x + correction;
            findY = y + correction;

            while (findY > n)
                findY -= n;
        }

        foreach (var e in cd)
        {
            if (e.x == findX && e.y == findY)
                return e.k - correction;
        }

        return -1;
    }

    static void InputData(int m, int n)
    {
        cd.Clear();

        int target = 0;
        int target_Max, non_Target_Max;
        int cnt = 0;

        if (m <= n)
        {
            target_Max = m;
            non_Target_Max = n;
        }
        else
        {
            target_Max = n;
            non_Target_Max = m;
        }

        while (target != target_Max)
        {
            target += non_Target_Max;
            cnt += non_Target_Max;

            while (target > target_Max)
                target -= target_Max;

            if (target_Max == m)
                cd.Add(new CalenderData(target, non_Target_Max, cnt));
            else if (target_Max == n)
                cd.Add(new CalenderData(non_Target_Max, target, cnt));
        }
    }

    static void Main()
    {
        int t = int.Parse(Console.ReadLine());

        for (int i = 0; i < t; i++)
        {
            string[] testCase = Console.ReadLine().Split();

            int m = int.Parse(testCase[0]);
            int n = int.Parse(testCase[1]);
            int x = int.Parse(testCase[2]);
            int y = int.Parse(testCase[3]);

            InputData(m, n);

            int k = GetYear(m, n, x, y);
            Console.WriteLine(k);
        }
    }
}