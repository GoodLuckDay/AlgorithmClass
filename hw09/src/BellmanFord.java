import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BellmanFord {
    static final int MAX = 10000000;
    static int[][] graph;
    static int[] solve;
    static int start;
    static int end;
    static int vertex;
    static int edge;

    public static void main(String[] args) throws IOException {
        initGraph();
        shortestPath();
    }

    private static void shortestPath() {
        solve[start] = 0;
        for (int i = 0; i < vertex; i++) {
            solve[i] = i == start ? 0 : MAX;
        }

        for (int i = 1; i < vertex - 1; i++) {
            for (int j = 0; j < vertex; j++) {
                for (int k = 0; k < vertex; k++) {
                    if (graph[j][k] != 0 && solve[k] > solve[j] + graph[j][k]) {
                        solve[k] = solve[j] + graph[j][k];
                    }
                }
            }
        }

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                if (graph[i][j] != 0 && solve[j] > solve[i] + graph[i][j]) {
                    System.out.println(solve[j]);
                    return;
                }
            }
        }

        System.out.println(solve[end]);

    }

    private static void initGraph() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./data10.txt"));
        vertex = Integer.parseInt(bufferedReader.readLine());
        String[] temp = bufferedReader.readLine().split(" ");
        StringTokenizer stringTokenizer;
        start = Integer.parseInt(temp[0]);
        end = Integer.parseInt(temp[1]);
        edge = Integer.parseInt(bufferedReader.readLine());
        graph = new int[vertex][vertex];
        solve = new int[vertex];
        for (int i = 0; i < edge; i++) {
            temp = bufferedReader.readLine().split(" ");
            int src = Integer.parseInt(temp[0]);
            int dest = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);
            graph[src][dest] = weight;
        }
    }
}
