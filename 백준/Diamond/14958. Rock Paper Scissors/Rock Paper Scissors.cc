/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * FFT Reference : koosaga
 */

#include <bits/stdc++.h>

using namespace std;
#define ll long long
const double PI = acos(-1);
const int MAX = 202020;
typedef complex<double> cpx;

void fft(vector<cpx> &f, bool inv) {
    int n = f.size(), j = 0;

    vector<cpx> roots(n >> 1);
    for (int i = 1; i < n; i++){
        int bit = n >> 1;

        while (j >= bit) j -= bit, bit >>= 1;
        j += bit;

        if (i < j) swap(f[i], f[j]);
    }

    double angle = 2 * PI / n * (inv ? -1 : 1);
    for (int i = 0; i < n >> 1; i++) roots[i] = cpx(cos(angle * i), sin(angle * i));
    for (int i = 2; i <= n; i <<= 1){
        int step = n / i;

        for (int j = 0; j < n; j += i){
            for (int k = 0; k < i / 2; k++){
                cpx u = f[j + k], v = f[j + k + i / 2] * roots[step * k];
                f[j + k] = u + v;
                f[j + k + i / 2] = u - v;
            }
        }
    }

    if (inv) for (int i = 0; i < n; i++) f[i] /= n;
}

vector<int> mul(vector<cpx> &u, vector<cpx> &v) {
    vector<cpx> fu(u.begin(), u.end()), fv(v.begin(), v.end());

    int n = 2; while (n < u.size() + v.size()) n <<= 1;
    fu.resize(n); fv.resize(n);

    fft(fu, false); fft(fv, false);
    for (int i = 0; i < n; i++) fu[i] *= fv[i];

    fft(fu, true);
    vector<int> w(n);
    for (int i = 0; i < n; i++) w[i] = round(fu[i].real());

    return w;
}

void parse(string s, int n){
	int len = s.size() / n;
	int tmp = 0;
	int i = 0, j = 0;

	if (s.size() % n == 1) ++len;

	if (s.size() % n == 1){
		for (; i < s.size() % n; ++i) tmp = tmp * 10 + s[i] - '0';
		s[j++] = tmp;
	}

	while (i < s.size()){
		tmp = 0;
		for (int k = 0; k < n; ++k) tmp = tmp * 10 + s[i + k] - '0';
		i += n;
		s[j++] = tmp;
	}
}

int substitution(char c){
    if (c == 'R') return 0;
    else if (c == 'P') return 1;
    else return 2;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);

    int n, m;
    string you, me;
    cin >> n >> m >> you >> me;
    reverse(me.begin(), me.end());

    vector<cpx> u(n), v(m);
    int ans[MAX];

    for (int i = 0; i < 3; i++){
        for (int j = 0; j < n; j++) u[j] = (substitution(you[j]) + 1) % 3 == i ? 1 : 0;
        for (int j = 0; j < m; j++) v[j] = substitution(me[j]) == i ? 1 : 0;
        vector<int> w = mul(u, v);
        for (int j = m - 1; j < n + m - 1; j++) ans[j] += w[j];
    }

    int max = 0;
    for (int i = m - 1; i < n + m - 1; i++) max = std::max(max, ans[i]);
    cout << max;
}