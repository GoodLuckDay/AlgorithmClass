import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class SequenceAlignment {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static HashMap<Integer, Integer> arrowPath = new HashMap<Integer, Integer>();
    static final int GAP = 1;
    static final int MISMATCH = 2;
    static String sentence1;
    static String sentence2;
    static int[][] array;
    static int[] x_array;
    static int[] y_array;

    public static void main(String[] args) throws IOException {
        System.out.print("string1 : ");
        sentence2 = bufferedReader.readLine();
        System.out.print("string2 : ");
        sentence1 = bufferedReader.readLine();
//        align(sentence1, sentence2);
//        String y = "ocurrance";
//        String x = "occurrence";
//        String y = "CTGACCTACCT";
//        String x = "CCTGACTACAT";
//        String y = "CAGTTGCAA";
//        String x = "AAGGTATGAATC";
        if(sentence2.length() >=10 || sentence1.length() >=10){
            System.out.println("10자리 이하의 문자열을 입력해 주세요");
            return ;
        }
        align(sentence2, sentence1, 0, 0);
        for (Integer integer : arrowPath.keySet()) {
            System.out.println(integer + " " + arrowPath.get(integer));
        }
    }

    private static void align(String sentence1, String sentence2, int xStart, int yStart) {
        int n = sentence1.length();
        int m = sentence2.length();
        if (n <= 2 || m <= 2) {
            arrowPath.put(xStart, yStart);
            standardAlignment(sentence1, sentence2);
            backTracking(xStart, yStart, n, m);
            return;
        }
        int[] YPrefix = AllYPrefixCosts(sentence1, n / 2, sentence2);
        int[] YSuffix = AllSuffixCosts(sentence1, n / 2, sentence2);

        int cost = Integer.MAX_VALUE;
        int best = Integer.MAX_VALUE;
        int bestq = 0;
        for (int q = 0; q < m; q++) {
            cost = YPrefix[q] + YSuffix[q];
            if (cost < best) {
                best = cost;
                bestq = q;
            }
        }

        arrowPath.put(n / 2 + xStart, bestq + yStart);
        align(sentence1.substring(0, n / 2), sentence2.substring(0, bestq), xStart, yStart);
        align(sentence1.substring(n / 2, n), sentence2.substring(bestq, m), xStart + n / 2, yStart + bestq);
    }

    private static void standardAlignment(String sentence1, String sentence2) {
        int n = sentence1.length();
        int m = sentence2.length();
        array = new int[n + 1][m + 1];
        for (int i = 0; i < m + 1; i++) {
            array[0][i] = i;
        }
        for (int i = 0; i < n + 1; i++) {
            array[i][0] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                array[i][j] = Integer.min(calculateMisMatch(sentence1, sentence2, i, j) + array[i - 1][j - 1], Integer.min(array[i - 1][j] + GAP, array[i][j - 1] + GAP));
            }
        }
    }

    private static int[] AllSuffixCosts(String sentence1, int mid, String sentence2) {
        String newSentence = "";
        for (int i = sentence1.length() - 1; i >= mid; i--) {
            newSentence += sentence1.charAt(i);
        }
        String newSentence2 = "";
        for (int i = sentence2.length() - 1; i >= 0; i--) {
            newSentence2 += sentence2.charAt(i);
        }
        alignment(newSentence, newSentence2);
        int size = y_array.length;
        int[] temp = new int[size];
        for (int i = 0; i < size; i++) {
            temp[i] = y_array[size-1-i];
        }
        return temp;
    }

    private static int[] AllYPrefixCosts(String sentence1, int mid, String sentence2) {
        alignment(sentence1.substring(0, mid), sentence2);
        return y_array;
    }

    private static void alignment(String sentence1, String sentence2) {
        int n = sentence1.length();
        int m = sentence2.length();
        x_array = new int[n+1];
        y_array = new int[m+1];
        int[] temp = new int[m+1];
        for (int i = 0; i < m + 1; i++) {
            y_array[i] = i*GAP;
        }
        for (int i = 0; i < n + 1; i++) {
            x_array[i] = i*GAP;
        }
        for(int i=1; i<=n; i++) {
            temp[0] = x_array[i];
            for (int j = 1; j <= m; j++) {
                temp[j] = Integer.min(calculateMisMatch(sentence1, sentence2, i,j) + y_array[j-1], Integer.min(y_array[j]+GAP, temp[j-1]+GAP));
            }
            System.arraycopy(temp, 0, y_array, 0, temp.length);
        }
    }

    private static void backTracking(int xStart, int yStart, int n, int m) {
        int i = n, j = m;
        while (i != 0 || j != 0) {
            arrowPath.put(xStart+i, yStart+j);
            if (i == 0) {
                j--;
                continue;
            }
            if (j == 0) {
                i--;
                continue;
            }
            if (array[i - 1][j] < array[i][j]) {
                i = i - 1;
                continue;
            }
            if (array[i][j - 1] < array[i][j]) {
                j = j - 1;
                continue;
            }
            if (array[i - 1][j - 1] <= array[i][j]) {
                i = i - 1;
                j = j - 1;

            }
        }
        arrowPath.put(xStart+i, yStart+j);
    }

    private static int calculateMisMatch(String st1, String st2, int i, int j) {
        return st1.charAt(i - 1) == st2.charAt(j - 1) ? 0 : MISMATCH;
    }
}

