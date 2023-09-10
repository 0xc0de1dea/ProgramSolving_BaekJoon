using System;
using System.Linq;
using System.Text;

class Program
{
    static void Main()
    {
        StringBuilder sb = new StringBuilder();
        int[] ab = Console.ReadLine().Split().Select((e) => int.Parse(e)).ToArray();
        int accuracy = 1004;
        bool appendZero = false;

        if (ab[0] >= ab[1])
        {
            if (ab[0] % ab[1] == 0)
            {
                sb.Append(ab[0] / ab[1]);
                accuracy = 0;
            }
            else
            {
                sb.Append(ab[0] / ab[1]).Append('.');
                ab[0] = ab[0] % ab[1];
            }
        }
        else
            sb.Append("0.");

        while (accuracy > 0)
        {
            while (ab[0] < ab[1])
            {
                if (appendZero)
                    sb.Append("0");
                else
                    appendZero = true;
                ab[0] *= 10;
                accuracy--;
            }

            sb.Append(ab[0] / ab[1]);
            ab[0] = ab[0] % ab[1];
            appendZero = false;
            accuracy--;

            if (ab[0] == 0)
                break;
        }

        Console.Write(sb.ToString());
    }
}