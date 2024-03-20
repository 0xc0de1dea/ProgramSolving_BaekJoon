#include <iostream>
#include <vector>
using namespace std;

vector<string> res;

void merge(vector<string>& str, int start, int mid, int end) {
    int i = start, j = mid+1, k = start;
    while (i <= mid && j <= end) {
        if (str[i].length() < str[j].length()) {
            res[k] = str[i];
            i++;
        }
        else if (str[i].length() > str[j].length()) {
            res[k] = str[j];
            j++;
        }
        else if (str[i] < str[j]) {
            res[k] = str[i];
            i++;
        }
        else {
            res[k] = str[j];
            j++;
        }
        k++;
    }
    if (i > mid) { 
        while (j <= end) {
            res[k] = str[j];
            k++; j++;
        }
    }
    else {
        while (i <= mid) {
            res[k] = str[i];
            k++; i++;
        }
    }
    for (i = start; i <= end; i++) { str[i] = res[i]; }
}

void mergeSort(vector<string>& str, int start, int end) {
    if (start < end) {
        int mid = (start+end)/2;
        mergeSort(str, start, mid);
        mergeSort(str, mid+1, end);
        merge(str, start, mid, end);
    }
}

int main() {
    cin.tie(NULL);
	cin.sync_with_stdio(false);
	int n, i;
	cin >> n;
	vector<string> str(n);
	res.resize(n);
	for (i = 0; i < n; i++) {
	    cin >> str[i];
	}
	mergeSort(str, 0, n-1);
	for (i = 0; i < n; i++) {
	    if (i && str[i-1] == str[i]) { continue; }
	    cout << str[i] << '\n';
	}
}