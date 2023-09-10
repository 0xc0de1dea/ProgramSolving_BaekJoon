# [Diamond V] Power towers - 13970 

[문제 링크](https://www.acmicpc.net/problem/13970) 

### 성능 요약

메모리: 26732 KB, 시간: 268 ms

### 분류

오일러 피 함수, 분할 정복을 이용한 거듭제곱, 수학, 정수론

### 문제 설명

<p>Suppose that we have a non empty list of positive integers: [x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>N</sub>]. The power tower corresponding to this list is the number defined as:</p>

<p style="text-align: center;">$x_1^{x_2^{.^{.^{.^{x_n}}}}}$, that is $x_1^{\left(x_2^{\left(.^{.^{.^{x_n}}}\right)}\right)}$.</p>

<p>Power towers can be very big numbers, even when the list consists of only a few small integers. For example, the power tower of the list [2, 3, 2] is equal to 2<sup>9</sup> = 512, and that of [5, 2, 3, 2] is equal to 5<sup>512</sup> and has 358 decimal digits. In this task, you will have to compute power towers modulo a given positive integer M.</p>

### 입력 

 <p>The first line of the input will contain two positive integers: T and M. Each of the following T lines will contain a positive integer N, followed by a list of N positive integers x<sub>1</sub>, x<sub>2</sub>, ... x<sub>N</sub>. There will be exactly one space separating consecutive numbers in all lines of the input.</p>

### 출력 

 <p>The output must contain T lines, each containing a single non-negative integer. The number in line k must be equal to the result of the power tower for the k-th given list modulo M.</p>

