package util;

import java.util.Random;

public class RandomUtil {
	public static final long RAND_SEED = System.currentTimeMillis();
	public static Random random = new Random (RAND_SEED);
	
	/**
	 * Returns a random real number uniformly in [0, 1) 
	 * 
	 * @return a random real number uniformly in [0, 1)
	 */
	public static double uniform () {
		return random.nextDouble();
	}
	
    /**
     * Returns a random integer uniformly in [0, n).
     * 
     * @param n number of possible integers
     * @return a random integer uniformly between 0 (inclusive) and {@code n} (exclusive)
     * @throws IllegalArgumentException if {@code n <= 0}
     */
    public static int uniform(int n) {
        if (n <= 0) throw new IllegalArgumentException("argument must be positive: " + n);
        return random.nextInt(n);
    }
    
    /**
     * Returns a random real number uniformly in [a, b).
     * 
     * @param  a the left endpoint
     * @param  b the right endpoint
     * @return a random real number uniformly in [a, b)
     * @throws IllegalArgumentException unless {@code a < b}
     */
    public static double uniform(double a, double b) {
        if (!(a < b)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + uniform() * (b-a);
    }
	
	/**
	 * Rearranges the elements of the specified array in uniformly random order
	 * @param a the array to shuffle
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 */
	public static void shuffle(double[][] a, double[][] b) {
		validateNotNull(a);
		validateNotNull(b);
		int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n-i);     // between i and n-1
            double[] temp = a[i];
            double[] temp2 = b[i];
            a[i] = a[r];
            a[r] = temp;
            b[i] = b[r];
            b[r] = temp2;
        }
	}
	
	//throw an IllegalArgumentException if x is null
	//(x can be of type Object[], double[], int[]...)
	private static void validateNotNull(Object x) {
		if(x == null) {
			throw new IllegalArgumentException("argument is null");
		}
	}
	
	
}
