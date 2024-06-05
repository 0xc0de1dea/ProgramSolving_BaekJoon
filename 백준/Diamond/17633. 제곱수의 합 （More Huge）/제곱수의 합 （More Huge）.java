import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * mt19937 Random Algorithm Author : datquocnguyen
 */

//import java.io.FileInputStream;

@FunctionalInterface
interface LamdaFunction<T> {
    long go(long x, long y);
}

class Euler {
    long phi(long n){
        long ret = n;

        for (long i = 2; i * i <= n; i++){
            if (n % i == 0){
                while (n % i == 0) n /= i;
                ret -= ret / i;
            }
        }
        if (n > 1) ret -= ret / n;

        return ret;
    }

    long phiMul(ArrayList<Long> primeFactor){
        long ret = primeFactor.get(0); primeFactor.add(0L);
        long t = 1L;
        
        for (int i = 1; i < primeFactor.size(); i++){
            if (primeFactor.get(i) == primeFactor.get(i - 1)) t *= primeFactor.get(i);
            else { ret *= phi(t); t = 1; }
        }

        return ret;
    }

    long getPhi(long n, ArrayList<Long> primeFactor, boolean phi){
        if (phi) return phiMul(primeFactor);
        else {
            long ret = n / primeFactor.get(0) * (primeFactor.get(0) - 1);

            for (int i = 1; i < primeFactor.size(); i++){
                if (Long.compare(primeFactor.get(i), primeFactor.get(i - 1)) != 0){
                    ret /= primeFactor.get(i);
                    ret *= primeFactor.get(i) - 1;
                }
            }

            return ret;
        }
    }

    long getDivCnt(ArrayList<Long> primeFactor){
        long ret = 1, exp = 2; primeFactor.add(0L);

        for (int i = 1; i < primeFactor.size(); i++){
            if (Long.compare(primeFactor.get(i), primeFactor.get(i - 1)) == 0){
                exp++;
            }
            else {
                ret *= exp; exp = 2;
            }
        }

        return ret;
    }
}

strictfp class MT19937 extends java.util.Random implements Serializable, Cloneable {
    // Serialization
    private static final long serialVersionUID = -4035832775130174188L; // locked as of Version 15

    // Period parameters
    private static final int N = 624;
    private static final int M = 397;
    private static final int MATRIX_A = 0x9908b0df; // private static final * constant vector a
    private static final int UPPER_MASK = 0x80000000; // most significant w-r bits
    private static final int LOWER_MASK = 0x7fffffff; // least significant r bits

    // Tempering parameters
    private static final int TEMPERING_MASK_B = 0x9d2c5680;
    private static final int TEMPERING_MASK_C = 0xefc60000;

    private int mt[]; // the array for the state vector
    private int mti; // mti==N+1 means mt[N] is not initialized
    private int mag01[];

    // a good initial seed (of int size, though stored in a long)
    // private static final long GOOD_SEED = 4357;

    /*
     * implemented here because there's a bug in Random's implementation of the Gaussian code
     * (divide by zero, and log(0), ugh!), yet its gaussian variables are private so we can't access
     * them here. :-(
     */

    private double __nextNextGaussian;
    private boolean __haveNextNextGaussian;

    /* We're overriding all internal data, to my knowledge, so this should be okay */
    public Object clone()
    {
        try {
            MT19937 f = (MT19937) (super.clone());
            f.mt = (int[]) (mt.clone());
            f.mag01 = (int[]) (mag01.clone());
            return f;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        } // should never happen
    }

    public boolean stateEquals(Object o)
    {
        if (o == this)
            return true;
        if (o == null || !(o instanceof MT19937))
            return false;
        MT19937 other = (MT19937) o;
        if (mti != other.mti)
            return false;
        for (int x = 0; x < mag01.length; x++)
            if (mag01[x] != other.mag01[x])
                return false;
        for (int x = 0; x < mt.length; x++)
            if (mt[x] != other.mt[x])
                return false;
        return true;
    }

    /** Reads the entire state of the MT19937 RNG from the stream */
    public void readState(DataInputStream stream)
        throws IOException
    {
        int len = mt.length;
        for (int x = 0; x < len; x++)
            mt[x] = stream.readInt();

        len = mag01.length;
        for (int x = 0; x < len; x++)
            mag01[x] = stream.readInt();

        mti = stream.readInt();
        __nextNextGaussian = stream.readDouble();
        __haveNextNextGaussian = stream.readBoolean();
    }

    /** Writes the entire state of the MT19937 RNG to the stream */
    public void writeState(DataOutputStream stream)
        throws IOException
    {
        int len = mt.length;
        for (int x = 0; x < len; x++)
            stream.writeInt(mt[x]);

        len = mag01.length;
        for (int x = 0; x < len; x++)
            stream.writeInt(mag01[x]);

        stream.writeInt(mti);
        stream.writeDouble(__nextNextGaussian);
        stream.writeBoolean(__haveNextNextGaussian);
    }

    /**
     * Constructor using the default seed.
     */
    public MT19937()
    {
        this(System.currentTimeMillis());
    }

    /**
     * Constructor using a given seed. Though you pass this seed in as a long, it's best to make
     * sure it's actually an integer.
     */
    public MT19937(long seed)
    {
        super(seed); /* just in case */
        setSeed(seed);
    }

    /**
     * Constructor using an array of integers as seed. Your array must have a non-zero length. Only
     * the first 624 integers in the array are used; if the array is shorter than this then integers
     * are repeatedly used in a wrap-around fashion.
     */
    public MT19937(int[] array)
    {
        super(System.currentTimeMillis()); /* pick something at random just in case */
        setSeed(array);
    }

    /**
     * Initalize the pseudo random number generator. Don't pass in a long that's bigger than an int
     * (Mersenne Twister only uses the first 32 bits for its seed).
     */

    synchronized public void setSeed(long seed)
    {
        // it's always good style to call super
        super.setSeed(seed);

        // Due to a bug in java.util.Random clear up to 1.2, we're
        // doing our own Gaussian variable.
        __haveNextNextGaussian = false;

        mt = new int[N];

        mag01 = new int[2];
        mag01[0] = 0x0;
        mag01[1] = MATRIX_A;

        mt[0] = (int) (seed & 0xffffffff);
        mt[0] = (int) seed;
        for (mti = 1; mti < N; mti++) {
            mt[mti] = (1812433253 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30)) + mti);
            /* See Knuth TAOCP Vol2. 3rd Ed. P.106 for multiplier. */
            /* In the previous versions, MSBs of the seed affect */
            /* only MSBs of the array mt[]. */
            /* 2002/01/09 modified by Makoto Matsumoto */
            // mt[mti] &= 0xffffffff;
            /* for >32 bit machines */
        }
    }

    /**
     * Sets the seed of the MT19937 using an array of integers. Your array must have a
     * non-zero length. Only the first 624 integers in the array are used; if the array is shorter
     * than this then integers are repeatedly used in a wrap-around fashion.
     */

    synchronized public void setSeed(int[] array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("Array length must be greater than zero");
        int i, j, k;
        setSeed(19650218);
        i = 1;
        j = 0;
        k = (N > array.length ? N : array.length);
        for (; k != 0; k--) {
            mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * 1664525)) + array[j] + j; /*
                                                                                            * non
                                                                                            * linear
                                                                                            */
            // mt[i] &= 0xffffffff; /* for WORDSIZE > 32 machines */
            i++;
            j++;
            if (i >= N) {
                mt[0] = mt[N - 1];
                i = 1;
            }
            if (j >= array.length)
                j = 0;
        }
        for (k = N - 1; k != 0; k--) {
            mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * 1566083941)) - i; /* non linear */
            // mt[i] &= 0xffffffff; /* for WORDSIZE > 32 machines */
            i++;
            if (i >= N) {
                mt[0] = mt[N - 1];
                i = 1;
            }
        }
        mt[0] = 0x80000000; /* MSB is 1; assuring non-zero initial array */
    }

    /**
     * Returns an integer with <i>bits</i> bits filled with a random number.
     */
    synchronized protected int next(int bits)
    {
        int y;

        if (mti >= N) // generate N words at one time
        {
            int kk;
            final int[] mt = this.mt; // locals are slightly faster
            final int[] mag01 = this.mag01; // locals are slightly faster

            for (kk = 0; kk < N - M; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
            }
            for (; kk < N - 1; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
            }
            y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
            mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

            mti = 0;
        }

        y = mt[mti++];
        y ^= y >>> 11; // TEMPERING_SHIFT_U(y)
        y ^= (y << 7) & TEMPERING_MASK_B; // TEMPERING_SHIFT_S(y)
        y ^= (y << 15) & TEMPERING_MASK_C; // TEMPERING_SHIFT_T(y)
        y ^= (y >>> 18); // TEMPERING_SHIFT_L(y)

        return y >>> (32 - bits); // hope that's right!
    }

    /*
     * If you've got a truly old version of Java, you can omit these two next methods.
     */

    private synchronized void writeObject(ObjectOutputStream out)
        throws IOException
    {
        // just so we're synchronized.
        out.defaultWriteObject();
    }

    private synchronized void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        // just so we're synchronized.
        in.defaultReadObject();
    }

    /**
     * This method is missing from jdk 1.0.x and below. JDK 1.1 includes this for us, but what the
     * heck.
     */
    public boolean nextBoolean()
    {
        return next(1) != 0;
    }

    /**
     * This generates a coin flip with a probability <tt>probability</tt> of returning true, else
     * returning false. <tt>probability</tt> must be between 0.0 and 1.0, inclusive. Not as precise
     * a random real event as nextBoolean(double), but twice as fast. To explicitly use this,
     * remember you may need to cast to float first.
     */

    public boolean nextBoolean(float probability)
    {
        if (probability < 0.0f || probability > 1.0f)
            throw new IllegalArgumentException("probability must be between 0.0 and 1.0 inclusive.");
        if (probability == 0.0f)
            return false; // fix half-open issues
        else if (probability == 1.0f)
            return true; // fix half-open issues
        return nextFloat() < probability;
    }

    /**
     * This generates a coin flip with a probability <tt>probability</tt> of returning true, else
     * returning false. <tt>probability</tt> must be between 0.0 and 1.0, inclusive.
     */

    public boolean nextBoolean(double probability)
    {
        if (probability < 0.0 || probability > 1.0)
            throw new IllegalArgumentException("probability must be between 0.0 and 1.0 inclusive.");
        if (probability == 0.0)
            return false; // fix half-open issues
        else if (probability == 1.0)
            return true; // fix half-open issues
        return nextDouble() < probability;
    }

    /**
     * This method is missing from JDK 1.1 and below. JDK 1.2 includes this for us, but what the
     * heck.
     */

    public int nextInt(int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive, got: " + n);

        if ((n & -n) == n)
            return (int) ((n * (long) next(31)) >> 31);

        int bits, val;
        do {
            bits = next(31);
            val = bits % n;
        }
        while (bits - val + (n - 1) < 0);
        return val;
    }

    /**
     * This method is for completness' sake. Returns a long drawn uniformly from 0 to n-1. Suffice
     * it to say, n must be > 0, or an IllegalArgumentException is raised.
     */

    public long nextLong(long n)
    {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive, got: " + n);

        long bits, val;
        do {
            bits = (nextLong() >>> 1);
            val = bits % n;
        }
        while (bits - val + (n - 1) < 0);
        return val;
    }

    /**
     * A bug fix for versions of JDK 1.1 and below. JDK 1.2 fixes this for us, but what the heck.
     */
    public double nextDouble()
    {
        return (((long) next(26) << 27) + next(27)) / (double) (1L << 53);
    }

    /**
     * Returns a double in the range from 0.0 to 1.0, possibly inclusive of 0.0 and 1.0 themselves.
     * Thus:
     * 
     * <p>
     * <table border=0>
     * <th>
     * <td>Expression
     * <td>Interval
     * <tr>
     * <td>nextDouble(false, false)
     * <td>(0.0, 1.0)
     * <tr>
     * <td>nextDouble(true, false)
     * <td>[0.0, 1.0)
     * <tr>
     * <td>nextDouble(false, true)
     * <td>(0.0, 1.0]
     * <tr>
     * <td>nextDouble(true, true)
     * <td>[0.0, 1.0]
     * </table>
     * 
     * <p>
     * This version preserves all possible random values in the double range.
     */
    public double nextDouble(boolean includeZero, boolean includeOne)
    {
        double d = 0.0;
        do {
            d = nextDouble(); // grab a value, initially from half-open [0.0, 1.0)
            if (includeOne && nextBoolean())
                d += 1.0; // if includeOne, with 1/2 probability, push to [1.0, 2.0)
        }
        while ((d > 1.0) || // everything above 1.0 is always invalid
                (!includeZero && d == 0.0)); // if we're not including zero, 0.0 is invalid
        return d;
    }

    /**
     * A bug fix for versions of JDK 1.1 and below. JDK 1.2 fixes this for us, but what the heck.
     */

    public float nextFloat()
    {
        return next(24) / ((float) (1 << 24));
    }

    /**
     * Returns a float in the range from 0.0f to 1.0f, possibly inclusive of 0.0f and 1.0f
     * themselves. Thus:
     * 
     * <p>
     * <table border=0>
     * <th>
     * <td>Expression
     * <td>Interval
     * <tr>
     * <td>nextFloat(false, false)
     * <td>(0.0f, 1.0f)
     * <tr>
     * <td>nextFloat(true, false)
     * <td>[0.0f, 1.0f)
     * <tr>
     * <td>nextFloat(false, true)
     * <td>(0.0f, 1.0f]
     * <tr>
     * <td>nextFloat(true, true)
     * <td>[0.0f, 1.0f]
     * </table>
     * 
     * <p>
     * This version preserves all possible random values in the float range.
     */
    public float nextFloat(boolean includeZero, boolean includeOne)
    {
        float d = 0.0f;
        do {
            d = nextFloat(); // grab a value, initially from half-open [0.0f, 1.0f)
            if (includeOne && nextBoolean())
                d += 1.0f; // if includeOne, with 1/2 probability, push to [1.0f, 2.0f)
        }
        while ((d > 1.0f) || // everything above 1.0f is always invalid
                (!includeZero && d == 0.0f)); // if we're not including zero, 0.0f is invalid
        return d;
    }

    /**
     * A bug fix for all versions of the JDK. The JDK appears to use all four bytes in an integer as
     * independent byte values! Totally wrong. I've submitted a bug report.
     */

    public void nextBytes(byte[] bytes)
    {
        for (int x = 0; x < bytes.length; x++)
            bytes[x] = (byte) next(8);
    }

    /** For completeness' sake, though it's not in java.util.Random. */

    public char nextChar()
    {
        // chars are 16-bit UniCode values
        return (char) (next(16));
    }

    /** For completeness' sake, though it's not in java.util.Random. */

    public short nextShort()
    {
        return (short) (next(16));
    }

    /** For completeness' sake, though it's not in java.util.Random. */

    public byte nextByte()
    {
        return (byte) (next(8));
    }

    /**
     * A bug fix for all JDK code including 1.2. nextGaussian can theoretically ask for the log of 0
     * and divide it by 0! See Java bug <a
     * href="http://developer.java.sun.com/developer/bugParade/bugs/4254501.html">
     * http://developer.java.sun.com/developer/bugParade/bugs/4254501.html</a>
     */

    synchronized public double nextGaussian()
    {
        if (__haveNextNextGaussian) {
            __haveNextNextGaussian = false;
            return __nextNextGaussian;
        }
        else {
            double v1, v2, s;
            do {
                v1 = 2 * nextDouble() - 1; // between -1.0 and 1.0
                v2 = 2 * nextDouble() - 1; // between -1.0 and 1.0
                s = v1 * v1 + v2 * v2;
            }
            while (s >= 1 || s == 0);
            double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s) / s);
            __nextNextGaussian = v2 * multiplier;
            __haveNextNextGaussian = true;
            return v1 * multiplier;
        }
    }
}

class MillerRabin {
    long add(long x, long y, long mod){
        x %= mod; y %= mod;
        return (x >= mod - y ? x - (mod - y) : x + y);
    }

    long mul(long x, long y, long mod){
        /*long ret = x * y - mod * (long)(1. / mod * x * y);
        return ret + (ret < 0 ? 1 : 0) * mod - (ret >= mod ? 1 : 0) * mod;*/

        /*BigInteger a = new BigInteger(Long.toString(x));
        BigInteger b = new BigInteger(Long.toString(y));
        BigInteger m = new BigInteger(Long.toString(mod));
        return a.multiply(b).mod(m).longValue();*/

        long ret = 0; x %= mod; y %= mod;

        while (y > 0){
            if ((y & 1) == 1) ret = add(ret, x, mod);
            x = add(x, x, mod); y >>= 1;
        }

        return ret;
    }

    long pow(long x, long y, long mod){
        long ret = 1; x %= mod;

        while (y > 0){
            if ((y & 1) == 1) ret = mul(ret, x, mod);
            x = mul(x, x, mod); y >>= 1;
        }

        return ret;
    }

    boolean check(long n, long a){
        if (n % a == 0) return true;

        long d = n - 1;
        while (d % 2 == 0){
            if (pow(a, d, n) == n - 1) return true;
            d >>= 1;
        }

        long ret = pow(a, d, n);
        return ret == n - 1 || ret == 1;
    }

    long[] a1 = new long[] { 2, 7, 61 };
    long[] a2 = new long[] { 2, 325, 9375, 28178, 450775, 9780504, 1795265022 };

    boolean isPrime(long n){
        if (n == 2 || n == 3 || n == 5 || n == 7) return true;
        if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0) return false;
        if (n < 11 * 11) return n > 1 ? true : false;

        if (n < 1L << 32){ for (long t : a1) if (n > t && !check(n, t)) return false; }
        else { for (long t : a2) if (n > t && !check(n, t)) return false; }

        return true;
    }
}

class PollardRho extends MillerRabin {
    ArrayList<Long> primeFactor = new ArrayList<>();

    void findFactor(long n){
        if (n == 1) return;
        if ((~n & 1) == 1) { primeFactor.add(2L); findFactor(n >> 1); return; }
        if (isPrime(n)) { primeFactor.add(n); return; }

        long a = 0xDEADBEEF, b = 0xDEADBEEF, c = 1, g = n;
        LamdaFunction<Long> find = (x, c0) -> ((mul(x, x, n) + c0) % n);
        LamdaFunction<Long> gcd = (x, y) -> { long r; while (y != 0){ r = x % y; x = y; y = r; } return x; };
        MT19937 rand = new MT19937();
        do {
            if (g == n){
                a = b = rand.nextLong(n - 3) + 2;
                c = rand.nextLong(20) + 1;
            }
            a = find.go(a, c); b = find.go(find.go(b, c), c); g = gcd.go(Math.abs(a - b), n);
        } while (g == 1);

        findFactor(g); findFactor(n / g);
    }

    ArrayList<Long> factorize(long n){
        findFactor(n);
        Collections.sort(primeFactor);

        return primeFactor;
    }
}

class Miller_Rabin{
    public long gcd(long n, long m){
        if(n==0) return (m>0)?m:-m;
        return gcd(m%n, n);
    }
    public long addmod(long a, long b, long mod){
        return a>=mod-b ? a+(b-mod) : a+b;
    }
    public long mulmod(long a, long b, long mod){
        long x = 0;
        while(b>0){
            if((b&1)>0) x = addmod(x,a,mod);
            a = addmod(a,a,mod);
            b>>=1;
        }
        return x;
    }
    public long powmod(long a, long b, long mod){
        long x = 1;
        while(b>0){
            if((b&1)>0) x = mulmod(x,a,mod);
            a = mulmod(a,a,mod);
            b>>=1;
        }
        return x;
    }
    public boolean miller_rabin(long x, long n){
        if(x%n==0) return false;
        long d = x-1;
        while((d&1)==0){
            if(powmod(n,d,x)==x-1)
                return true;
            d>>=1;
        }
        long tmp = powmod(n,d,x);
        return tmp == 1 || tmp == x-1;
    }
    public boolean isPrime(long n){
        if(n==1) return false;
        long[] li = new long[]{2,3,5,7,11,13,17,19,23,29,31,37};
        for(long a:li){
            if(n==a) return true;
            if(n>40 && !miller_rabin(n,a)) return false;
        }
        if(n<=40) return false;
        return true;
    }
}

class Pollard_rho{
    Miller_Rabin m = new Miller_Rabin();
    public long g(long x, long c, long mod){
        return m.addmod(m.mulmod(x,x,mod),c,mod);
    }
    public void rec(long n, ArrayList<Long> arr){
        if(n==1) return;
        if(n%2==0){
            arr.add(2L);
            rec(n/2,arr);
            return;
        }
        if(m.isPrime(n)){
            arr.add(n); return;
        }
        long a=1,b=1,c=1,d=n;
        do{
            if(d==n){
                a=b=(long)(Math.random()*(n-3))+2;
                c=(long)(Math.random()*19)+1;
            }
            a=g(a,c,n); b=g(g(b,c,n),c,n);
            d = m.gcd(a-b,n);
        }while(d==1);
        rec(d,arr);
        rec(n/d,arr);
    }
    public ArrayList<Long> factorize(long n){
        ArrayList<Long> arr = new ArrayList<>();
        rec(n,arr);
        // HashMap<Long,Long> m = new HashMap<>();
        // for(long d:arr)
        //     m.put(d,m.getOrDefault(d,0L)+1);
        return arr;
    }
}

public class Main {
    static long add(long x, long y, long mod){
        x %= mod; y %= mod;
        return (x >= mod - y ? x - (mod - y) : x + y);
    }

    static long mul(long x, long y, long mod){
        long ret = 0; x %= mod; y %= mod;

        while (y > 0){
            if ((y & 1) == 1) ret = add(ret, x, mod);
            x = add(x, x, mod); y >>= 1;
        }

        return ret;
    }

    static long pow(long x, long y, long mod){
        long ret = 1; x %= mod;

        while (y > 0){
            if ((y & 1) == 1) ret = mul(ret, x, mod);
            x = mul(x, x, mod); y >>= 1;
        }

        return ret;
    }

    public static long sqrt(long n) {
        ArrayList<Long> L = new PollardRho().factorize(n);
        HashMap<Long, Long> d = new HashMap<>();

        for (long i : L) {
            d.put(i, d.getOrDefault(i, 0L) + 1);
        }

        long res = 1;
        
        for (long i : d.keySet()) {
            res *= pow(i, d.get(i) / 2, Long.MAX_VALUE);
        }

        return res;
    }

    // static long tonelliShanks2(long n, long p){
    //     long s = 0;
    //     long q = p - 1;

    //     while ((q & 1) == 0){
    //         q /= 2;
    //         ++s;
    //     }

    //     if (s == 1){
    //         long r = pow(n, (p + 1) / 4, p);
            
    //         if (mul(r, r, p) == n) {
    //             return r;
    //         }

    //         return 1;
    //     }

    //     long z = 1;

    //     while (pow(++z, (p - 1) / 2, p) != p - 1){
    //         //if (z <= 10){
    //             // System.out.println();
    //             // System.out.println(z + " " + (p - 1) / 2 + " " + p);
    //             // System.out.println(pow(z, (p - 1) / 2, p) + " " + (p - 1));
    //         //}
    //     }

    //     long c = pow(z, q, p);
    //     long r = pow(n, (q + 1) / 2, p);
    //     long t = pow(n, q, p);
    //     long m = s;

    //     while (t != 1){
    //         long tt = t;
    //         long i = 0;

    //         while (tt != 1){
    //             tt = mul(tt, tt, p);
    //             ++i;

    //             if (i == m){
    //                 return 1;
    //             }
    //         }

    //         long b = pow(c, pow(2, m - i - 1, p - 1), p);
    //         long b2 = mul(b, b, p);
    //         r = mul(r, b, p);
    //         t = mul(t, b2, p);
    //         c = b2;
    //         m = i;
    //     }

    //     if (mul(r, r, p) == n){
    //         return r;
    //     }

    //     return 1;
    // }

    // static long[] cornacchia4(long n){
    //     BigInteger r1 = new BigInteger(String.valueOf(n));
    //     BigInteger r2 = new BigInteger(String.valueOf(tonelliShanks2(n - 1, n)));

    //     System.out.println();
    //     System.out.println(r1 + " " + r2);

    //     while (r1.multiply(r1).compareTo(new BigInteger(String.valueOf(n))) != -1){
    //         BigInteger t = r1.mod(r2);
    //         r1 = new BigInteger(r2.toString());
    //         r2 = new BigInteger(t.toString());
    //     }

    //     long x = r1.longValue();
    //     long y = (long)Math.sqrt(n - r1.longValue() * r1.longValue());

    //     return new long[] { x, y };
    // }

    // static long[] cornacchia3(long n){
    //     BigInteger r1 = new BigInteger(String.valueOf(n));
    //     BigInteger r2;
    //     BigInteger n2 = new BigInteger(String.valueOf(n)).divide(BigInteger.TWO);

    //     for (r2 = BigInteger.ONE; r2.compareTo(n2) != 1; r2 = r2.add(BigInteger.ONE)){
    //         if ((r2.multiply(r2)).mod(r1).equals(r1.subtract(BigInteger.ONE))){
    //             break;
    //         }
    //     }

    //     System.out.println();
    //     System.out.println(r1 + " " + r2);

    //     while (r1.multiply(r1).compareTo(new BigInteger(String.valueOf(n))) != -1){
    //         BigInteger t = r1.mod(r2);
    //         r1 = new BigInteger(r2.toString());
    //         r2 = new BigInteger(t.toString());
    //     }

    //     long x = r1.longValue();
    //     long y = (long)Math.sqrt(n - r1.longValue() * r1.longValue());

    //     return new long[] { x, y };
    // }

    // static long[] cornacchia2(long n){
    //     long r1 = n;
    //     long r2;

    //     for (r2 = 1; r2 <= n / 2; r2++){
    //         if ((r2 * r2) % r1 == r1 - 1){
    //             break;
    //         }
    //     }

    //     System.out.println();
    //     System.out.println(r1 + " " + r2);

    //     while (r1 * r1 >= n){
    //         long tmp = r1 % r2;
    //         r1 = r2;
    //         r2 = tmp;
    //     }

    //     long x = r1;
    //     long y = (long)Math.sqrt(n - x * x);

    //     return new long[] { x, y };
    // }

    // public static long modPow(long base, long exp, long mod) {
    //     long result = 1;
    //     while (exp > 0) {
    //         if (exp % 2 == 1) {
    //             result = (result * base) % mod;
    //         }
    //         base = (base * base) % mod;
    //         exp /= 2;
    //     }
    //     return result;
    // }

    // public static long modMul(long a, long b, long mod) {
    //     return (a * b) % mod;
    // }

    // static class Pair<A, B> {
    //     public final A first;
    //     public final B second;

    //     public Pair(A first, B second) {
    //         this.first = first;
    //         this.second = second;
    //     }
    // }

    public static BigInteger tonelli(long p) {
        long q = p - 1;
        long s = 0;
        while (q % 2 == 0) {
            q /= 2;
            s++;
        }
        
        BigInteger bp = new BigInteger(String.valueOf(p));
        BigInteger z = new BigInteger(String.valueOf((long) (Math.random() * (p - 2) + 2)));
        while (z.modPow((bp.subtract(BigInteger.ONE)).divide(new BigInteger("2")), bp).equals(BigInteger.ONE)) {
            z = new BigInteger(String.valueOf((long) (Math.random() * (p - 2) + 2)));
        }
        BigInteger m = new BigInteger(String.valueOf(s));
        BigInteger c = new BigInteger(String.valueOf(pow(z.longValue(), q, p)));
        BigInteger t = new BigInteger(String.valueOf(pow(p - 1, q, p)));
        BigInteger r = new BigInteger(String.valueOf(pow(p - 1, (q + 1) / 2, p)));

        if (t.equals(BigInteger.ZERO)) return BigInteger.ZERO;
        while (!t.equals(BigInteger.ONE) && !t.equals(BigInteger.ZERO)) {
            BigInteger tt = t;
            BigInteger i = BigInteger.ZERO;
            while (!t.mod(bp).equals(BigInteger.ONE)) {
                t = t.modPow(new BigInteger("2"), bp);
                i = i.add(BigInteger.ONE);
            }
            BigInteger b = c.modPow(new BigInteger("2").modPow(m.subtract(i).subtract(BigInteger.ONE), bp), bp);
            m = i;
            c = b.modPow(new BigInteger("2"), bp);
            t = (tt.multiply(c)).mod(bp);
            r = (r.multiply(b)).mod(bp);
        }
        return r;
    }

    public static BigInteger tonelli2(long p) {
        long q = p - 1;
        long s = 0;
        while (q % 2 == 0) {
            q /= 2;
            s++;
        }
        long z = (long) (Math.random() * (p - 2) + 2);
        while (pow(z, (p - 1) / 2, p) == 1) {
            z = (long) (Math.random() * (p - 2) + 2);
        }
        long m = s;
        long c = pow(z, q, p);
        long t = pow(p - 1, q, p);
        long r = pow(p - 1, (q + 1) / 2, p);

        if (t == 0) return BigInteger.ZERO;
        while (t != 1 && t != 0) {
            long tt = t;
            long i = 0;
            while (t % p != 1) {
                t = pow(t, 2, p);
                i++;
            }
            long b = pow(c, pow(2, m - i - 1, p), p);
            m = i;
            c = pow(b, 2, p);
            t = (tt * c) % p;
            r = (r * b) % p;
        }
        return new BigInteger(String.valueOf(r));
    }

    public static long cornacchia(long p) {
        if (p % 4 == 3) return -1;
        if (p == 2) return 1;

        BigInteger r = tonelli(p);
        BigInteger rr = new BigInteger(String.valueOf(p));
        BigInteger pp = new BigInteger(String.valueOf(p));
        
        while (r.multiply(r).compareTo(pp) == 1) {
            rr = rr.mod(r);
            if (rr.multiply(rr).compareTo(pp) == -1) return rr.longValue();
            r = r.mod(rr);
        }
        
        return r.longValue();
    }

    // public static int counting(long n){
    //     ArrayList<Long> list = new PollardRho().factorize(n);
    //     HashMap<Long, Long> factMap = new HashMap<>();

    //     for (long i : list){
    //         factMap.put(i, factMap.getOrDefault(i, 0L) + 1);
    //     }

    //     boolean flag = true;
    //     boolean flag2 = true;

    //     for (long val : factMap.keySet()){
    //         if (factMap.get(val) % 2 != 0) flag = false;
    //         if (val % 4 == 3 && factMap.get(val) % 2 != 0) flag2 = false;
    //     }

    //     if (flag) return 1;
    //     if (flag2) return 2;
    //     while (n % 4 == 0) n /= 4;
    //     if (n % 8 != 7) return 3;
    //     return 4;
    // }

    public static int counting(long n){
        if ((long)Math.sqrt(n) * (long)Math.sqrt(n) == n) return 1;
        else {
            while (n % 4 == 0) n /= 4;
            if (n % 8 == 7) return 4;
            else {
                ArrayList<Long> primeFactor = new ArrayList<>();
                primeFactor = new MillerRabin().isPrime(n) ? new ArrayList<>(Arrays.asList(n)) : new PollardRho().factorize(n);
                primeFactor.add(0L);
                int exp = 1;

                for (int i = 1; i < primeFactor.size(); i++){
                    if (Long.compare(primeFactor.get(i), primeFactor.get(i - 1)) != 0){
                        if (primeFactor.get(i - 1) % 4 == 3 && (exp & 1) == 1){
                            return 3;
                        }
                        else exp = 1;
                    }
                    else exp++;
                }

                return 2;
            }
        }
    }

    public static long[] get1(long n){
        return new long[] { (long)Math.sqrt(n) };
    }

    public static long[] get2(long n){
        ArrayList<Long> list = new PollardRho().factorize(n);
        TreeMap<Long, Long> factMap = new TreeMap<>();

        for (long i : list){
            factMap.put(i, factMap.getOrDefault(i, 0L) + 1);
        }

        long mul = 1;
        ArrayList<Long> list2 = new ArrayList<>();

        for (long key : factMap.keySet()){
            mul *= pow(key, factMap.get(key) / 2, Long.MAX_VALUE);

            if (factMap.get(key) % 2 != 0){
                list2.add(key);
            }
        }

        long[] ans = { 1, 0 };

        for (long val : list2){
            long k = cornacchia(val);
            long[] res = { k, sqrt(val - k * k) };
            long a = ans[0], b = ans[1];
            long c = res[0], d = res[1];
            ans[0] = a * d + b * c;
            ans[1] = Math.abs(a * c - b * d);
        }

        ans[0] *= mul;
        ans[1] *= mul;

        return ans;
    }

    public static long[] get3(long n){
        ArrayList<Long> list = new PollardRho().factorize(n);
        HashMap<Long, Long> factMap = new HashMap<>();

        for (long i : list){
            factMap.put(i, factMap.getOrDefault(i, 0L) + 1);
        }

        long mul = 1;
        long newN = 1;

        for (long val : factMap.keySet()){
            mul *= pow(val, factMap.get(val) / 2, Long.MAX_VALUE);
            newN *= pow(val, factMap.get(val) % 2, Long.MAX_VALUE);
        }

        long t = 1;

        while (counting(newN - t * t) != 2){
            t++;
        }

        long[] ans = get2(newN - t * t);
        ans[0] *= mul;
        ans[1] *= mul;
        ans = Arrays.copyOf(ans, ans.length + 1);
        ans[2] = t * mul;

        return ans;
    }

    public static long[] get4(long n){
        int a = 0;

        while (n % 4 == 0){
            a++;
            n /= 4;
        }

        long[] ans = get3(n - 1);
        long twoPowA = pow(2, a, Long.MAX_VALUE);

        for (int i = 0; i < ans.length; i++){
            ans[i] *= twoPowA;
        }

        ans = Arrays.copyOf(ans, ans.length + 1);
        ans[ans.length - 1] = twoPowA;

        return ans;
    }

    public static long quotient(BigInteger a, BigInteger b){
        BigInteger tmp = a.mod(b);

        if (tmp.compareTo(BigInteger.ZERO) == -1) tmp = tmp.add(b);
        if (b.compareTo(new BigInteger("2").multiply(tmp)) == -1) tmp = tmp.subtract(b);

        return (a.subtract(tmp).divide(b)).longValue();
    }

    public static long[] div(long n1, long n2, long m1, long m2){
        BigInteger bn1 = new BigInteger(String.valueOf(n1));
        BigInteger bn2 = new BigInteger(String.valueOf(n2));
        BigInteger bm1 = new BigInteger(String.valueOf(m1));
        BigInteger bm2 = new BigInteger(String.valueOf(m2));

        BigInteger tmp = (bm1.multiply(bm1)).add((bm2.multiply(bm2)));
        long t1 = quotient((bn1.multiply(bm1)).add((bn2.multiply(bm2))), tmp);
        long t2 = quotient((bn2.multiply(bm1)).subtract((bn1.multiply(bm2))), tmp);

        return new long[] { t1, t2 };
    }

    public static long[] mmod(long n1, long n2, long m1, long m2){
        long[] tmp = div(n1, n2, m1, m2);
        long t1 = tmp[0], t2 = tmp[1];
        long r1 = n1 - t1 * m1 + t2 * m2;
        long r2 = n2 - t1 * m2 - t2 * m1;

        return new long[] { r1, r2 };
    }

    public static long[] ggcd(long n1, long n2, long m1, long m2){
        while (m1 != 0 || m2 != 0){
            long[] tmp = mmod(n1, n2, m1, m2);
            n1 = m1; n2 = m2;
            m1 = tmp[0]; m2 = tmp[1];
        }

        return new long[] { n1, n2 };
    }

    public static long residue(long n){
        long tmp = n / 4;

        for (long i = 2; ; i++){
            long t1 = pow(i, tmp, n);
            long t2 = mul(t1, t1, n);

            if (t2 == n - 1) return t1;
        }
    }

    public static long[] p2p(long n){
        if (n == 2) return new long[] { 1, 1 };

        long tmp = residue(n);
        long[] res = ggcd(n, 0, tmp, 1);
        res[0] = Math.abs(res[0]);
        res[1] = Math.abs(res[1]);

        return res;
    }

    public static long upperbound(ArrayList<Long> list, int left, int right, long target){
        while (left < right){
            int mid = left + right >> 1;

            if (list.get(mid) <= target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static long lowerbound(ArrayList<Long> list, int left, int right, long target){
        while (left < right){
            int mid = left + right >> 1;

            if (list.get(mid) < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static boolean chk2(long n){
        ArrayList<Long> list = new PollardRho().factorize(n);
        Collections.sort(list);

        boolean chk2 = true;

        for (long i : list){
            if (((i & 3) == 3) && (((upperbound(list, 0, list.size(), i) - lowerbound(list, 0, list.size(), i)) & 1) == 1)){
                chk2 = false;
                break;
            }
        }

        return chk2;
    }

    public static void print2(long n, long c){
        ArrayList<Long> list = new Pollard_rho().factorize(n);
        long[] ans = { 0, 0 };
        long cnt = 1;

        for (long i : list){
            if (i == 2 || i % 4 == 1){
                long[] tp = p2p(i);

                if (ans[0] == 0 && ans[1] == 0){
                    ans = tp;
                } else {
                    long a = ans[0], b = ans[1];
                    long p = tp[0], q = tp[1];
                    long na = a * p + b * q, nb = Math.abs(a * q - b * p);
                    ans[0] = na;
                    ans[1] = nb;
                }
            } else {
                cnt *= i;
            }
        }

        cnt = (long)Math.sqrt(cnt);
        System.out.print(((ans[0] * cnt) << c) + " " + ((ans[1] * cnt) << c));
    }

    public static void print3(long n, long c){
        long t1 = 2;

        if (n % 4 == 3) t1 = 1;

        while (true){
            long t2 = n - (t1 * t1);

            if (chk2(t2)){
                System.out.print((t1 << c) + " ");
                print2(t2, c);
                break;
            }

            t1 += 2;
        }
    }

    public static void print4(long n, long c){
        System.out.print((1 << c) + " ");
        print3(n - 1, c);
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        long n = in.nextLong();
        // int k = counting(n);
        // sb.append(k).append('\n');
        // long[] res = null;

        if ((long)Math.sqrt(n) * (long)Math.sqrt(n) == n){
            System.out.println("1");
            //System.out.println((long)Math.sqrt(n));
            return;
        }

        long c4 = 0;

        while (n % 4 == 0){
            n /= 4;
            c4++;
        }

        boolean chk2 = chk2(n);

        if (chk2){
            System.out.println("2");
            //print2(n, c4);
        } else if (n % 8 != 7){
            System.out.println("3");
            //print3(n, c4);
        } else {
            System.out.println("4");
            //print4(n, c4);
        }

        // if (k == 1){
        //     res = get1(n);
        // } else if (k == 2){
        //     res = get2(n);
        // } else if (k == 3){
        //     res = get3(n);
        // } else if (k == 4){
        //     res = get4(n);
        // }

        // Arrays.sort(res);

        // for (long x : res){
        //     sb.append(x).append(' ');
        // }

        // System.out.println(sb);
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