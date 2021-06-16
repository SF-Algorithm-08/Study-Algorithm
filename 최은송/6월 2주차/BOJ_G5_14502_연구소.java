package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_14502_연구소 {
    static int map[][], N, M, ans;
    static int dr[] = {-1,1,0,0}, dc[] = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = 0;
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combi(0);
        System.out.println(ans);
    }

    static void combi(int cnt){
        if(cnt == 3){
            int copy[][] = new int[N][M];
            for(int i = 0; i < N; i++)
                System.arraycopy(map[i], 0, copy[i], 0, M);

            ans = Math.max(ans, bfs(copy));

            return;
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0){
                    map[i][j] = -1;
                    combi(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static int bfs(int[][] copy){
        Deque<int[]> q = new LinkedList<int[]>();
        boolean visited[][] = new boolean[N][M];

        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                if(copy[i][j] == 2)  q.offer(new int[]{i, j});

        int pos[] = q.peekFirst();
        visited[pos[0]][pos[1]] = true;

        while(!q.isEmpty()){
            pos = q.poll();

            for(int i = 0; i < 4; i++){
                int nr = pos[0] + dr[i];
                int nc = pos[1] + dc[i];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M || copy[nr][nc] != 0 || visited[nr][nc])  continue;
                copy[nr][nc] = 2;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(copy[i][j] == 0)  cnt++;
            }
        }
        return cnt;
    }
}
