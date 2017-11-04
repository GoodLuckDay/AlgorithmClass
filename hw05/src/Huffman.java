import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public class Huffman {
    static HashMap<String, Integer> frequecyTable = new HashMap<>();
    static HashMap<String, String> huffmanCode = new HashMap<>();
    static MinHeap minHeap = new MinHeap();
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("./data06_huffman.txt").toString()));
        String[] strings = bufferedReader.readLine().split("");

        countCharacters(strings);
        makeHuffmanTree();
        makeHuffmanCode(nodes[1], "");
        printHuffmanCode();

    }

    private static void printHuffmanCode() {
        for(String key : huffmanCode.keySet()){
            System.out.println(key+","+ huffmanCode.get(key));
        }
    }

    private static void makeHuffmanCode(Node node, String str){
        if(!node.character.equals("")){
            huffmanCode.put(node.character, str);
        }
        else {
            makeHuffmanCode(node.leftNode, str+"0");
            makeHuffmanCode(node.rightNode, str+"1");
        }
    }

    private static void makeHuffmanTree() {
        nodes = new Node[frequecyTable.keySet().size()+1];

        for(String key : frequecyTable.keySet()){
            minHeap.insert(nodes, new Node(frequecyTable.get(key), key));
        }

        int n = minHeap.count;
        for(int i=0; i<n-1; i++){
            Node z = new Node(0,"");
            z.leftNode = minHeap.extract_min(nodes);
            z.rightNode = minHeap.extract_min(nodes);
            z.count = z.leftNode.count + z.rightNode.count;
            minHeap.insert(nodes, z);
        }
    }

    private static void countCharacters(String[] strings) {
        for(int i=0; i<strings.length; i++){
            String temp = strings[i];
            if(frequecyTable.containsKey(temp)){
                frequecyTable.replace(temp, frequecyTable.get(temp)+1);
            }
            else{
                frequecyTable.put(temp,1);
            }
        }
    }
}

class MinHeap{
    static int count = 0;
    public static void insert(Node[] nodes, Node x){
        nodes[++count] = x;
        reverseHeappy(nodes, x, count);
    }

    private static void reverseHeappy(Node[] nodes, Node x, int index) {
        int parent = index/2;
        int child = index;
        while(parent > 0 && nodes[parent].count > x.count){
            nodes[child] = nodes[parent];
            child = parent;
            parent /=2;
        }
        nodes[child] = x;
    }

    public static Node extract_min(Node[] nodes){
        Node maxNode = count>=1 ? nodes[1] : null;
        if(maxNode != null){
            swap(nodes, 1, count);
            nodes[count--] = null;
            heappfy(nodes, 1);
        }
        return maxNode;
    }


    private static void heappfy(Node[] nodes, int index){
        int left = 2*index;
        int right= 2*index+1;
        int largest = index;
        if(left <= count && nodes[left].count < nodes[largest].count){
            largest = left;
        }
        else{
            largest = index;
        }
        if(right <= count && nodes[right].count < nodes[largest].count){
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
}

class Node{
    int count = 0;
    String character = "";
    Node leftNode = null;
    Node rightNode = null;

    public Node(int count, String character){
        this.count = count;
        this.character = character;
    }
}