import sys
from collections import deque

N, M = map(int, input().split(' '))
q = deque(map(int, input().split(' ')))
q2 = deque(range(1, N+1))
total = 0

for i in q:
    cnt = 0
    while q2[0] != i:
        q2.rotate(-1)
        cnt += 1
    total += min(cnt, len(q2) - cnt)
    q2.popleft()
print(total)
