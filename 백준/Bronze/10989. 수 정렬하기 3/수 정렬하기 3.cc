#include <iostream>

unsigned int count[10001];

int main()
{
    std::cin.tie(NULL);
    std::cout.tie(NULL);
    std::ios_base::sync_with_stdio(false);
    
	unsigned int N, input;
	std::cin >> N;

	for (unsigned int i = 1; i <= N; ++i)
	{
		std::cin >> input;
	    ++count[input];
	}

	for (int i = 1; i <= 10000; ++i)
	{
		if (count[i] > 0)
			for (unsigned int j = 1; j <= count[i]; ++j)
				std::cout << i << '\n';
	}

	return 0;
}