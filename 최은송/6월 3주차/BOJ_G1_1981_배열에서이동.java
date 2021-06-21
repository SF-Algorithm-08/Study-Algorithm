package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_1981_배열에서이동 {
    static int N, map[][], max, min, ans;
    static int[] dr = {0,0,-1,1}, dc = {1,-1,0,0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        min = 200; max = 0; ans = 200;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        int end = max;
        max = map[0][0];
        while(min <= map[0][0] && max <= end){
            visited = new boolean[N][N];
            if(dfs(0,0)) {
                ans = Math.min(ans, max-min);
                min++;
            }
            else{
                max++;
            }
        }
        System.out.println(ans);
    }

    static boolean dfs(int r, int c){
        if(r == N-1 && c == N-1) return true;
        visited[r][c] = true;

        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] < min || map[nr][nc] > max) continue;
            if(dfs(nr, nc)) return true;
        }
        return false;
    }
}
