using System;

class Program
{
    static void Merge(short[] numbers, short start, short mid, short end)
    {
        short[] result = new short[numbers.Length];
        short pnt = start;
        short left = start;
        short right = (short)(mid + 1);
        
        while (left <= mid && right <= end)
        {
            if (numbers[left] <= numbers[right])
            {
                result[pnt++] = numbers[left];
                left++;
            }
            else
            {
                result[pnt++] = numbers[right];
                right++;
            }
        }

        while (right <= end)
        {
            result[pnt++] = numbers[right];
            right++;
        }
        
        while (left <= mid)
        {
            result[pnt++] = numbers[left];
            left++;
        }

        for (short i = start; i <= end; i++)
            numbers[i] = result[i];
    }

    static void MergeSort(short[] numbers, short start, short end)
    {
        if (start < end)
        {
            short mid = (short)((start + end) / 2);

            MergeSort(numbers, start, mid);
            MergeSort(numbers, (short)(mid + 1), end);
            Merge(numbers, start, mid, end);
        }
    }

    static void Main()
    {
        short n = short.Parse(Console.ReadLine());
        short[] numbers = new short[n];

        for (int i = 0; i < n; i++)
            numbers[i] = short.Parse(Console.ReadLine());

        MergeSort(numbers, 0, (short)(n - 1));

        System.Text.StringBuilder sb = new System.Text.StringBuilder();

        for (int i = 0; i < n; i++)
            sb.Append(numbers[i] + "\n");

        Console.Write(sb);
    }
}