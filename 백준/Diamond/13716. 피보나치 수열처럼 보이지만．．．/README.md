# [Diamond V] 피보나치 수열처럼 보이지만... - 13716 

[문제 링크](https://www.acmicpc.net/problem/13716) 

### 성능 요약

메모리: 2164 KB, 시간: 36 ms

### 분류

벌리캠프–매시, 다이나믹 프로그래밍, 분할 정복을 이용한 거듭제곱, 수학

### 문제 설명

<p>피보나치 수열은 다음과 같이 정의된다.</p>

<p style="text-align: center;">F<sub>1</sub> = 1, F<sub>2</sub> = 2, F<sub>i</sub> = F<sub>i-1</sub> + F<sub>i-2</sub> (i > 2).</p>

<p>A<sub>i</sub>(k)는 다음과 같이 정의 된다.</p>

<p style="text-align: center;">A<sub>i</sub>(k) = F<sub>i</sub> × i<sup>k</sup> (i ≥ 1).</p>

<p>n과 k가 주어졌을 때, A<sub>1</sub>(k) + A<sub>2</sub>(k) + ... + A<sub>n</sub>(k)를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 n과 k가 주어진다. (1 ≤ n ≤ 10<sup>17</sup>, 1 ≤ k ≤ 40)</p>

### 출력 

 <p>첫째 줄에 A<sub>1</sub>(k) + A<sub>2</sub>(k) + ... + A<sub>n</sub>(k)를 10<sup>9</sup> + 7로 나눈 나머지를 출력한다.</p>

