package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_금고털기 {
    static char[] start, end;
    static int[][] time = {{1, 1}, {3, 2}, {5, 4}, {7, 6}};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for(int t = 1; t <= T; t++){
            start = new char[4];
            end = new char[4];
            start = in.readLine().trim().toCharArray();
            end = in.readLine().trim().toCharArray();
            System.out.printf("#%d %d\n", t, solve());
        }
    }
    static int solve(){
        int sum = 0;
        int clock, cclock;
        for(int i = 0; i < 4; i++){
            //시계방향    start가 end보다 크거나 같으면 start-end, 아니면 start부터 A까지 돌아서 end로
            clock = end[i] <= start[i] ? start[i] - end[i] : start[i] + 26 - end[i];
            clock *= time[i][0];
            //반시계방향  end가 start보다 크거나 같으면 end-start, 아니면 start부터 Z까지 돌아서 end로
            cclock = start[i] <= end[i] ? end[i] - start[i] : 26 - start[i] + end[i];
            cclock *= time[i][1];

            sum += Math.min(clock, cclock);
        }
        return sum;
    }
}
/**
 5
 FDSA
 JATY
 AAAA
 ZZZZ
 SSSS
 SSSS
 ZXKC
 QWWW
 YTUY
 ZIQZ
 * */