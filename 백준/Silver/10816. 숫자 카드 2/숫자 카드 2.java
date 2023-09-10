import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
		int N = in.nextInt();
		int[] arr = new int[N];

		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		Arrays.sort(arr);
		int M = in.nextInt();
		
		for(int i = 0; i < M; i++) {
			int key = in.nextInt();
			sb.append(upperBound(arr, key) - lowerBound(arr, key)).append(' ');
		}
        
		System.out.println(sb);
	}
 
	private static int lowerBound(int[] arr, int key) {
		int lo = 0; 
		int hi = arr.length;
 
		while (lo < hi) {
			int mid = (lo + hi) / 2;

			if (key <= arr[mid]) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
        
		return lo;
	}

	private static int upperBound(int[] arr, int key) {
		int lo = 0;
		int hi = arr.length; 
 
		while (lo < hi) {
			int mid = (lo + hi) / 2;
 
			if (key < arr[mid]) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
 
		return lo;
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}