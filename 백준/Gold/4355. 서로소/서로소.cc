#include <iostream>

using namespace std;

long long n;
long long ans, num;
bool notprime = false;

void solve() {
  for (long long i = 2; i * i <= n; i++) {
    if (num % i == 0) {
      notprime = true;
      ans = ans / i * (i-1); 
      while (num % i == 0) {
        num /= i;    // 거듭제곱?
      }
    }
  }
  
  if(!notprime && n!=1){
    ans = ans - 1;
    cout << ans <<"\n" ;
  }
  else if(num != 1) { 
    ans = ans / num * (num - 1);
    cout << ans <<"\n";
  }
  else cout << ans <<"\n";
}

void input() {
  while(1){
    cin >> n;
    if(!n) return;
    ans = n;
    num = n;
    notprime = false;
    if(n!=1) solve();
    else if(n==1) cout << "0\n";
  }
}

int main() {
  ios::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(NULL);
  input();
  return 0;
}