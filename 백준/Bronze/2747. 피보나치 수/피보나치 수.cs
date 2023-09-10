using System;

class BigNumber
{
    public long[] num;

    public BigNumber(string num)
    {
        int pnt = 0;
        int size = (int)Math.Ceiling(num.Length / 15m);

        this.num = new long[size];

        while (pnt < num.Length)
        {
            string partition = "";

            for (int i = pnt; i < pnt + 15; i++)
            {
                if (i < num.Length)
                    partition += num[i];
                else
                    break;
            }

            this.num[pnt / 15] = long.Parse(partition);
            pnt += 15;
        }
    }

    private BigNumber(long[] num)
    {
        this.num = num;
    }

    public static BigNumber operator +(BigNumber a, BigNumber b)
    {
        int scanA = a.num.Length - 1;
        int scanB = b.num.Length - 1;
        long[] result;
        long[] temp = new long[scanA > scanB ? scanA + 2 : scanB + 2];
        long carry = 0;

        for (int i = scanA > scanB ? scanA : scanB; i >= 0; i--)
        {
            long add = 0;

            if (scanA < 0)
                add = long.Parse(b.num[scanB].ToString()) + carry;
            else if (scanB < 0)
                add = long.Parse(a.num[scanA].ToString()) + carry;
            else
                add = long.Parse(a.num[scanA].ToString()) + long.Parse(b.num[scanB].ToString()) + carry;

            scanA--;
            scanB--;
            carry = add / 1000000000000000;

            temp[i + 1] = add % 1000000000000000;
        }

        if (carry > 0)
        {
            result = new long[a.num.Length > b.num.Length ? a.num.Length + 1 : b.num.Length + 1];

            temp[0] = carry;
            result = temp;
        }
        else
        {
            result = new long[a.num.Length > b.num.Length ? a.num.Length : b.num.Length];

            for (int i = 0; i < temp.Length - 1; i++)
                result[i] = temp[i + 1];
        }

        return new BigNumber(result);
    }

    public override string ToString()
    {
        System.Text.StringBuilder result = new System.Text.StringBuilder();
        int numLength = num.Length;

        for (int i = 0; i < numLength; i++)
        {
            if (i != 0)
                if (num[i].ToString().Length < 15)
                    for (int j = num[i].ToString().Length; j < 15; j++)
                        result.Append(0);

            result.Append(num[i]);
        }

        return result.ToString();
    }
}

class Program
{
    static BigNumber Fibonacci(int n, BigNumber cur, BigNumber prev)
    {
        if (n == 1)
            return cur;
        else if (n == 0)
            return prev;

        return Fibonacci(--n, cur + prev, cur);
    }

    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        BigNumber fibonacci = Fibonacci(n, new BigNumber("1"), new BigNumber("0"));
        Console.WriteLine(fibonacci.ToString());
    }
}