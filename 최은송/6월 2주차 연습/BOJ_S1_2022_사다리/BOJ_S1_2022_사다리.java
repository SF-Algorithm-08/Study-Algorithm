package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_2022_사다리 {
    static double x, y, c;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        double left = 0, right = Math.min(x, y);
        double mid = 0;
        while(left+0.000001 < right){
            mid = (left + right) / 2.0;
            if(findC(mid) >= c){
                left = mid + 0.0001;
            }else{
                right = mid - 0.0001;
            }
        }
        System.out.printf("%.3f\n", mid);
    }
    static double findC(double w){
        double h1 = Math.sqrt(x*x - w*w);
        double h2 = Math.sqrt(y*y - w*w);
        double _c = (h1 * h2) / (h1 + h2);
        return _c;
    }
}
