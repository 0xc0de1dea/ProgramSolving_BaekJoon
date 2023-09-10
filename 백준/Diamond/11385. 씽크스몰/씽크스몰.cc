/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * FFT Reference : koosaga team note
 */

#include <bits/stdc++.h>

using namespace std;
#define int long long
const double PI = acos(-1);
const int MAX = 1024;
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

vector<int> mul(vector<int> &u, vector<int> &v) {
    vector<cpx> fu(u.begin(), u.end()), fv(v.begin(), v.end());

    int n = 2; while (n < u.size() + v.size()) n <<= 1;
    fu.resize(n); fv.resize(n);

    fft(fu, false); fft(fv, false);
    for (int i = 0; i < n; i++) fu[i] *= fv[i];
    fft(fu, true);

    vector<int> ret(n);
    for (int i = 0; i < n; i++) ret[i] = round(fu[i].real());

    return ret;
}

vector<int> pow(vector<int> base, int exp){
    vector<int> ret(base.size());
    if (exp & 1) ret = base, exp--;
    else ret[0] = 1;

    while (exp){
        if (exp & 1) ret = mul(ret, base);

        base = mul(base, base);
        exp >>= 1;
    }

    return ret;
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

signed main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);

    int n, m, v;
    cin >> n >> m;

    vector<int> f, g, nf, ng;
    for (int i = 0; i <= n; i++) cin >> v, f.push_back(v);
    for (int i = 0; i <= m; i++) cin >> v, g.push_back(v);

    nf = f, ng = g;
    for (auto &e : nf) e >>= 10;
    for (auto &e : ng) e >>= 10;
    vector<int> h11 = mul(nf, ng);

    nf = f, ng = g;
    for (auto &e : nf) e >>= 10;
    for (auto &e : ng) e &= ((1 << 10) - 1);
    vector<int> h12 = mul(nf, ng);

    nf = f, ng = g;
    for (auto &e : nf) e &= ((1 << 10) - 1);
    for (auto &e : ng) e >>= 10;
    vector<int> h21 = mul(nf, ng);

    nf = f, ng = g;
    for (auto &e : nf) e &= ((1 << 10) - 1);
    for (auto &e : ng) e &= ((1 << 10) - 1);
    vector<int> h22 = mul(nf, ng);

    int ans = 0;
    for (int i = 0; i <= n + m; i++) ans ^= (h11[i] << 20) + ((h12[i] + h21[i]) << 10) + h22[i];

    cout << ans;
}