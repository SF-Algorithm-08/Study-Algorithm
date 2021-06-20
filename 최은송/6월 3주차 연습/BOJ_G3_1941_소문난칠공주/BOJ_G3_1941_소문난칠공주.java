package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G3_1941_소문난칠공주 {
    static char[][] map;
    static boolean[] visited;
    static int ans = 0;
    static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][];
        visited = new boolean[25];

        for(int i=0; i<5; i++)
            map[i] = in.readLine().toCharArray();

        dfs(0, 0);
        System.out.println(ans);
    }
    static void dfs(int idx, int cnt){
        if(cnt == 7){
            //이다솜파가 4명 이상인지 체크
            if(checkFour()){
                // 체크한 애들이 인접했는지 체크
                if(checkAdj())  ans++;
            }
            return;
        }
        for(int i=idx; i<25; i++){
            if(visited[i])  continue;
            visited[i] = true;
            dfs(i, cnt+1);
            visited[i] = false;
        }
    }
    static boolean checkFour(){
        int cnt = 0;
        for(int i=0; i<25; i++){
            if(visited[i] && map[i/5][i%5] == 'S') cnt++;
        }
        return cnt >= 4;
    }
    static boolean checkAdj(){
        int cnt = 1;
        Queue<int[]> q = new LinkedList<int[]>();
        boolean[][] visitQ = new boolean[5][5];
        for(int i=0; i<25; i++){
            if(visited[i]){
                q.offer(new int[]{i/5, i%5});
                visitQ[i/5][i%5] = true;
                break;
            }
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                int idx = nr*5 + nc;
                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || !visited[idx] || visitQ[nr][nc]) continue;
                cnt++;
                q.offer(new int[]{nr, nc});
                visitQ[nr][nc] = true;
            }
        }
        return cnt == 7;
    }
}
