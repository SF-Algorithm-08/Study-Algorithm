package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_1389_케빈베이컨의6단계법칙 {
    static int N, M, arr[][];
    static final int INF = 101;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++)
            Arrays.fill(arr[i], INF);

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] = 1;
        }
        floyd_warshall();
        int min = INF;
        int sum[] = new int[N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                sum[i] += arr[i][j];
            }
            if(sum[i] <= min)    min = sum[i];
        }
        for(int i=1; i<=N; i++){
            if(sum[i] == min) {
                System.out.println(i);
                break;
            }
        }
    }
    static void floyd_warshall(){
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(arr[i][k] + arr[k][j] < arr[i][j])
                        arr[i][j] = arr[i][k] + arr[k][j];
                }
            }
        }
    }
}
