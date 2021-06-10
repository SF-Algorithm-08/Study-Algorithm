import java.io.*;
import java.util.*;

public class Main_14502 {

    private static int N, M,answer;
    private static int[][] board, copyMap;
    private static int[] dy = {0,0,1,-1};
    private static int[] dx = {1,-1,0,0};
    private static Queue<Pair> q;

    private static class Pair {
        int y;
        int x;
        public Pair(int y,int x) {
            this.y=y;
            this.x=x;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    for (int k = 0; k < N; k++)
                        for (int l = 0; l < M; l++)
                            copyMap[k][l] = board[k][l];
                    copyMap[i][j] = 1;
                    makeWall(1);
                    copyMap[i][j] = 0;
                }

            }
        }
        System.out.println(answer);

    }

    private static void makeWall(int cnt) {

        if (cnt == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    copyMap[i][j] = 1;
                    makeWall(cnt + 1);
                    copyMap[i][j] = 0;
                }
            }
        }

    }

    private static void bfs() {
        int[][] visit = new int[N][M];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                visit[i][j] = copyMap[i][j];
                if (visit[i][j] == 2) q.add(new Pair(i,j));
            }
        }

        while (!q.isEmpty())
        {
            int cy = q.peek().y;
            int cx = q.peek().x;
            q.poll();
            for (int i = 0; i < 4; i++)
            {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if (nx < 0 || ny<0 || ny >= N || nx >= M) continue;
                if (visit[ny][nx] == 0) {
                    visit[ny][nx] = 2;
                    q.add(new Pair(ny,nx));;
                }
            }
        }
        int cnt = 0;

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (visit[i][j] == 0) cnt++;
            }
        }
        answer = Math.max(cnt,answer);
    }
}
