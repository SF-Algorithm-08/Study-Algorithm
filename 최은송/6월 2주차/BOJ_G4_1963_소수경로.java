package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1963_소수경로{
    static int[] prime = new int[10000];
    public static void main(String[] args) throws IOException {
        BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
        eratos();
        int T = Integer.parseInt(in.readLine());
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(end - start == 0) {
                System.out.println(0);
            } else{
                int ans = bfs(start, end);
                System.out.printf("%s\n", ans == -1 ? "Impossible" : Integer.toString(ans));
            }
        }
    }

    public static void eratos(){
        for(int i = 2; i < 10000; i++)
            prime[i] = i;

        for(int i = 2;  i < 10000; i++){
            if(prime[i] == 0) continue;
            for(int j = i + i; j < 10000; j += i)
                prime[j] = 0;
        }
    }

    public static int bfs(int start, int end){
        int ret = -1;
        Queue<int[]> q = new LinkedList<int[]>();
        boolean[] used = new boolean[10000];
        used[start] = true;

        q.offer(new int[]{start, 0});

        while(!q.isEmpty()) {
            int num = q.peek()[0];
            int cnt = q.peek()[1];
            q.poll();
            if (num == end) {
                ret = cnt;
                break;
            }
            StringBuilder n = new StringBuilder(Integer.toString(num));
            for (int i = 0; i < 4; i++) {
                int j = i == 0 ? 1 : 0;
                StringBuilder str = new StringBuilder(n.toString());
                for (; j < 10; j++) {
                    str.setCharAt(i, (char) ('0' + j));
                    num = Integer.parseInt(str.toString());
                    if (used[num] || prime[num] == 0) continue;
                    used[num] = true;
                    q.offer(new int[]{num, cnt + 1});
                }
            }
        }
        return ret;
    }
}
/*

**/
