import java.io.*;
import java.util.StringTokenizer;

public class merge_sorting {
    static final int file_size = 10000;
    static int[] array = new int[file_size];
    static int[] helper = new int[file_size];
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("./Worst/"+file_size+".txt"));
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
        mergeSort(0, file_size-1);
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

    public static void mergeSort(int left, int right){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid+1, right);
            merge(left, mid, right);
        }
    }

    private static void merge(int left, int mid, int right) {
        for(int i=left; i<=right; i++){
            helper[i] = array[i];
        }
        int i = left;
        int j = mid+1;
        int k = left;

        while(i <= mid && j <=right){
            if(helper[i] < helper[j]){
                array[k] = helper[i];
                i++;
            }
            else{
                array[k] = helper[j];
                j++;
            }
            k++;
        }

        while(i <= mid){
            array[k] = helper[i];
            i++;
            k++;
        }

    }


}
