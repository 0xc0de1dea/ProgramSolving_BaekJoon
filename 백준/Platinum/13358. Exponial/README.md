# [Platinum I] Exponial - 13358 

[문제 링크](https://www.acmicpc.net/problem/13358) 

### 성능 요약

메모리: 14176 KB, 시간: 128 ms

### 분류

오일러 피 함수, 분할 정복을 이용한 거듭제곱, 수학, 정수론

### 문제 설명

<p>Everybody loves big numbers (if you do not, you might want to stop reading at this point). There are many ways of constructing really big numbers known to humankind, for instance:</p>

<ul>
	<li>Exponentiation: \(  42^{2016}= \underbrace {42 \cdot 42 \cdot ... \cdot 42}_\text{2016 times} \)</li>
	<li>Factorials: 2016! = 2016 · 2015 · . . . · 2 · 1.</li>
</ul>

<p>In this problem we look at their lesser-known love-child the exponial, which is an operation defined for all positive integers n as</p>

<p>exponial(n) = \( n^{{(n-1)}^{{{(n-2)}^{{...}^{{2^1}}}}}} \)</p>

<p>For example, exponial(1) = 1 and exponial(5) = \( 5^{4^{3^{2^1}}} \) ≈ 6.206 · 10<sup>183230</sup> which is already pretty big. Note that exponentiation is right-associative: \( a^{b^c}=a^{(b^c)} \).</p>

<p>Since the exponials are really big, they can be a bit unwieldy to work with. Therefore we would like you to write a program which computes exponial(n) mod m (the remainder of exponial(n) when dividing by m).</p>

### 입력 

 <p>The input consists of two integers n (1 ≤ n ≤ 10<sup>9</sup>) and m (1 ≤ m ≤ 10<sup>9</sup>)</p>

### 출력 

 <p>Output a single integer, the value of exponial(n) mod m.</p>

