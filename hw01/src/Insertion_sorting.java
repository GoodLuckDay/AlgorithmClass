import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Insertion_sorting {
    static final int file_size = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./Random/"+file_size+".txt"));
        int[] array = new int[file_size];
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
        for(int i=1; i<file_size; i++){
            int key = array[i];
            int j = i-1;
            while(j >= 0 && array[j] > key){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
        long end = System.currentTimeMillis();
        System.out.println("실행시간 : "+(end - start) / 1000.0 +"초");

    }
}
