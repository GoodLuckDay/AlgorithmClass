import java.io.*;
import java.util.StringTokenizer;

public class QuickSort {
    static final int file_size = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./Random/"+file_size+".txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./201302482_quick"));
        int[] array = new int[file_size];
        int count = 0;
        String str = null;

        while((str = bufferedReader.readLine()) != null){
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            while(stringTokenizer.hasMoreTokens()){
                array[count++] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        long start = System.currentTimeMillis();
        quickSort(array, 0, file_size-1);
        long end = System.currentTimeMillis();
        for(int i=0; i<file_size-1; i++){
            bufferedWriter.write(array[i]+" ");
        }
        bufferedWriter.write(array[file_size-1]+"");
        bufferedWriter.flush();

        System.out.println("실행 시간 : "+(end - start));
    }

    public static void quickSort(int[] A, int left, int right){
        if(left <= right){
            int pivot = partition(A,left,right);
            quickSort(A, left, pivot-1);
            quickSort(A, pivot+1, right);
        }
    }

    public static int partition(int[] A, int left, int right){
        int pivot = A[right];
        int i = left;
        int j = right-1;
        while(j != i-1){
            if(pivot >= A[i])
                i++;
            else if(pivot < A[j])
                j--;
            else{
                swap(A, i, j);
                i++;
                j--;
            }
        }
        swap(A, i, right);
        return i;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
