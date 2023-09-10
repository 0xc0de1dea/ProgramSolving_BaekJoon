/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

#include <bits/stdc++.h>

using namespace std;
using ull = unsigned long long;

ull a1[] = { 2, 7, 61 };
ull a2[] = { 2, 325, 9375, 28178, 450775, 9780504, 1795265022 };

ull add(ull x, ull y, ull mod){
    x %= mod; y %= mod;

    return (x + y >= mod ? x - (mod - y) : x + y);
}

ull mul(ull x, ull y, ull mod){
    x %= mod; y %= mod;
    ull t = 0;

    while (y > 0){
        if (y & 1) t = add(t, x, mod);
        x = add(x, x, mod);
        y >>= 1;
    }

    return t;
}

ull pow(ull x, ull y, ull mod){
    x %= mod;
    ull t = 1L;

    while (y > 0){
        if (y & 1) t = (t * x) % mod; //mul(t, x, mod);
        x = (x * x) % mod; //mul(x, x, mod);
        y >>= 1;
    }

    return t;
}

bool millerRabin(ull n, ull a){
    ull d = n - 1;

    while (d % 2 == 0){
        if (pow(a, d, n) == n - 1) return true;
        d >>= 1;
    }

    ull t = pow(a, d, n);
    return t == n - 1 || t == 1;
}

bool isPrime(ull n){
    ull max = 4294967296;
    ull limit = 3000000000;

    if (n <= 1) return false;
    if (n < max) limit = 10000;
    if (n <= limit) { for (ull i = 2; i * i <= n; i++) if (n % i == 0) return false; return true; }
    if (n < max) { for (int i = 0; i < 3; i++) if (!millerRabin(n, a1[i])) return false; }
    else { for (int i = 0; i < 7; i++) if (!millerRabin(n, a2[i])) return false; }
    return true;
}

main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL), cout.tie(NULL);

    int n;
    cin >> n;
    ull area, cnt = 0;

    while (n--){
        cin >> area;
        if (isPrime(area * 2 + 1)) cnt++;
    }

    cout << cnt;
}