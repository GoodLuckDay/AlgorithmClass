import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class ClosestPairOfPoint {
    static final int file_size = 100;
    public static void main(String[] args) throws IOException {
        Point[] array = new Point[file_size];
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get("./closest dataSet/closest_"+file_size+".in").toString()));
        int count = 0;
        String str;
        while((str = bufferedReader.readLine()) != null){
            String[] positions = str.split(" ");
            array[count++] = new Point(Double.parseDouble(positions[0]), Double.parseDouble(positions[1]));
        }

        QuickSort(array,0, file_size-1, false);
        System.out.println(ClosestPair(array, 0, file_size-1));
    }

    private static Double ClosestPair(Point[] array, int left, int right) {
        int size = right - left + 1;
        int mid = (left + right) / 2;
        if(size <= 3){
            return bruteforce(array,left, right);
        }

        double leftMin = ClosestPair(array, left, mid);
        double rightMin = ClosestPair(array,mid+1 ,right);
        double delta = Double.min(leftMin, rightMin);

        Point[] separatedPoint = new Point[size];
        int separatedCount = 0;

        for(int i=left; i<=right; i++){
            if(array[i].xPosition - array[mid].xPosition <= delta){
                separatedPoint[separatedCount++] = array[i];
            }
        }

        QuickSort(separatedPoint, 0, separatedCount-1, true);

        for(int i=0; i<separatedCount; i++){
            for(int j=i+1; j<=separatedCount; j++){
                double distance = getDistance(array[i], array[j]);
                if( distance < delta){
                    delta = Double.min(delta,distance);
                }
            }
        }

        return delta;

    }

    private static Double bruteforce(Point[] array, int left, int right) {
        Double min = Double.MAX_VALUE;
        for(int i=left; i<right; i++){
            for(int j=i+1; j<=right; j++){
                double distance = getDistance(array[i], array[j]);
                if(min > distance){
                    min = distance;
                }
            }
        }
        return min;

    }

    private static double getDistance(Point point1, Point point2){
        return Math.sqrt(Math.pow(point1.xPosition - point2.xPosition,2) + Math.pow(point1.yPosition - point2.yPosition,2));
    }

    private static void QuickSort(Point[] array, int left, int right, boolean y_coordinate) {
        if(left < right){
            int pivot = partition(array,left, right, y_coordinate);
            QuickSort(array,left, pivot-1, y_coordinate);
            QuickSort(array,pivot+1, right, y_coordinate);
        }
    }

    private static int partition(Point[] array, int left, int right, boolean isYvalue) {
        int i = left;
        int j = right-1;
        double pivot = (isYvalue ? array[i].yPosition : array[i].xPosition);

        while(i <= j){
            if((isYvalue ? array[i].yPosition : array[i].xPosition) < pivot){
                i++;
            }
            else if((isYvalue ? array[i].yPosition : array[i].xPosition) > pivot){
                j--;
            }
            else{
                swap(array, i, j);
                i++;
                j--;
            }
        }
        swap(array, right, i);
        return i;

    }

    private static void swap(Point[] array, int left, int right){
        Point temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

}

class Point{
    double xPosition;
    double yPosition;

    public Point(double x, double y){
        xPosition = x;
        yPosition = y;
    }
}
