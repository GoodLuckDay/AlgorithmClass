import java.util.Arrays;

public class DFS {
    static final int vertexCount = 6;
    static final int startVertex = 0;
    static Node[] graph = new Node[vertexCount];
    static boolean[] visited = new boolean[vertexCount];
    static int[] findingTime = new int[vertexCount];
    static int[] completedTime = new int[vertexCount];
    static int[] parentNode = new int[vertexCount];
    static int timeCount = 1;
    public static void main(String[] args) {
        initDFS();
        for(int i=0; i<vertexCount; i++){
            if(!visited[i]) {
                findingTime[i] = timeCount++;
                solveDFS(i);
                completedTime[i] = timeCount++;
            }
        }
        printResult();
    }

    private static void printResult() {
        System.out.println("시작 노드 : "+ (startVertex+1));
        for(int i=0; i<vertexCount; i++){
            if(i != startVertex) {
                System.out.println(i+1 + "번째 Node의 부모노드 : " + (parentNode[i]+1));
                System.out.println(i+1 + "번째 발견 시간 : " + findingTime[i]);
                System.out.println(i+1 + "번째 탐색 완료 시간 : " + completedTime[i]);
            }
        }
    }

    private static void solveDFS(int startVertex) {
        if(visited[startVertex]){
            return ;
        }
        visited[startVertex] = true;
        for(Node node=graph[startVertex];node != null; node=node.next){
            int destVertex = node.vertex;
            if(!visited[destVertex]){
                findingTime[destVertex] = timeCount++;
                parentNode[destVertex] = startVertex;
                solveDFS(destVertex);
                completedTime[destVertex] = timeCount++;
            }
        }
    }

    private static void initDFS() {
        Arrays.fill(findingTime, -1);
        Arrays.fill(completedTime, -1);
        Arrays.fill(parentNode, -1);
        graph[0] = new Node(3, graph[0]);
        graph[0] = new Node(1, graph[0]);
        graph[1] = new Node(4, graph[1]);
        graph[2] = new Node(5, graph[2]);
        graph[2] = new Node(4, graph[2]);
        graph[3] = new Node(1, graph[3]);
        graph[4] = new Node(3, graph[4]);
        graph[5] = new Node(5, graph[5]);
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
