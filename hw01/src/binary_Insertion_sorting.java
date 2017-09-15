import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class binary_Insertion_sorting {
    static final int file_size = 10000;
    static int[] array = new int[file_size];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./Random/"+file_size+".txt"));
        int index = 0;
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            while(stringTokenizer.hasMoreTokens()){
                array[index] = Integer.parseInt(stringTokenizer.nextToken());
                index++;
            }
        }
        long start = System.currentTimeMillis();
        insertion_sort();
        long end = System.currentTimeMillis();
        System.out.println("실행시간 : "+(end - start)+"초");
    }

    private static void insertion_sort() {
        for(int i=1; i<file_size; i++){
            int key = array[i];
            int j = binary_search(0, i-1, key);
            for(int k=i-1; k>=j; k--){
                array[k+1] = array[k];
            }
            array[j] = key;
        }
    }

    public static int binary_search(int left, int right, int value){
        if(left >= right){
            return array[left] < value ? left+1 : left;
        }
        int mid = (left + right) / 2;
        return array[mid] < value ? binary_search(mid+1,right,value) : binary_search(left,mid,value);
    }
}
