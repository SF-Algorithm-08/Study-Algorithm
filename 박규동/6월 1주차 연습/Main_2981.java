import java.util.*;
import java.io.*;
public class Main_2981 {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        for(int i=0;i<N;i++) {

        }

    }

    private static int gcd(int x, int y) {
        if(x%y==0) return y;
        else return gcd(y,x%y);
    }
}
