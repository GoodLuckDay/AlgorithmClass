import java.io.*;
import java.nio.file.Paths;
import java.util.StringTokenizer;

//PreCondition : 배열 array는 1부터 N까지 오름차순으로 정렬이 되어있다.
//PostCondition : 찾고자하는 숫자 findNumber가 배열 array에 존재하면 그 장소를 얻는다.
//invariant : 자신이 찾고자하는 숫자 findNumber가 배열 array에 존재한다면 left와 right 구간사이에 존재할것이다.

public class LoopInvariant {
    static final int N = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("invariant_data.txt").toString()));
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[N+1];
        int count = 1;
        int findNumber = 0;
        String str = null;
        while((str = bufferedReader.readLine()) != null){
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            while(stringTokenizer.hasMoreTokens()){
                array[count++] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        System.out.print("찾고자 하는 수 x를 입력해 주세요 : ");
        findNumber = Integer.parseInt(scanner.readLine());

        int left = 1;
        int right = N;
        while(left < right){
            int mid = (left + right) / 2;
            if(array[mid] < findNumber){
                left = mid+1;
            }
            else if(array[mid] >= findNumber){
                right = mid;
            }
        }
        if(array[left] == findNumber){
            System.out.println("Find");
        }
        else{
            System.out.println("Not Exist");
        }

    }
}
