import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
    static final int VERTEXCOUNT = 5;
    static final int STARTVERTEX = 0;
    static int count = 0;
    static boolean[] visit = new boolean[VERTEXCOUNT];
    static int[] d = new int[VERTEXCOUNT];
    static int[][] graph = new int[VERTEXCOUNT][VERTEXCOUNT];
    static PriorityQueue<Nodes> priorityQueue = new PriorityQueue<>(new Comparator<Nodes>() {
        @Override
        public int compare(Nodes o1, Nodes o2) {
            return Integer.compare(o1.distant, o2.distant);
        }
    });
    public static void main(String[] args) {
        initGraph();
        initDistant();
        findShortestPath();
    }

    private static void findShortestPath() {
        System.out.println("dijkstra's algorithm.\n");
        int index = 0;
        while(!priorityQueue.isEmpty()){
            count = 0;
            int startVertex = priorityQueue.poll().vertex;
            if(!visit[startVertex]) {
                visit[startVertex] = true;
                currentVertexInfo(index++, startVertex);
                for(int i=0; i<VERTEXCOUNT; i++){
                    String str = d[i]+"";
                    if(graph[startVertex][i] > 0 &&(!visit[i] && d[startVertex] + graph[startVertex][i] < d[i])){
                        d[i] = d[startVertex] + graph[startVertex][i];
                        priorityQueue.add(new Nodes(i, d[i]));
                        str += " -> d["+(char)(i+'A')+"] = "+d[i];
                    }
                    printDistantInfo(i, str);
                }
            }
        }
    }

    private static void printDistantInfo(int vertex, String str) {
        if(!visit[vertex]){
            System.out.println("Q["+count+"] : d["+(char)(vertex+'A')+"] = "+str);
            count++;
        }
    }

    private static void currentVertexInfo(int index ,int startVertex) {
        System.out.println("==========================================");
        System.out.println("S[" + index + "] : d[" + (char)(startVertex+'A') + "] = " + d[startVertex]);
        System.out.println("-------------------------------------------");
    }

    private static void initDistant() {
        for(int i=0; i<VERTEXCOUNT; i++){
            d[i] = i==STARTVERTEX ? 0 : Integer.MAX_VALUE;
        }
        priorityQueue.offer(new Nodes(STARTVERTEX, d[STARTVERTEX]));
    }
    private static void initGraph() {
        graph[0][1] = 10;
        graph[0][2] = 3;
        graph[1][2] = 1;
        graph[1][3] = 2;
        graph[2][1] = 4;
        graph[2][3] = 8;
        graph[2][4] = 2;
        graph[3][4] = 7;
        graph[4][3] = 9;

    }
}

class Nodes {
    int vertex;
    int distant;

    public Nodes(int vertex, int distant){
        this.vertex = vertex;
        this.distant = distant;
    }
}

