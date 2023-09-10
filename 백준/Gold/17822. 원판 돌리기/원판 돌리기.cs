using System;
using System.Linq;
using System.Collections.Generic;

class Program
{
    static int n, m, t;
    static List<List<int>> plate = new List<List<int>>();
    static List<List<int>> action = new List<List<int>>();
    static Queue<int[]> del = new Queue<int[]>();

    static void DataMod(bool check)
    {
        if (check)
            return;

        int sum = 0, cnt = 0;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (plate[i][j] > 0)
                    cnt++;

                sum += plate[i][j];
            }
        }

        if (cnt == 0)
            return;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (plate[i][j] > 0)
                {
                    if (plate[i][j] * cnt > sum)
                        plate[i][j]--;
                    else if (plate[i][j] * cnt < sum)
                        plate[i][j]++;
                }
            }
        }
    }

    static bool Remove()
    {
        bool isChange = false;

        while (del.Count > 0)
        {
            int[] delAxis = del.Dequeue();
            plate[delAxis[0]][delAxis[1]] = 0;
            isChange = true;
        }

        return isChange;
    }

    static void Find(int i, int j)
    {
        if (plate[i][j] == 0)
            return;

        int r = j + 1, u = i + 1;

        if (r < 0) r += m;
        r %= m;

        if (plate[i][j] == plate[i][r])
        {
            del.Enqueue(new int[] { i, j });
            del.Enqueue(new int[] { i, r });
        }

        if (i + 1 < n)
        {
            if (plate[i][j] == plate[u][j])
            {
                del.Enqueue(new int[] { i, j });
                del.Enqueue(new int[] { u, j });
            }
        }
    }

    static void Spin(int x, int inc, int d, int k)
    {
        if (x >= n)
            return;

        int tmp;

        for (int i = 0; i < k; i++)
        {
            if (d == 0)
            {
                tmp = plate[x][m - 1];
                plate[x].RemoveAt(m - 1);
                plate[x].Insert(0, tmp);
            }
            else
            {
                tmp = plate[x][0];
                plate[x].RemoveAt(0);
                plate[x].Add(tmp);
            }
        }

        Spin(x + inc, inc, d, k);
    }

    static int SpinPlate(int t)
    {
        int x, d, k;

        for (int i = 0; i < t; i++)
        {
            x = action[i][0];
            d = action[i][1];
            k = action[i][2];

            Spin(x - 1, x, d, k);

            for (int a = 0; a < n; a++)
                for (int b = 0; b < m; b++)
                    Find(a, b);

            DataMod(Remove());
        }

        int sum = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                sum += plate[i][j];

        return sum;
    }

    static void Main()
    {
        int[] arrayTmp = new int[3];
        arrayTmp = Console.ReadLine().Split().Select(e => int.Parse(e)).ToArray();

        n = arrayTmp[0];
        m = arrayTmp[1];
        t = arrayTmp[2];

        List<int> listTmp = new List<int>();

        for (int i = 0; i < n; i++)
        {
            listTmp = Console.ReadLine().Split().Select(e => int.Parse(e)).ToList();
            plate.Add(new List<int>(listTmp));
        }

        for (int i = 0; i < t; i++)
        {
            listTmp = Console.ReadLine().Split().Select(e => int.Parse(e)).ToList();
            action.Add(new List<int>(listTmp));
        }

        Console.Write(SpinPlate(t));
    }
}