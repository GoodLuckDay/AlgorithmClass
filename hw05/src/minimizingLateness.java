import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;

public class minimizingLateness{
    public static void main(String[] args) throws IOException {
        PriorityQueue<Data> priorityQueue = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if(o1.d < o2.d){
                    return -1;
                }
                else if(o1.d > o2.d){
                    return 1;
                }
                else{
                    if(o1.t < o2.t){
                        return -1;
                    }
                    else if(o1.t > o2.t){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        });
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("./data06_lateness.txt").toString()));
        int N = Integer.parseInt(bufferedReader.readLine());
        for(int i=0; i<N; i++){
            String[] tempStr = bufferedReader.readLine().split(" ");
            priorityQueue.add(new Data(Integer.parseInt(tempStr[0]), Integer.parseInt(tempStr[1])));
        }

        int lateness = 0;
        int time = 0;
        while(!priorityQueue.isEmpty()) {
            Data d = priorityQueue.poll();
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
