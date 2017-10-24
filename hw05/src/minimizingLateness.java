import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class minimizingLateness{
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("./data06_lateness.txt").toString()));
        ArrayList<Data> arrayList = new ArrayList<>();
        int N = Integer.parseInt(bufferedReader.readLine());
        for(int i=0; i<N; i++){
            String[] tempStr = bufferedReader.readLine().split(" ");
            arrayList.add(new Data(Integer.parseInt(tempStr[0]), Integer.parseInt(tempStr[1])));
        }

        int lateness = 0;
        int time = 0;
        for(int i=0; i<arrayList.size(); i++){
            Data d = arrayList.get(i);
            int tempF = time + d.t;
            time = time + d.t;
            lateness += Math.max(0, tempF - d.d);
        }

        System.out.println(lateness);
    }
}

class Data{
    int t;
    int d;

    public Data(int t, int d){
        this.t = t;
        this.d = d;
    }
}
