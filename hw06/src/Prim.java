import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim {
    static PriorityQueue<PrimNode> priorityQueue = new PriorityQueue<>(new Comparator<PrimNode>() {
        @Override
        public int compare(PrimNode o1, PrimNode o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    });
    static final int VERTEXSIZE = 9;
    static final int STARTVERTEX = 0;
    static boolean[] visit = new boolean[VERTEXSIZE];
    static PrimNode[] graph = new PrimNode[VERTEXSIZE];
    public static void main(String[] args) {
        initGraph();
        minimumSpanningTree();
    }

    private static void minimumSpanningTree() {
        int cost = 0;
        priorityQueue.add(new PrimNode(' ',STARTVERTEX, 0));
        while(!priorityQueue.isEmpty()){
            PrimNode temp = priorityQueue.poll();
            int currentVertex = temp.vertex;
            if(!visit[currentVertex]) {
                visit[currentVertex] = true;
                PrimNode currentEdge = graph[currentVertex];
                cost+=temp.weight;
                printEdge(temp.source, temp.vertex, temp.weight);
                while(currentEdge !=null){
                    priorityQueue.add(new PrimNode(currentVertex,currentEdge.vertex, currentEdge.weight));
                    currentEdge = currentEdge.next;
                }
            }
        }
        System.out.println("\nw<MST> = "+cost);
    }

    private static void printEdge(int source, int dest, int weight){
        System.out.println("w< "+(char)(source + 'a')+", "+(char)(dest + 'a')+"> = "+weight);
    }
    private static void initGraph() {
        graph[0] = new PrimNode(1, graph[0], 4);
        graph[0] = new PrimNode(7, graph[0], 8);
        graph[1] = new PrimNode(0, graph[1], 4);
        graph[1] = new PrimNode(2, graph[1], 8);
        graph[1] = new PrimNode(7, graph[1], 11);
        graph[2] = new PrimNode(1, graph[2], 8);
        graph[2] = new PrimNode(3, graph[2], 7);
        graph[2] = new PrimNode(5, graph[2], 4);
        graph[2] = new PrimNode(8, graph[2], 2);
        graph[3] = new PrimNode(2, graph[3], 7);
        graph[3] = new PrimNode(4, graph[3], 9);
        graph[3] = new PrimNode(5, graph[3], 14);
        graph[4] = new PrimNode(3, graph[4], 9);
        graph[4] = new PrimNode(5, graph[4], 10);
        graph[5] = new PrimNode(2, graph[5], 4);
        graph[5] = new PrimNode(3, graph[5], 14);
        graph[5] = new PrimNode(4, graph[5], 10);
        graph[5] = new PrimNode(6, graph[5], 2);
        graph[6] = new PrimNode(5, graph[6], 2);
        graph[6] = new PrimNode(7, graph[6], 1);
        graph[6] = new PrimNode(8, graph[6], 6);
        graph[7] = new PrimNode(0, graph[7], 8);
        graph[7] = new PrimNode(1, graph[7], 11);
        graph[7] = new PrimNode(6, graph[7], 1);
        graph[7] = new PrimNode(8, graph[7], 7);
        graph[8] = new PrimNode(2, graph[8], 2);
        graph[8] = new PrimNode(6, graph[8], 6);
        graph[8] = new PrimNode(8, graph[8], 7);
    }
}

class PrimNode {
    int source;
    int vertex;
    int weight;
    PrimNode next;

    public PrimNode(int vertex, PrimNode next , int weight){
        this.vertex = vertex;
        this.weight = weight;
        this.next = next;
    }

    public PrimNode(int source, int vertex, int weight){
        this.source = source;
        this.vertex = vertex;
        this.weight = weight;
    }
}
