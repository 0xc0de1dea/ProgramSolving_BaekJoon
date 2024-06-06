#include <bits/stdc++.h>

using namespace std;

int arr[101];

int main(void)
{
	int n, r;
	cin >> n;

	fill_n(arr, 101, -1);

	while(n--)
	{
		int me, mn;
		cin >> me >> mn;
		arr[me] = mn;
	}

	cin >> r;
	while(r--)
	{
		int l;
		cin >> l;

		bool flag = false;
		vector<int> v;
		while (l--)
		{
			int s;
			cin >> s;
			if (arr[s] == -1)
				flag = true;
			v.push_back(arr[s]);
		}

		if (flag)
		{
			cout << "YOU DIED\n";
			continue;
		}

		for (auto& i : v)
			cout << i << ' ';
		cout << endl;
	}

	return 0;
}