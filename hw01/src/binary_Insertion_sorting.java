import java.io.*;
import java.util.StringTokenizer;

public class binary_Insertion_sorting {
    static final int file_size = 10000;
    static int[] array = new int[file_size];
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("./Random/"+file_size+".txt"));
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("201302482_output.txt"));
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
        for(int i=0; i<file_size-1; i++){
            bufferedWriter.write(array[i]+" ");
        }
        bufferedWriter.write(array[file_size-1]+"");
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void insertion_sort() {
        for(int i=1; i<file_size; i++){
            int key = array[i];
            int j = i-1;
            int loc = binary_search(0, i-1, key);
            while(j >= loc){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }

    public static int binary_search(int left, int right, int value){
        if(left >= right){
            return array[left] < value ? left+1 : left;
        }
        int mid = (left + right) / 2;
        if(array[mid] == value){
            return mid+1;
        }
        return array[mid] < value ? binary_search(mid+1,right,value) : binary_search(left,mid,value);
    }
}
