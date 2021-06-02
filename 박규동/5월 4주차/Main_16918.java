package com.company;

import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dy = {1, -1, 0, 0,};
    private static final int[] dx = {0, 0, 1, -1};
    private static char[][] board;
    private static int[][] bomb;
    private static int R,C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N;
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        bomb = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
                if(board[i][j]=='O') bomb[i][j] = 3;
            }
        }
        solve(N);
        printAnswer();
    }

    private static void solve(int N) {

        int time=1;
        while(time<=N) {
            exploding(time);
            if(time%2==0) putBomb(time);
            time++;
        }
    }

    private static void exploding(int time) {
        boolean visit[][] = new boolean[R][C];
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(bomb[i][j]==time) {
                    visit[i][j]=true;
                    for(int k=0;k<4;k++) {
                        int ny = i+dy[k];
                        int nx = j+dx[k];
                        if(ny<0||nx<0||ny>=R||nx>=C) continue;
                        visit[ny][nx]=true;
                    }
                }
            }
        }

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(visit[i][j]) bomb[i][j]=0;
            }
        }
    }

    private static void putBomb(int time) {
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++)  {
                if(bomb[i][j]==0) bomb[i][j] = time+3;
            }
        }

    }


    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(bomb[i][j]==0) sb.append('.');
                else sb.append('O');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

}
