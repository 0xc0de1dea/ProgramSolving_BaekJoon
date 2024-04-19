# [Silver II] 속이기 - 11895 

[문제 링크](https://www.acmicpc.net/problem/11895) 

### 성능 요약

메모리: 14300 KB, 시간: 124 ms

### 분류

애드 혹, 수학

### 제출 일자

2024년 4월 19일 09:40:44

### 문제 설명

<p>승현이는 남을 속이는 것을 참 좋아합니다. 오늘 승현이는 수열을 가지고 우리를 속여 보려고 합니다.</p>

<p>승현이는 길이가 n인 자연수로 구성된 수열 a<sub>1</sub>, a<sub>2</sub>, ⋯, a<sub>n</sub>을 들고 왔습니다. 그러더니 갑자기 이 수열의 각 원소를 두 그룹 X나 Y 중 하나에 넣으라고 합니다. 편의상 X의 모든 원소를 X<sub>1</sub>, X<sub>2</sub>, ⋯, X<sub>k</sub>, Y의 모든 원소를 Y<sub>1</sub>, Y<sub>2</sub>, ⋯, Y<sub>n−k</sub>로 둡시다. (단 k > 0,n−k > 0)</p>

<p>승현이는 X<sub>1</sub> ⊕ X<sub>2</sub> ⊕ ⋯ ⊕ X<sub>k</sub>의 값과 Y<sub>1</sub> ⊕ Y<sub>2</sub> ⊕ ⋯ ⊕ Y<sub>n−k</sub>의 값이 같으면 우리에게 X<sub>1</sub> + X<sub>2</sub> + ⋯ + X<sub>k</sub>원을 준다고 합니다.</p>

<p>승현이에게 너무 많이 속은 여러분은 믿기지 않지만, 돈을 잃을 일은 없으니 한 번 시도해 보기로 했습니다. 승현이가 제시한 수열 a가 주어질 때, 여러분이 돈을 받을 수 있는지, 돈을 받을 수 있다면 최대 얼마나 받을 수 있는지 구하는 프로그램을 작성하세요.</p>

<p>참고: ⊕는 배타적 논리합(XOR)를 뜻합니다. 자세한 설명은 <a href="http://ko.wikipedia.org/wiki/%EB%B0%B0%ED%83%80%EC%A0%81_%EB%85%BC%EB%A6%AC%ED%95%A9#.EB.B9.84.ED.8A.B8.EA.B0.84_.EB.B0.B0.ED.83.80.EC.A0.81_.EB.85.BC.EB.A6.AC.ED.95.A9">여기</a>와 <a href="http://ko.wikipedia.org/wiki/XOR_%EC%8A%A4%EC%99%91_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98#.EC.A6.9D.EB.AA.85">여기</a>를 참고하세요.</p>

### 입력 

 <p>첫 번째 줄에 n (1 ≤ n ≤ 1,000)이 주어지고, 두 번째 줄에 a<sub>1</sub>, a<sub>2</sub>, ⋯ ,a<sub>n</sub> (1 ≤ a<sub>1</sub>, a<sub>2</sub>, ⋯, a<sub>n</sub> ≤ 10<sup>6</sup>)이 공백을 사이로 두고 주어집니다.</p>

### 출력 

 <p>첫 번째 줄에 여러분이 받을 수 있는 돈의 최댓값을 출력합니다. 만약 돈을 받을 수 없다면 0을 출력합니다.</p>

