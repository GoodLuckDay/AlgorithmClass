import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InversionCounter {
    static final int file_size = 100;
    static int count = 0;
    static int[] array = new int[file_size];
    static int[] heler = new int[file_size];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("./dataset04/data05_inversion_04.txt").toString()));
        StringTokenizer stringTokenizer;
        String str = null;
        while((str = bufferedReader.readLine()) != null) {
            stringTokenizer = new StringTokenizer(str);
            while (stringTokenizer.hasMoreTokens()) {
                array[count++] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        System.out.println(sortAndCount(0, count-1));
    }

    private static int sortAndCount(int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right)/2;
        int rA = sortAndCount(left, mid);
        int rB = sortAndCount(mid+1, right);
        int r = mergeAndCount(left, mid, right);
        return rA + rB + r;
    }

    private static int mergeAndCount(int left, int mid, int right) {
        int inversion_count = 0;
        int indexA = left;
        int indexB = mid+1;
        int k = left;
//        for(int i=left; i<=right; i++){
//            heler[i] = array[i];
//        }
        System.arraycopy(array, left , heler, left, right-left+1);
        while(indexA <= mid && indexB <= right){
            if(heler[indexA] > heler[indexB]){
                inversion_count = inversion_count + (mid - indexA+1);
                array[k++] = heler[indexB++];
            }
            else{
                array[k++] = heler[indexA++];
            }
        }

        while(indexA <= mid ){
            array[k++] = heler[indexA++];
        }
        return inversion_count;
    }
}
