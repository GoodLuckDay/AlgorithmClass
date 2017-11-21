//CTGACCTACCT
//CCTGACTACAT
//ocurrance
//occurrence
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SequenceAlignment {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][] array;
    static String[][] pullArray;
    static String string1;
    static String string2;
    static int m ,n;
    static final int GAP = 1;
    static final int MISMATCHES = 2;
    public static void main(String[] args) throws IOException {
        inputString();
        initArray();
        solve();
        backTracking();
        printArray();
    }

    private static void printArray() {

        System.out.println("MIN COST : "+array[m-1][n-1]);
        System.out.printf("%8s", "");
        for(String str : string2.split("")){
            System.out.printf("%2s",str);
            System.out.printf("%2s", "");
        }
        System.out.println("\n");
        for(int i=0; i<2*m-1; ++i){
            if(i % 2 == 0) {
                String temp = i >= 1 ? "" + string1.charAt(i/2-1) : "";
                System.out.printf("%2s", temp);
            } else {
                System.out.printf("%2s", "");
            }
            System.out.printf("%2s", "");
            for(int j=0; j<2*n-1; ++j){
                System.out.printf("%2s",pullArray[i][j]);
            }
            System.out.println();
        }
    }



    private static void solve() {
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                array[i][j] = Integer.min(calculateMisMisMatch(i,j) + array[i-1][j-1],
                        Integer.min(GAP+array[i-1][j], GAP+array[i][j-1]));
            }
        }
    }

    private static void backTracking() {

        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                pullArray[2*i][2*j] = ""+array[i][j];
            }
        }

        int i = m-1, j = n-1;

        while (i > 0 || j > 0) {
            if (i == 0) {
                pullArray[2*i][2*j-1] = "←";
                j--;
                continue;
            }
            if (j == 0) {
                pullArray[2*i-1][2*j] = "↑";
                i--;
                continue;
            }
            if (array[i-1][j] < array[i][j]) {
                pullArray[2*i-1][2*j] = "↑";
                --i;
                continue;
            }
            if (array[i][j-1] < array[i][j]) {
                pullArray[2*i][2*j-1] = "←";
                --j;
                continue;
            }

            if (array[i-1][j-1] == array[i][j]) {
                pullArray[2*i-1][2*j-1] = "↖";
                --i;
                --j;
            }
        }
    }

    private static int calculateMisMisMatch(int i, int j){
        return string1.charAt(i-1) == string2.charAt(j-1) ? 0 : MISMATCHES;
    }
    private static void initArray() {
        for(int i=0; i<n; i++){
            array[0][i] = i*GAP;
        }
        for(int j=0; j<m; j++){
            array[j][0] = j*GAP;
        }
    }

    private static void inputString() throws IOException {
        System.out.print("string1 : ");
        string1 = bufferedReader.readLine();
        System.out.print("string2 : ");
        string2 = bufferedReader.readLine();
//        string1 = "CTGACCTACCT";
//        string2 = "CCTGACTACAT";
//        System.out.println();
        m = string1.length()+1;
        n = string2.length()+1;
        array = new int[m][n];
        pullArray = new String[2*m-1][2*n-1];
        for(int i=0; i<pullArray.length; i++){
            for(int j=0; j<pullArray[0].length; j++){
                pullArray[i][j] = "";
            }
        }
    }
}
