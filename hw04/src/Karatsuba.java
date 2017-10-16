import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;

public class Karatsuba {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("./dataset04/data05_karatsuba.txt").toString()));
        BigInteger A = new BigInteger(bufferedReader.readLine());
        BigInteger B = new BigInteger(bufferedReader.readLine());
        System.out.println(calculate_karatsuba(A, B));
    }

    private static BigInteger calculate_karatsuba(BigInteger A, BigInteger B) {
        int m = digitCount(A)/2;
        if(m == 1){
            return A.multiply(B);
        }
        BigInteger x1 = A.divide(new BigInteger(String.valueOf((long)Math.pow(10,m))));
        BigInteger x2 = A.mod(new BigInteger(String.valueOf((long)Math.pow(10,m))));
        m = digitCount(B)/2;
        BigInteger y1 = B.divide(new BigInteger(String.valueOf((long)Math.pow(10,m))));
        BigInteger y2 = B.mod(new BigInteger(String.valueOf((long)Math.pow(10,m))));

        BigInteger z0 = calculate_karatsuba(x2, y2);
        BigInteger z2 = calculate_karatsuba(x1, y1);
        BigInteger z1 = calculate_karatsuba(x2.add(x1), y2.add(y1)).subtract(z2).subtract(z0);

        return z2.multiply(new BigInteger(String.valueOf((long)Math.pow(10,2*m)))).add(z1.multiply(new BigInteger(String.valueOf((long)Math.pow(10,m))))).add(z0);
    }

    private static int digitCount(BigInteger a) {
        long number = a.longValue();
        int count = 0;
        while(number != 0){
            number /= 10;
            count++;
        }
        return count;
    }


}
