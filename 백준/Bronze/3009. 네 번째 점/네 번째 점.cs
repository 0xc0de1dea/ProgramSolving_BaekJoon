using System;
using System.Linq;

class Program
{
    static void Main()
    {
        int[] point_1 = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int[] point_2 = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int[] point_3 = Console.ReadLine().Split().Select(int.Parse).ToArray();
        int[] point_4 = new int[2];

        if (point_1[0] == point_2[0])
            point_4[0] = point_3[0];
        else if (point_1[0] == point_3[0])
            point_4[0] = point_2[0];
        else if (point_2[0] == point_3[0])
            point_4[0] = point_1[0];

        if (point_1[1] == point_2[1])
            point_4[1] = point_3[1];
        else if (point_1[1] == point_3[1])
            point_4[1] = point_2[1];
        else if (point_2[1] == point_3[1])
            point_4[1] = point_1[1];

        Console.Write("{0} {1}", point_4[0], point_4[1]);
    }
}