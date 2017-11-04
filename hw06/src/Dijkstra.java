import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {
    static final int VERTEXCOUNT = 5;
    static final int STARTVERTEX = 0;
    static boolean[] visit = new boolean[VERTEXCOUNT];
    static int[] d = new int[VERTEXCOUNT];
//    static Nodes[] graph = new Nodes[VERTEXCOUNT];
    static int[][] graph = new int[VERTEXCOUNT][VERTEXCOUNT];
    static PriorityQueue<Nodes> priorityQueue = new PriorityQueue<>(new Comparator<Nodes>() {
        @Override
        public int compare(Nodes o1, Nodes o2) {
            return Integer.compare(o1.distant, o2.distant);
        }
    });
    static HashMap<Integer, String> indexConverter = new HashMap<>();
    public static void main(String[] args) {
        initGraph();
        initDistant();
        initIndexConverter();
        findShortestPath();
    }

    private static void initIndexConverter() {
        indexConverter.put(0, "A");
        indexConverter.put(1, "B");
        indexConverter.put(2, "C");
        indexConverter.put(3, "D");
        indexConverter.put(4, "E");
    }

    private static void findShortestPath() {
        System.out.println("dijkstra's algorithm.\n");
        int sum = 0;
        while(!priorityQueue.isEmpty()){
            int count = 0;
            int startVertex = priorityQueue.poll().vertex;
            if(!visit[startVertex]) {
                visit[startVertex] = true;
                currentVertexInfo(startVertex);
                for(int i=0; i<VERTEXCOUNT; i++){
                    String str = d[i]+"";
                    if(graph[startVertex][i] > 0 &&(!visit[i] && d[startVertex] + graph[startVertex][i] < d[i])){
                        d[i] = d[startVertex] + graph[startVertex][i];
                        priorityQueue.add(new Nodes(i, d[i]));
                        str += "-> d["+indexConverter.get(i)+"] = "+d[i];
                    }
                    printDistantInfo(i, count, str);
                }
            }
        }
    }

    private static void printDistantInfo(int vertex, int count, String str) {
        if(!visit[vertex]){
            System.out.println("Q["+count+"] : d["+indexConverter.get(vertex)+"] = "+str);
        }
    }

    private static void currentVertexInfo(int startVertex) {
        System.out.println("==========================================");
        System.out.println("S[" + startVertex + "] : d[" + indexConverter.get(startVertex) + "] = " + d[startVertex]);
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
//    Nodes next;
//    public Nodes(int vertex, int distant, Nodes next){
//        this.vertex = vertex;
//        this.distant = distant;
//        this.next = next;
//    }

    public Nodes(int vertex, int distant){
        this.vertex = vertex;
        this.distant = distant;
    }
}

