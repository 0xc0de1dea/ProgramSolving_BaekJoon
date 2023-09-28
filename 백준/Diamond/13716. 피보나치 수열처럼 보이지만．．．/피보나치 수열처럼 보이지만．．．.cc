/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * BerlekampMessey Reference : koosaga
 */

#include <bits/stdc++.h>

using namespace std;
using ll = long long;
using matrix = vector<vector<ll>>;
const ll mod = 1000000007;

ll add(ll x, ll y, ll mod){
    x %= mod; y %= mod;
    return (x >= mod - y ? x - (mod - y) : x + y);
}

ll mul(ll x, ll y, ll mod){
    ll ret = 0; x %= mod; y %= mod;
    while (y > 0){
        if (y & 1) ret = add(ret, x, mod);
        x = add(x, x, mod); y >>= 1;
    }
    return ret;
}

ll ipow(ll x, ll p){
	ll ret = 1, piv = x;
	while(p){
		if(p & 1) ret = ret * piv % mod;
		piv = piv * piv % mod;
		p >>= 1;
	}
	return ret;
}
vector<int> berlekamp_massey(vector<int> x){
	vector<int> ls, cur;
	int lf, ld;
	for(int i=0; i<x.size(); i++){
		ll t = 0;
		for(int j=0; j<cur.size(); j++){
			t = (t + 1ll * x[i-j-1] * cur[j]) % mod;
		}
		if((t - x[i]) % mod == 0) continue;
		if(cur.empty()){
			cur.resize(i+1);
			lf = i;
			ld = (t - x[i]) % mod;
			continue;
		}
		ll k = -(x[i] - t) * ipow(ld, mod - 2) % mod;
		vector<int> c(i-lf-1);
		c.push_back(k);
		for(auto &j : ls) c.push_back(-j * k % mod);
		if(c.size() < cur.size()) c.resize(cur.size());
		for(int j=0; j<cur.size(); j++){
			c[j] = (c[j] + cur[j]) % mod;
		}
		if(i-lf+(int)ls.size()>=(int)cur.size()){
			tie(ls, lf, ld) = make_tuple(cur, i, (t - x[i]) % mod);
		}
		cur = c;
	}
	for(auto &i : cur) i = (i % mod + mod) % mod;
	return cur;
}
int get_nth(vector<int> rec, vector<int> dp, ll n){
	int m = rec.size();
	vector<int> s(m), t(m);
	s[0] = 1;
	if(m != 1) t[1] = 1;
	else t[0] = rec[0];
	auto mul = [&rec](vector<int> v, vector<int> w){
		int m = v.size();
		vector<int> t(2 * m);
		for(int j=0; j<m; j++){
			for(int k=0; k<m; k++){
				t[j+k] += 1ll * v[j] * w[k] % mod;
				if(t[j+k] >= mod) t[j+k] -= mod;
			}
		}
		for(int j=2*m-1; j>=m; j--){
			for(int k=1; k<=m; k++){
				t[j-k] += 1ll * t[j] * rec[k-1] % mod;
				if(t[j-k] >= mod) t[j-k] -= mod;
			}
		}
		t.resize(m);
		return t;
	};
	while(n){
		if(n & 1) s = mul(s, t);
		t = mul(t, t);
		n >>= 1;
	}
	ll ret = 0;
	for(int i=0; i<m; i++) ret += 1ll * s[i] * dp[i] % mod;
	return ret % mod;
}
int guess_nth_term(vector<int> x, ll n){
	if(n < x.size()) return x[n];
	vector<int> v = berlekamp_massey(x);
	if(v.empty()) return 0;
	return get_nth(v, x, n);
}

matrix operator * (matrix& a, matrix& b){
	matrix c(2, vector<ll>(2));

	for (int i = 0; i < 2; i++){
		for (int j = 0; j < 2; j++){
			for (int k = 0; k < 2; k++){
				c[i][j] += mul(a[i][k], b[k][j], mod);
			}
			c[i][j] %= mod;
		}
	}

	return c;
}

matrix matrix_2x2_pow(matrix& m, ll exp){
	matrix ret = {{ 1, 0 }, { 0, 1 }};

	while (exp){
		if (exp & 1) ret = ret * m;
		m = m * m; exp >>= 1;
	}

	return ret;
}

main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);

    ll n, dp = 0; cin >> n;
    int k; cin >> k;
    vector<int> f;
    
    for (ll i = 1; i < 1000; i++){
		matrix fibo_matrix = {{ 1, 1 }, { 1, 0 }};
        fibo_matrix = matrix_2x2_pow(fibo_matrix, i);
		ll ik = ipow(i, k);
		dp = add(dp, mul(fibo_matrix[0][0], ik, mod), mod);
        f.push_back(dp);
    }

	cout << guess_nth_term(f, n - 1);
}