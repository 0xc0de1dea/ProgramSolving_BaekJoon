/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * MillerRabin & PollardRho Reference : Jinhan's Note
 */

#include <bits/stdc++.h>

using namespace std;
using ll = long long;
using ull = unsigned long long;

struct Random {
    mt19937 rand;
    Random() : rand((unsigned)chrono::steady_clock::now().time_since_epoch().count()) { }
    Random(int seed) : rand(seed) { }
    template <typename T = int>
    T getInt(T s = 0, T e = 32767) { return uniform_int_distribution<T>(s, e)(rand); }
} Random;

struct MillerRabin {
    ll mul(ll x, ll y, ll mod){
        ll ret = x * y - mod * ull(1.L / mod * x * y);
        return ret + (ret < 0) * mod - (ret >= mod) * mod;
    }

    ll pow(ll x, ll y, ll mod){
        ll ret = 1; x %= mod;

        while (y > 0){
            if (y & 1) ret = mul(ret, x, mod);
            x = mul(x, x, mod); y >>= 1;
        }

        return ret;
    }

    bool check (ll n, ll a){
        if (n % a == 0) return true;

        ll d = n - 1;
        while (d % 2 == 0){
            if (pow(a, d, n) == n - 1) return true;
            d >>= 1;
        }

        ll ret = pow(a, d, n);
        return ret == n - 1 || ret == 1;
    }

    bool isPrime(ll n){
        if (n == 2 || n == 3 || n == 5 || n == 7) return true;
        if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0) return false;
        if (n < 121) return n > 1;

        if (n < 1ll << 32){
            for (auto& i : { 2, 7, 61 }){
                if (n == i) return true;
                if (n > i && !check(n, i)) return false;
            }
        }
        else {
            for (auto& i : { 2, 325, 9375, 28178, 450775, 9780504, 1795265022 }){
                if (n == i) return true;
                if (n > i && !check(n, i)) return false;
            }
        }
        
        return true;
    }
};

struct PollardRho : public MillerRabin {
    void findFactor(ll n, vector<ll>& primeFactor){
        if (n == 1) return;
        if (~n & 1) { primeFactor.push_back(2); findFactor(n >> 1, primeFactor); return; }
        if (isPrime(n)) { primeFactor.push_back(n); return; }

        ll a, b, c, g = n;
        auto f = [&](ll x) { return (mul(x, x, n) + c) % n; };
        do {
            if (g == n){
                a = b = Random.getInt<ll>(0, n - 3) + 2;
                c = Random.getInt<ll>(0, 19) + 1;
            }

            a = f(a); b = f(f(b)); g = gcd(abs((ll)(a - b)), n);
        } while (g == 1);

        findFactor(g, primeFactor); findFactor(n / g, primeFactor);
    }

    vector<ll> factorize(ll n){
        vector<ll> ret;
        findFactor(n, ret);
        sort(ret.begin(), ret.end());

        return ret;
    }
} PollardRho;

main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);

    ll n;
    cin >> n;
    
    for (auto& primeFactor : PollardRho.factorize(n)){
        cout << primeFactor << '\n';
    }
}