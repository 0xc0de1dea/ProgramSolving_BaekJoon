using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    static int[,] sudoku = new int[9, 9];
    static List<int[]> blank_List = new List<int[]>();
    static bool found = false;

    static void Dfs(int idx)
    {
        if (idx == blank_List.Count)
        {
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                    Console.Write("{0} ", sudoku[i, j]);

                Console.WriteLine();
            }

            found = true;
        }

        for (int input = 1; input <= 9; input++)
        {
            if (found)
                return;

            bool isValid = true;
            int blank_X = blank_List[idx][1];
            int blank_Y = blank_List[idx][0];

            for (int x = 0; x < 9; x++)
                if (sudoku[blank_Y, x] == input)
                    isValid = false;

            for (int y = 0; y < 9; y++)
                if (sudoku[y, blank_X] == input)
                    isValid = false;

            for (int squareY = blank_Y / 3 * 3; squareY < blank_Y / 3 * 3 + 3; squareY++)
                for (int squareX = blank_X / 3 * 3; squareX < blank_X / 3 * 3 + 3; squareX++)
                    if (sudoku[squareY, squareX] == input)
                        isValid = false;

            if (isValid)
            {
                sudoku[blank_Y, blank_X] = input;
                Dfs(idx + 1);
                sudoku[blank_Y, blank_X] = 0;
            }
        }
    }

    static void Main()
    {
        for (int i = 0; i < 9; i++)
        {
            int[] input = Console.ReadLine().Split().Select(int.Parse).ToArray();

            for (int j = 0; j < 9; j++)
            {
                sudoku[i, j] = input[j];

                if (input[j] == 0)
                    blank_List.Add(new int[] { i, j });
            }
        }

        Dfs(0);
    }
}