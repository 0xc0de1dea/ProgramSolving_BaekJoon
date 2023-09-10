using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int[] arr = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int state = arr[0], cnt = 0;
        
        for (int i = 1; i < 8; i++)
        {
            if (arr[i] - state == 1)
                cnt++;
            else if (arr[i] - state == -1)
                cnt--;

            state = arr[i];
        }

        if (cnt == 7)
            Console.Write("ascending");
        else if (cnt == -7)
            Console.Write("descending");
        else
            Console.Write("mixed");
    }
}