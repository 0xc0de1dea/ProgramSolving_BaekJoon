using System;

class Program
{
    static void Merge(int[,] axis, int[,] copy, int start, int mid, int end)
    {
        int pnt = start;
        int left = start;
        int right = mid + 1;

        while (left <= mid && right <= end)
        {
            if (axis[left, 1] < axis[right, 1])
            {
                copy[pnt, 0] = axis[left, 0];
                copy[pnt, 1] = axis[left, 1];
                left++;
            }
            else if (axis[left, 1] > axis[right, 1])
            {
                copy[pnt, 0] = axis[right, 0];
                copy[pnt, 1] = axis[right, 1];
                right++;
            }
            else
            {
                if (axis[left, 0] <= axis[right, 0])
                {
                    copy[pnt, 0] = axis[left, 0];
                    copy[pnt, 1] = axis[left, 1];
                    left++;
                }
                else
                {
                    copy[pnt, 0] = axis[right, 0];
                    copy[pnt, 1] = axis[right, 1];
                    right++;
                }
            }

            pnt++;
        }

        while (right <= end)
        {
            copy[pnt, 0] = axis[right, 0];
            copy[pnt, 1] = axis[right, 1];
            pnt++;
            right++;
        }

        while (left <= mid)
        {
            copy[pnt, 0] = axis[left, 0];
            copy[pnt, 1] = axis[left, 1];
            pnt++;
            left++;
        }

        for (int i = start; i <= end; i++)
        {
            axis[i, 0] = copy[i, 0];
            axis[i, 1] = copy[i, 1];
        }
    }

    static void MergeSort(int[,] axis, int[,] copy, int start, int end)
    {
        if (start < end)
        {
            int mid = (start + end) / 2;

            MergeSort(axis, copy, start, mid);
            MergeSort(axis, copy, mid + 1, end);
            Merge(axis, copy, start, mid, end);
        }
    }

    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int[,] axis = new int[n, 2];
        int[,] copy = new int[n, 2];
        string[] input;

        for (int i = 0; i < n; i++)
        {
            input = Console.ReadLine().Split();
            axis[i, 0] = int.Parse(input[0]);
            axis[i, 1] = int.Parse(input[1]);
        }

        MergeSort(axis, copy, 0, n - 1);

        System.Text.StringBuilder sb = new System.Text.StringBuilder();

        for (int i = 0; i < n; i++)
            sb.AppendFormat("{0} {1}", axis[i, 0], axis[i, 1]).AppendLine();

        Console.Write(sb);
    }
}