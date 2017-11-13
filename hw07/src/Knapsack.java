import java.util.*;
public class Knapsack {
    static Scanner scanner = new Scanner(System.in);
    static Item[] items;
    static int[][] answer;
    static int N, W;
    public static void main(String[] args) {
        N = scanner.nextInt();
        W = scanner.nextInt();
        inputItems();
        solve();
        printMaxItems();
    }

    private static void printMaxItems() {
        System.out.println("max : "+ answer[N][W]);
        System.out.print("item : ");
        int i=N, j=W;
        while(i!=0 && j!=0){
            if(answer[i][j] != answer[i-1][j]){
                System.out.print(i+" ");
                j = j - items[i].weight;
            }
            i = i-1;
        }
    }

    private static void solve() {
        for(int i=0; i<=N; i++){
            for(int j=0; j<=W; j++){
                if(i == 0){
                    answer[i][j] = 0;
                }
                else if(items[i].weight > j){
                    answer[i][j] = answer[i-1][j];
                }
                else{
                    answer[i][j] = Integer.max(answer[i-1][j], answer[i-1][j-items[i].weight] + items[i].value);
                }
                System.out.printf("\t"+ answer[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private static void inputItems() {
        items = new Item[N+1];
        answer = new int[N+1][W+1];
        for(int i=1; i<=N; i++){
            int inputValue = scanner.nextInt();
            int inputWeight = scanner.nextInt();
            items[i] = new Item(inputValue, inputWeight);
        }
    }

}

class Item{
    int value;
    int weight;

    public Item(int value, int weight){
        this.value = value;
        this.weight = weight;
    }
}
