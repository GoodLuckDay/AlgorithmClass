import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    static Queue<Integer> queue = new LinkedList();
    static final int vertexCount = 8;
    static final int startVertex = 1;
    static Node[] graph = new Node[vertexCount];
    static boolean[] visited = new boolean[vertexCount];
    static int[] cost = new int[vertexCount];
    static int[] parentNode = new int[vertexCount];

    public static void main(String[] args) {
        initBFS();
        solveBFS(startVertex);
        printResult();
    }

    private static void printResult() {
        System.out.println("시작 노드 : "+ (startVertex+1));
        for(int i=0; i<vertexCount; i++){
            if(i != startVertex) {
                System.out.println(i+1 + "번째 Node의 Cost : " + cost[i]);
                System.out.println(i+1 + "번째 Node의 부모노드 : " + (parentNode[i]+1));
            }
        }
    }

    private static void solveBFS(int startVertex) {
        cost[startVertex] = 0;
        queue.add(startVertex);
        while(!queue.isEmpty()){
            int currentVertex = queue.poll();
            if(!visited[currentVertex]) {
                visited[currentVertex] = true;
                for(Node node = graph[currentVertex]; node != null; node = node.next){
                    int destVertex = node.vertex;
                    if(!visited[destVertex]){
                        if(cost[destVertex] == -1){
                            cost[destVertex] = cost[currentVertex] + 1;
                            parentNode[destVertex] = currentVertex;
                        }
                        queue.add(destVertex);
                    }
                }
            }
        }
    }

    private static void initBFS() {
        Arrays.fill(cost, -1);
        Arrays.fill(parentNode, -1);
        graph[0] = new Node(1, graph[0]);
        graph[0] = new Node(4, graph[0]);
        graph[1] = new Node(0, graph[1]);
        graph[1] = new Node(5, graph[1]);
        graph[2] = new Node(3, graph[2]);
        graph[2] = new Node(5, graph[2]);
        graph[2] = new Node(6, graph[2]);
        graph[3] = new Node(2, graph[3]);
        graph[3] = new Node(6, graph[3]);
        graph[3] = new Node(7, graph[3]);
        graph[4] = new Node(0, graph[4]);
        graph[5] = new Node(1, graph[5]);
        graph[5] = new Node(2, graph[5]);
        graph[5] = new Node(6, graph[5]);
        graph[6] = new Node(2, graph[6]);
        graph[6] = new Node(3, graph[6]);
        graph[6] = new Node(5, graph[6]);
        graph[6] = new Node(7, graph[6]);
        graph[7] = new Node(3, graph[7]);
        graph[7] = new Node(6, graph[7]);
    }
    static class Node{
        int vertex;
        Node next;

        public Node(int vertex, Node next){
            this.vertex = vertex;
            this.next = next;
        }
    }

}