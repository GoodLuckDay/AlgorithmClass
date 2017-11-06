import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Prim {
    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    });
    static final int VERTEXSIZE = 9;
    static final int STARTVERTEX = 0;
    static boolean[] visit = new boolean[VERTEXSIZE];
    static Edge[] graph = new Edge[VERTEXSIZE];
    static HashMap<Integer, String> indexConverter = new HashMap<>();
    public static void main(String[] args) {
        initGraph();
        initIndexConverter();
        minimumSpanningTree();
    }

    private static void initIndexConverter() {
        indexConverter.put(-1, " ");
        indexConverter.put(0, "a");
        indexConverter.put(1, "b");
        indexConverter.put(2, "c");
        indexConverter.put(3, "d");
        indexConverter.put(4, "e");
        indexConverter.put(5, "f");
        indexConverter.put(6, "g");
        indexConverter.put(7, "h");
        indexConverter.put(8, "i");
    }

    private static void minimumSpanningTree() {
        int cost = 0;
        priorityQueue.add(new Edge(-1,STARTVERTEX, 0));
        while(!priorityQueue.isEmpty()){
            Edge temp = priorityQueue.poll();
            int currentVertex = temp.vertex;
            if(!visit[currentVertex]) {
                visit[currentVertex] = true;
                Edge currentEdge = graph[currentVertex];
                cost+=temp.weight;
                printEdge(temp.source, temp.vertex, temp.weight);
                while(currentEdge !=null){
                    priorityQueue.add(new Edge(currentVertex,currentEdge.vertex, currentEdge.weight));
                    currentEdge = currentEdge.next;
                }
            }
        }
        System.out.println("\nw<MST> = "+cost);
    }

    private static void printEdge(int source, int dest, int weight){
        System.out.println("w<"+indexConverter.get(source)+", "+indexConverter.get(dest)+"> = "+weight);
    }
    private static void initGraph() {
        graph[0] = new Edge(1, graph[0], 4);
        graph[0] = new Edge(7, graph[0], 8);
        graph[1] = new Edge(0, graph[1], 4);
        graph[1] = new Edge(2, graph[1], 8);
        graph[1] = new Edge(7, graph[1], 11);
        graph[2] = new Edge(1, graph[2], 8);
        graph[2] = new Edge(3, graph[2], 7);
        graph[2] = new Edge(5, graph[2], 4);
        graph[2] = new Edge(8, graph[2], 2);
        graph[3] = new Edge(2, graph[3], 7);
        graph[3] = new Edge(4, graph[3], 9);
        graph[3] = new Edge(5, graph[3], 14);
        graph[4] = new Edge(3, graph[4], 9);
        graph[4] = new Edge(5, graph[4], 10);
        graph[5] = new Edge(2, graph[5], 4);
        graph[5] = new Edge(3, graph[5], 14);
        graph[5] = new Edge(4, graph[5], 10);
        graph[5] = new Edge(6, graph[5], 2);
        graph[6] = new Edge(5, graph[6], 2);
        graph[6] = new Edge(7, graph[6], 1);
        graph[6] = new Edge(8, graph[6], 6);
        graph[7] = new Edge(0, graph[7], 8);
        graph[7] = new Edge(1, graph[7], 11);
        graph[7] = new Edge(6, graph[7], 1);
        graph[7] = new Edge(8, graph[7], 7);
        graph[8] = new Edge(2, graph[8], 2);
        graph[8] = new Edge(6, graph[8], 6);
        graph[8] = new Edge(8, graph[8], 7);
    }
}

class Edge{
    int source;
    int vertex;
    int weight;
    Edge next;

    public Edge(int vertex, Edge next , int weight){
        this.vertex = vertex;
        this.weight = weight;
        this.next = next;
    }

    public Edge(int source, int vertex, int weight){
        this.source = source;
        this.vertex = vertex;
        this.weight = weight;
    }
}
