import java.util.*;
public class BellmanFord {
    static Scanner scanner = new Scanner(System.in);
    static int[][] graph;
    static int[][] solve;
//    static int[] solve;
    static int vertex;
    static int start, end;
    static int edge;
    public static void main(String[] args){
        initGraph();
        shortestPath();
        int cost = solve[vertex-1][start];
        System.out.println(cost >= 0 ? cost : "Negative cycle");
    }

    private static void shortestPath() {
        for(int i=0; i<vertex; i++){
            solve[0][i] = 10000;
        }
        solve[0][end] = 0;

        for(int i=1; i<vertex; i++){
            for(int j=0; j<vertex; j++){
                solve[i][j] = solve[i-1][j];
            }


            for(int j=0; j<vertex; j++){
                for(int k=0; k<vertex; k++){
                    if(graph[j][k] != 0){
                        solve[i][j] = Integer.min(solve[i][j], solve[i-1][k] + graph[j][k]);
                    }
                }
            }
        }
    }


    private static void initGraph() {
        vertex = scanner.nextInt();
        start = scanner.nextInt();
        end = scanner.nextInt();
        edge = scanner.nextInt();
        graph = new int[vertex][vertex];
        solve = new int[vertex][vertex];
        for(int i=0; i<edge; i++){
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph[src][dest] = weight;
        }

        shortestPath();
    }
}
