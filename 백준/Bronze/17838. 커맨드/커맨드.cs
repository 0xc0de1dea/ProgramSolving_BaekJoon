using System;

class Program
{
    static void Main()
    {
        int t = int.Parse(Console.ReadLine());
        string[] cmd = new string[t];

        for (int i = 0; i < t; i++)
            cmd[i] = Console.ReadLine();

        for (int i = 0; i < t; i++)
        {
            if (cmd[i].Length == 7)
            {
                if (cmd[i][0] == cmd[i][1] && cmd[i][1] == cmd[i][4] && cmd[i][2] == cmd[i][3] && cmd[i][3] == cmd[i][5] && cmd[i][5] == cmd[i][6] && cmd[i][0] != cmd[i][2])
                    Console.WriteLine("1");
                else
                    Console.WriteLine("0");
            }
            else
                Console.WriteLine("0");
        }
    }
}