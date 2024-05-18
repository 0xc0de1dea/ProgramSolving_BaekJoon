# [Platinum V] Estrela - 13646 

[문제 링크](https://www.acmicpc.net/problem/13646) 

### 성능 요약

메모리: 16180 KB, 시간: 708 ms

### 분류

오일러 피 함수, 수학, 정수론

### 제출 일자

2024년 5월 18일 23:26:52

### 문제 설명

<p>Fernando ganhou um compasso de aniversário, e agora sua diversão favorita é desenhar estrelas: primeiro, ele marca N pontos sobre a circunferência, dividindo-a em N arcos iguais; depois, ele liga cada ponto ao k-ésimo ponto seguinte, até voltar ao ponto inicial.</p>

<p>Dependendo do valor de k, Fernando pode ou não atingir todos os pontos marcados sobre a circunferência; quando isto acontece, a estrela é chamada de completa. Por exemplo, quando N = 8, as possíveis estrelas são as mostradas no desenho abaixo; as estrelas (a) e (c) são completas, enquanto as estrelas (b) e (d) não o são.</p>

<p><img alt="" src="https://onlinejudgeimages.s3.amazonaws.com/problem/13646/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202017-01-05%20%EC%98%A4%ED%9B%84%209.40.59.png" style="height:148px; width:609px">Dependendo do valor de N, pode ser possível desenhar muitas estrelas diferentes; Fernando pediu que você escrevesse um programa que, dado N, determina o número de estrelas completas que ele pode desenhar.</p>

### 입력 

 <p>Cada caso de teste contém de uma única linha, contendo um único inteiro N, indicando o número de arcos no qual a circunferência foi dividida.</p>

<p>Restrições</p>

<ul>
	<li>3 ≤ N < 2<sup>31</sup></li>
</ul>

### 출력 

 <p>Para cada caso de teste, seu programa deve imprimir uma única linha contendo um único inteiro, indicando o número de estrelas completas que podem ser desenhadas.</p>

