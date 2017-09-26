import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapSort {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("data_heap.txt"));
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        Node[] nodes = new Node[101];
        String str = null;
        while((str = bufferedReader.readLine()) != null){
            String[] line = str.split(", ");
            nodes[++count] = new Node(Integer.parseInt(line[0]), line[1]);
        }
        build_Max_Heap(nodes);

        String inputNum = "";
        System.out.println("\n**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****\n");
        while(!inputNum.equals("6")){
            printMyHeap(nodes);
            inputNum = scanner.readLine();
            try {
                switch (Integer.parseInt(inputNum)) {
                    case 1:
                        System.out.print("신규 작업명 (20 Bytes 이내) : ");
                        String work = scanner.readLine();
                        System.out.print("우선 순위 (0 ~ 999) : ");
                        int rank = Integer.parseInt(scanner.readLine());
                        insert(nodes, new Node(rank, work));
                        System.out.println("**** 작업 추가 완료 ****\n");
                        break;
                    case 2:
                        Node maxNode = max(nodes);
                        System.out.println(maxNode == null ? "\n현재 어떠한 작업도 큐에 저장이 되어있지 않습니다.\n" : "\n**** MAX : " + maxNode.values + ", " + maxNode.subject + " ****\n");
                        break;
                    case 3:
                        Node extract_Node = extract_max(nodes);
                        System.out.println(extract_Node == null ? "\n삭제 가능한 작업이 존재하지 않습니다.\n" : "\n**** 한 개의 작업을 처리했습니다. ****\n");
                        break;
                    case 4:
                        System.out.print("수정할 노드 x 입력 : ");
                        int modifyIndex = Integer.parseInt(scanner.readLine());
                        System.out.print("수정할 key : ");
                        int modityKey = Integer.parseInt(scanner.readLine());
                        Node increase_Node = increase_key(nodes, modifyIndex, modityKey);
                        System.out.println(increase_Node == null ? "\n현재 key보다 작아 질 수 없습니다. \n" : "\n**** 한 개의 작업을 처리했습니다. ****");
                        break;
                    case 5:
                        System.out.print("삭제할 노드 x 입력 : ");
                        int removeIndex = Integer.parseInt(scanner.readLine());
                        Node delete_Node = delete(nodes, removeIndex);
                        System.out.println(delete_Node == null ? "\n삭제 가능한 작업이 존재하지 않습니다.\n" : "\n**** 한 개의 작업을 처리했습니다. ****\n");
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("\n**** (1~6) 사이에 값을 입력해 주세요! ****");
                        break;
                }
            }
            catch (NumberFormatException error){
                System.out.println("\n**** (1~6) 사이에 값을 입력해 주세요! ****");
            }
        }
    }

    public static void insert(Node[] nodes, Node x){
        nodes[++count] = x;
        int parent = count/2;
        int child = count;
        while(parent > 0 && nodes[parent].values < x.values){
            nodes[child] = nodes[parent];
            child = parent;
            parent /=2;
        }
        nodes[child] = x;

    }

    public static Node max(Node[] nodes)
    {
        return count >= 1 ? nodes[1] : null;
    }

    public static Node extract_max(Node[] nodes){
        Node maxNode = count>=1 ? nodes[1] : null;
        if(maxNode != null){
            swap(nodes, 1, count);
            nodes[count--] = null;
            heappfy(nodes, 1);
        }
        return maxNode;
    }
    public static Node increase_key(Node[] nodes, int x, int k){
        Node increase_Node = nodes[x].values < k ? nodes[x] : null;
        if(increase_Node !=null){
            increase_Node.values = k;
            swap(nodes, 1, x);
            heappfy(nodes, 1);
        }
        return increase_Node;
    }
    public static Node delete(Node[] nodes, int x){
        Node deleteNode = x<=count ? nodes[x] : null;
        if(deleteNode !=null){
            swap(nodes,x, count);
            nodes[count--] = null;
            heappfy(nodes, x);
        }
        return deleteNode;
    }

    public static void build_Max_Heap(Node[] nodes){
        for(int i=count/2; i>0; i--){
            heappfy(nodes, i);
        }
    }
    private static void heappfy(Node[] nodes, int index){
        int left = 2*index;
        int right= 2*index+1;
        int largest = index;
        if(left <= count && nodes[left].values > nodes[largest].values){
            largest = left;
        }
        else{
            largest = index;
        }
        if(right <= count && nodes[right].values > nodes[largest].values){
            largest = right;
        }

        if(largest != index){
            swap(nodes, largest, index);
            heappfy(nodes, largest);
        }

    }
    private static void swap(Node[] nodes, int i, int j){
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }
    public static void printMyHeap(Node[] nodes){
        for(int i=1; i<=count; i++){
            System.out.println(nodes[i]);
        }
        System.out.println("\n-----------------------------------------------");
        System.out.println("1. 작업 추가    2. 최댓값  3. 최대 우선순위 작업 처리");
        System.out.println("4. 원소 키값 증가         5. 작업제거        6.종료");
        System.out.println("-----------------------------------------------");
    }

}

class Node{
    String subject = "";
    int values = 0;
    public Node(int values, String subject){
        this.values = values;
        this.subject = subject;
    }

    public String toString(){
        return values+", "+subject;
    }
}
