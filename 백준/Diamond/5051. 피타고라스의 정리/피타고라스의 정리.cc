/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * FFT Reference : koosaga team note
 */

#include <bits/stdc++.h>

using namespace std;
#define int long long
const double PI = acos(-1);
const int MAX = 500001;
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
    for (int i = 0; i < n; i++) w[i] = fu[i].real() + (fu[i].real() > 0 ? 0.5 : -0.5);

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

main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);

    int n;
    cin >> n;

    vector<int> rem(n), remdbl(n);
    for (int i = 1; i < n; i++) rem[i * i % n]++, remdbl[2 * i * i % n]++;

    vector<cpx> f(n);
    for (int i = 0; i < n; i++) f[i] = cpx(rem[i], 0);

    vector<int> w = mul(f, f);
    int cnt = 0;

    for (int i = 1; i < n; i++){
        int t = i * i % n;
        int tot = w[t] + w[t + n];
        int sub = remdbl[t];
        cnt += (tot - sub) / 2 + sub;
    }

    cout << cnt;
}