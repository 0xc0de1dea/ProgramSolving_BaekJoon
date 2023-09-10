/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * FFT Source : https://infograph.tistory.com/354
 */

//import java.io.FileInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

/* Complex Class */
class Complex {
	//I'd like to access data directly not using method.
	public double re = 0;
	public double im = 0;
	
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	// return "2+3i" style string
	@Override
	public String toString() {
		return toString("i");
	}
	
	// toString("j") --> "2+3j" style
	public String toString(String im_ch) {
		String s = "";
		
		if(this.re==0 && this.im==0) {
			s = "0";
		}else if(this.re == 0) {
			s = this.im + im_ch;
		}else if(this.im == 0) {
			s = "" + this.re;
		}else if(this.im > 0) {
			s = this.re + "+" + this.im + im_ch;
		}else {
			s = "" + this.re + this.im + im_ch;
		}
		return s;
	}	
}

/* Complex operator */
class CMath {
	public static Complex add(Complex z1, Complex z2) {
		return new Complex(z1.re + z2.re, z1.im + z2.im);
	}
	
	public static Complex subtract(Complex z1, Complex z2) {
		return new Complex(z1.re - z2.re, z1.im - z2.im);
	}
	
	public static Complex multiply(Complex z1, Complex z2) {		
		return new Complex(z1.re * z2.re - z1.im * z2.im, z1.re * z2.im + z1.im * z2.re);
		
	}
	
	public static Complex divideR(Complex z, double r) {
		return new Complex(z.re / r, z.im / r);
	}
	
	public static double magnitude(Complex z) {
		return Math.sqrt(z.re * z.re + z.im * z.im);
	}
}

/* FastFourierTransform */
class FFT {
	static double log2(double a) {
		return Math.log(a) / Math.log(2);
	}
	
	/**
	 * Check the number is power of 2 or not. 
	 * @param N
	 * @return 
	 * 	0 : it is power of 2
	 *  padding_count: if it is now power of 2, required count number to be power of 2  
	 * @throws Exception 
	 */
	private static int checkPowerOfTwo(int N) throws Exception {
		int result = -1;
		
		if (N == 0) throw new Exception("Input size is zero.");
		
		double l,c,f;
		
		l=FFT.log2(N);
		c=Math.ceil(l);
		f=Math.floor(l);
		if(c==f) result =0;
		else result = (int)Math.pow(2, c)-N;
				
		return result;
	}
		
	private static Complex[] makePowerOfTwoSize(Complex[] arr, int fillCount) {		
		Complex[] newArr = new Complex[arr.length + fillCount]; //initial value is null
		System.arraycopy(arr, 0, newArr, 0, arr.length);  
		for(int i=arr.length;i<newArr.length;++i) {
			newArr[i] = new Complex(0,0);
		}
		return newArr;
	}
			
	// getReversedArr(13,4) -> {1,0,1,1}  where, 13=1101
	private static int getReversedNum(int num, int numOfBits) {		
		int i, j, k;
		
		//1. Decimal number -> Reversed Binary array
		byte a;
		int d=0;
											//num=13, numOfBits=4
		for (i=0;i<numOfBits;++i) {			// 0			1		  2			3  
			j = numOfBits - 1 - i;			// 3			2		  1			0
			k = (int)(num >> j);			// 13>>3=1		13>>2=3   13>>1=6   13>>0=13
			a = (byte)(k & 1);				// arr[3]=1		arr[2]=1  arr[1]=0  arr[0]=1
			d = d + (a<<i);
		}		
		return d; 		
	}	
			
	private static Complex[] arrange4fft(Complex[] src, int numOfBits) throws Exception{
		int i,j;
		Complex[] arrangedSrc = new Complex[src.length];
		
		for(i=0;i<src.length;++i) {
			j = FFT.getReversedNum(i, numOfBits); //{000,001,010, 011, 100, 101, 110, 111} -> {0, 4, 2, 6, 1, 5, 3, 7}
			arrangedSrc[j] = src[i];
		}
		return arrangedSrc;
	}
	
	/**
	 * Calculate W(Convolution ring)
	 * 
	 * W[k] for FFT = cos(theta) - isin(theta), where theta = (2pi*k/N)
	 * W[k] for IFFT = cos(-theta) - isin(-theta), where theta = (2pi*k/N)
	 * @param N 
	 * @param isInverse true: for IFFT, false: for FFT
	 * @return calculated W array
	 */
	private static Complex[] calculateW(int N, boolean isInverse) {
		Complex[] W = null;
		double T = 0;
		
		int N2 = N/2;
		W = new Complex[N2];		
		T = 2 * Math.PI / N;
		
		double theta = 0;
		if (isInverse) { //IFFT
			for(int i=0;i<N2;++i) {
				theta = -(T * i);
				W[i] = new Complex(Math.cos(theta), -Math.sin(theta));
			}
		}else { //FFT
			for(int i=0;i<N2;++i) {
				theta = (T * i);
				W[i] = new Complex(Math.cos(theta), -Math.sin(theta));
			}
		}			
		
		return W;
	}
	
	/**
	 * 
	 * @param X 2D array. X[0][] and X[1][] change it's role each other by the src and tgt number 
	 * @param src source index of the array X. 0 or 1
	 * @param tgt target index of the array X. 0 or 1
	 * @param s Starting index of the data in the array
	 * @param size Region size to be calculated: 2 -> 4 -> 8 -> ...
	 * @param kJump k's jumping value to use right value of W
	 * @param W Convolution ring
	 */
	private static void regionFFT(
			Complex[][] X, int src, int tgt, int s, int size, int kJump, Complex[] W) {
		
		// Xm+1[i] = Xm[i] + Xm[i+half]W[k]
		// Xm+1[i+half] = Xm[i] - Xm[i+half]W[k]
		int k=0;
		int e = s + (size/2)-1;
		Complex T=null;
		int half = (int)(size / 2);
		for(int i=s;i<=e;++i) {
			T = CMath.multiply(X[src][i+half], W[k]);
			X[tgt][i] = CMath.add(X[src][i], T);
			X[tgt][i+half] = CMath.subtract(X[src][i], T);
			k += kJump;
		}
	}
	////////////////////////////////////////////////////

	
	private static Complex[] fft_forward(Complex[] src, boolean isInverse) throws Exception {
		int N = src.length;
		
		//1. check src data size. If it's not 2^n size then make it to be 2^n size with zero padding
		int fillCount = 0;
		fillCount = checkPowerOfTwo(N);
		if(fillCount > 0) {
			src = makePowerOfTwoSize(src, fillCount);
		}else if(fillCount < 0) {
			throw new Exception("Something wrong: while calculateing filling count from the input data");
		}
		
		//2. calculate loop count: 2-> 1, 4->2, 8->3, 16->4		
		N = src.length; //input array can be changed. so, read one more time.
		int totalLoop = (int)FFT.log2(N); //it is guaranteed that N is 2^n number, therefore log2(N) will be int value. 
		
		//3. arrange src index to calculate FFT as bottom-up style
		Complex[] arrangedSrc = arrange4fft(src, totalLoop);
		
		//4. calculate W: 0 to (N/2-1)
		Complex[] W = calculateW(N, isInverse); //for fft. for ifft:calculateW(N, true)
		
		//5. use 2-dimensional array to save memory space. X[0, ] <-> X[1, ]
		Complex[][] X = new Complex[2][N];
		System.arraycopy(arrangedSrc, 0, X[0], 0, N);
				
		//6. calculate FFT by bottom-up style
		int srcIdx=0, tgtIdx=0;
		int kJump=0, regionSize =0;
		for(int curLoop=0; curLoop<totalLoop;++curLoop) {
			tgtIdx = (tgtIdx+1) % 2;
			srcIdx = (tgtIdx+1) % 2;
			regionSize = 1 << (curLoop+1);			//if N=8: 2 -> 4 -> 8
			kJump = 1 << (totalLoop - curLoop - 1);	//if N=8: 4 -> 2 -> 1
			int i=0;
			while(i<N) {
				regionFFT(X, srcIdx, tgtIdx, i, regionSize, kJump, W);
				i += regionSize;
			}
		}
		
		//7. find final output index in the X array
		int resultIdx = ((totalLoop & 1) == 0) ? 0 : 1;
		
		//8. in case of IFFT, divide N
		if(isInverse) {
			for(int i=0;i<N;++i) X[resultIdx][i] = CMath.divideR(X[resultIdx][i], N);			
		}
		
		//9. return the result as 1d array
		return X[resultIdx];
	}
		
	public static Complex[] fft(double[] src) throws Exception{
		return FFT.fft(src, -1);
	}
	
	public static Complex[] fft(double[] src, int roundDigit) throws Exception{
		Complex[] complexSrc = new Complex[src.length];
		
		for(int i=0;i<src.length;++i) complexSrc[i] = new Complex(src[i],0);
		
		Complex[] X = fft_forward(complexSrc,  false);
		
		// round up at roundDigit
		if(roundDigit >= 0) {
			double d = Math.pow(10.0, roundDigit);
			for(int i=0;i<X.length;++i) {
				X[i] = new Complex(Math.round(X[i].re * d)/d, Math.round(X[i].im * d)/d);
			}
		}		
		return X;
	}
	
	public static double[] ifft(Complex[] src) throws Exception{
		return FFT.ifft(src,-1);
	}
	
	public static double[] ifft(Complex[] src, int roundDigit) throws Exception{
		Complex[] complexArr = fft_forward(src,  true);
		
		double[] x = new double[complexArr.length];
		
		if(roundDigit >= 0) {
			double d = Math.pow(10.0, roundDigit);  // round up at roundDigit
			for(int i=0;i<x.length;++i) {
				x[i] = Math.round(complexArr[i].re * d) / d ;
			} 
		}else {
			for(int i=0;i<x.length;++i) {
				x[i] = complexArr[i].re;
			} 
		}		
		
		return x;
	}	
}


public class Main {
    public static int bigMul(double[] a, double[] b) throws Exception{
        int asz = a.length, bsz = b.length;
        int shift = (int)Math.ceil(FFT.log2(asz + bsz));

        int n = 1; n <<= shift;

        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < asz; i++) x[i] = a[asz - i - 1];
        for (int i = 0; i < bsz; i++) y[i] = b[bsz - i - 1];

        Complex[] X, Y;
        X = FFT.fft(x);
        Y = FFT.fft(y);

        Complex[] Z = new Complex[n];
        for (int i = 0; i < n; i++) Z[i] = CMath.multiply(X[i], Y[i]);

        double[] z = FFT.ifft(Z);
        for (int i = 0; i < n; i++) z[i] = Math.round(z[i]);

        int max = 0;

        for (int i = z.length - 1; i >= 0; i--) {
            int t = (int)z[i];
            max = Math.max(max, t);
        }

        return max;
    }

	public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        
        int n = in.nextInt();
        double[] a = new double[n << 1];
        double[] b = new double[n];
        
        for (int i = 0; i < n; i++) a[i] = in.nextDouble();
        for (int i = n; i < n << 1; i++) a[i] = a[i - n];
        for (int i = n - 1; i >= 0; i--) b[i] = in.nextDouble();
        
		System.out.print(bigMul(a, b));
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
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

    boolean isAlphabet(byte c){
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}