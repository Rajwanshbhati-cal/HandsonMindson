import java.math.BigInteger;

public class Convergence{
    
    
    public static int[] getContinuedFractionE(int n) {
        int[] seq = new int[n];
        seq[0] = 2; 

        for (int i = 1; i < n; i++) {
            if ((i + 1) % 3 == 0) {
                seq[i] = 2 * ((i + 1) / 3);
            } else {
                seq[i] = 1;
            }
        }
        return seq;
    }

 
    public static BigInteger getConvergent(int n) {
        int[] seq = getContinuedFractionE(n);
        BigInteger numerator = BigInteger.ONE, denominator = BigInteger.ZERO;

        for (int i = n - 1; i >= 0; i--) {
            BigInteger temp = numerator;
            numerator = BigInteger.valueOf(seq[i]).multiply(numerator).add(denominator);
            denominator = temp;
        }
        return numerator;
    }


    public static int sumOfDigits(BigInteger num) {
        String numStr = num.toString();
        int sum = 0;
        for (char digit : numStr.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }
        return sum;
    }

    public static void main(String[] args) {
        BigInteger numerator100 = getConvergent(100);
        System.out.println("100th Convergent Numerator: " + numerator100);
        System.out.println("Sum of Digits: " + sumOfDigits(numerator100));
    }
}
