import java.io.*;
import java.util.StringTokenizer;

public class Insertion_sorting {
    static final int file_size = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./Worst/"+file_size+".txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("201302482_output.txt"));
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
        insertion_sort(array);
        long end = System.currentTimeMillis();
        System.out.println("실행시간 : "+(end - start)+"초");
        for(int i=0; i<file_size-1; i++){
            bufferedWriter.write(array[i]+" ");
        }
        bufferedWriter.write(array[file_size-1]+"");
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void insertion_sort(int[] array) {
        for(int i=1; i<file_size; i++){
            int key = array[i];
            int j = i-1;
            while(j >= 0 && array[j] > key){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }
}
