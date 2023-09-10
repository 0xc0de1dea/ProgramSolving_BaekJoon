using System;

class Program
{
    static void Main()
    {
        string croatia = Console.ReadLine();
        int cnt = 0;

        for (int i = 0; i < croatia.Length; i++)
        {
            char cur = croatia[i];
            char next = ' ';
            char nextnext = ' ';

            if (i + 1 < croatia.Length)
                next = croatia[i + 1];

            if (i + 2 < croatia.Length)
                nextnext = croatia[i + 2];

            if (cur == 'c' && next == '=') i++;
            else if (cur == 'c' && next == '-') i++;
            else if (cur == 'd' && next == 'z' && nextnext == '=') i += 2;
            else if (cur == 'd' && next == '-') i++;
            else if (cur == 'l' && next == 'j') i++;
            else if (cur == 'n' && next == 'j') i++;
            else if (cur == 's' && next == '=') i++;
            else if (cur == 'z' && next == '=') i++;

            cnt++;
        }

        Console.Write(cnt);
    }
}