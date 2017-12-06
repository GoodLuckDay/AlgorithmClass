import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OptimalBST {
    static double[] p = new double[101];
    static double[] q = new double[101];
    static double[][] e = new double[101][101];
    static double[][] w = new double[101][101];
    static int[][] root = new int[101][101];
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./data11.txt"));
        String str = null;
        while((str = bufferedReader.readLine()) != null){
            String[] temp = str.split("\t");
            p[n] = temp[0].equals("-1") ? 0 : Double.parseDouble(temp[0]);
            q[n] = Double.parseDouble(temp[1]);
            n++;
        }
        findOptimalBST();
        System.out.println(e[1][n-1]);
    }

    private static void findOptimalBST() {
        for(int i=1; i<=n; i++){
            e[i][i-1] = q[i-1];
            w[i][i-1] = q[i-1];
        }

        for(int l=1; l<n; l++){
            for(int i=1; i<n-l+1; i++){
                int j = i + l -1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j-1] + p[j] + q[j];
                for(int r = i; r <= j; r++){
                    double t = e[i][r-1] + e[r+1][j] + w[i][j];
                    if(t < e[i][j]){
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }

    }
}
