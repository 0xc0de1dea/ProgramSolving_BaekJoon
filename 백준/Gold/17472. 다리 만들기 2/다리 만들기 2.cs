using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    static byte[] mapSize;
    static byte w, h;

    static void Main()
    {
        mapSize = Console.ReadLine().Split().Select((e) => byte.Parse(e)).ToArray();
        h = mapSize[0];
        w = mapSize[1];

        Graph graph = new Graph(h, w);

        for (byte i = 0; i < h; i++)
        {
            byte[] temp = Console.ReadLine().Split().Select((e) => byte.Parse(e)).ToArray();

            for (byte j = 0; j < w; j++)
            {
                graph.InputMatter(i, j, temp[j]);
            }
        }

        graph.ClusteringIsland(); // 섬 군집화
        graph.BuildBridge(); // 브릿지 커넥션
        graph.Print(graph.Kruskal()); // 모든 섬을 연결하는 브릿지 길이의 최소값을 구한 후 프린트
    }

    class Map
    {
        public byte matter;
        public byte islandID;
    }

    class Bridge
    {
        public byte start;
        public byte end;
        public byte size;

        public Bridge()
        {
        }

        public Bridge(byte start)
        {
            this.start = start;
            size = 0;
        }
    }

    class Graph
    {
        byte n, m; // 가로, 세로 사이즈
        Map[,] map; // 맵 정보
        List<Bridge> bridge = new List<Bridge>(); // 브릿지 리스트
        byte givingID = 0; // 배분 ID;

        public Graph(byte n, byte m)
        {
            this.n = n;
            this.m = m;

            map = new Map[n, m];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    map[i, j] = new Map();
        }

        public void InputMatter(byte i, byte j, byte matter)
        {
            map[i, j].matter = matter;
        }

        public void ClusteringIsland()
        {
            for (byte row = 0; row < n; row++)
            {
                for (byte col = 0; col < m; col++)
                {
                    if (map[row, col].matter == 1 && map[row, col].islandID == 0)
                    {
                        givingID++;
                        FindNearby(row, col, givingID);
                    }
                }
            }
        }

        public void FindNearby(byte startRow, byte startCol, byte givingID)
        {
            List<int[]> openList = new List<int[]>
            {
                new int[2] { startRow, startCol }
            };

            int row, col;
            int current;

            while (openList.Count > 0)
            {
                current = openList.Count - 1;
                row = openList[current][0];
                col = openList[current][1];

                map[row, col].islandID = givingID;

                if ((col + 1) < m)
                    if (map[row, col + 1].islandID == 0 && map[row, col + 1].matter == 1)
                        openList.Add(new int[2] { row, col + 1 });

                if ((row + 1) < n)
                    if (map[row + 1, col].islandID == 0 && map[row + 1, col].matter == 1)
                        openList.Add(new int[2] { row + 1, col });

                if ((col - 1) >= 0)
                    if (map[row, col - 1].islandID == 0 && map[row, col - 1].matter == 1)
                        openList.Add(new int[2] { row, col - 1 });

                if ((row - 1) >= 0)
                    if (map[row - 1, col].islandID == 0 && map[row - 1, col].matter == 1)
                        openList.Add(new int[2] { row - 1, col });

                openList.RemoveAt(current);
            }
        }

        public void BuildBridge()
        {
            byte state;
            byte current;
            byte startID = 0, endID = 0;
            byte[,] minSize = new byte[givingID + 1, givingID + 1];
            for (byte i = 1; i <= givingID; i++)
                for (byte j = 1; j <= givingID; j++)
                    minSize[i, j] = byte.MaxValue;
            bool isBuild = false;

            // 세로방향 브릿지 빌드
            for (byte col = 0; col < m; col++)
            {
                state = map[0, col].matter;

                for (byte row = 1; row < n; row++)
                {
                    current = map[row, col].matter;
                    state += current;

                    if (state == 1 && current == 0)
                    {
                        isBuild = true;
                        startID = map[row - 1, col].islandID;
                        bridge.Add(new Bridge(startID));
                    }

                    if (isBuild)
                    {
                        if (current == 1 || row == n - 1)
                        {
                            isBuild = false;
                            endID = map[row, col].islandID;
                            bridge[bridge.Count - 1].end = endID;

                            if (bridge[bridge.Count - 1].size != 1 && endID != 0 && endID != startID)
                            {
                                if (minSize[startID, endID] > bridge[bridge.Count - 1].size)
                                    minSize[startID, endID] = bridge[bridge.Count - 1].size;
                            }
                            else
                                bridge.RemoveAt(bridge.Count - 1);
                        }
                        else if (current == 0)
                        {
                            bridge[bridge.Count - 1].size++;
                        }
                    }

                    state = current;
                }
            }

            // 가로방향 브릿지 빌드
            for (byte row = 0; row < n; row++)
            {
                state = map[row, 0].matter;

                for (byte col = 1; col < m; col++)
                {
                    current = map[row, col].matter;
                    state += current;

                    if (state == 1 && current == 0)
                    {
                        isBuild = true;
                        startID = map[row, col - 1].islandID;
                        bridge.Add(new Bridge(startID));
                    }

                    if (isBuild)
                    {
                        if (current == 1 || col == m - 1)
                        {
                            isBuild = false;
                            endID = map[row, col].islandID;
                            bridge[bridge.Count - 1].end = endID;

                            if (bridge[bridge.Count - 1].size != 1 && endID != 0 && endID != startID)
                            {
                                if (minSize[startID, endID] > bridge[bridge.Count - 1].size)
                                    minSize[startID, endID] = bridge[bridge.Count - 1].size;
                            }
                            else
                                bridge.RemoveAt(bridge.Count - 1);
                        }
                        else if (current == 0)
                        {
                            bridge[bridge.Count - 1].size++;
                        }
                    }

                    state = current;
                }
            }

            byte checkStart, checkEnd, checkSize;
            bool[,] removeState = new bool[givingID + 1, givingID + 1];

            // 1:1 대응관계로 만드는 작업
            for (int i = bridge.Count - 1; i >= 0; i--)
            {
                checkStart = bridge[i].start;
                checkEnd = bridge[i].end;
                checkSize = bridge[i].size;

                if (checkSize >= minSize[checkStart, checkEnd])
                {
                    if (removeState[checkStart, checkEnd] == true)
                    {
                        bridge.RemoveAt(i);
                    }
                    else if (!removeState[checkStart, checkEnd] && checkSize == minSize[checkStart, checkEnd])
                    {
                        removeState[checkStart, checkEnd] = true;
                    }
                }
            }
        }

        public Tuple<int, int> Kruskal()
        {
            // 브릿지 사이즈로 오름차순 정렬
            bridge.Sort(delegate (Bridge a, Bridge b)
            {
                if (a.size > b.size)
                    return 1;
                else if (a.size < b.size)
                    return -1;
                return 0;
            });

            int[] set = new int[givingID + 1];

            // 섬을 정점으로 가정. 각 정점의 parent 위치 데이터를 저장
            for (byte i = 1; i < givingID + 1; i++)
                set[i] = i;

            int sum = 0;
            int sumCount = givingID - 1;
            Bridge target = new Bridge();

            for (int i = 0; i < bridge.Count; i++)
            {
                target = bridge[i];

                // 브릿지 커넥션은 섬의 개수 - 1 번만 이루어지면 됨
                if (sumCount <= 0)
                    break;

                // parent가 같지 않은 경우, 다시말해 사이클이 발생하지 않을 경우에만 연결이 이루어짐
                if (!FindParent(set, target.start, target.end))
                {
                    sum += target.size;
                    sumCount--;
                    UnionParent(set, target.start, target.end);
                }
            }

            return new Tuple<int, int>(sum, sumCount);
        }

        // 해당 정점의 parent를 불러옴
        int GetParent(int[] set, int get)
        {
            if (set[get] == get)
                return get;
            return set[get] = GetParent(set, set[get]);
        }

        // 해당 정점을 parent에 소속시킴
        void UnionParent(int[] set, int start, int end)
        {
            start = GetParent(set, start);
            end = GetParent(set, end);

            if (start < end)
                set[end] = start;
            else
                set[start] = end;
        }

        bool FindParent(int[] set, int start, int end)
        {
            start = GetParent(set, start);
            end = GetParent(set, end);

            if (start == end)
                return true;
            else
                return false;
        }

        public void Print(Tuple<int, int> kruskal)
        {
            if (kruskal.Item1 == 0 || kruskal.Item2 != 0)
                Console.Write("-1");
            else
                Console.Write(kruskal.Item1);
        }
    }
}