from collections import deque

K = int(input())
q = deque()
total = 0

for i in range(K):
    item = int(input())
    if item == 0:
        q.pop()
    else:
        q.append(item)

while len(q) != 0:
    total += q.pop()

print(total)
