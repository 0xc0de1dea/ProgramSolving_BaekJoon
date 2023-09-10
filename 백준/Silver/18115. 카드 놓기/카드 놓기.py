from collections import deque

N = int(input())
deque_A = deque(map(int, input().split(' ')))
ori_A = deque()
fin_A = deque(range(1, N + 1))

for i in range(N):
    state = deque_A.pop()
    fin_Item = fin_A.popleft()
    if state == 1:
        ori_A.appendleft(fin_Item)
    elif state == 2:
        ori_A.rotate(-1)
        ori_A.appendleft(fin_Item)
        ori_A.rotate(1)
    else:
        ori_A.append(fin_Item)

for i in ori_A:
    print(i, end = ' ')
