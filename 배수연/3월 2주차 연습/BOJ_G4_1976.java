package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1976 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] connect = new int[N][N];
		int[] plan = new int[M];
		String ans = "YES";
		
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				connect[i][j] = Integer.parseInt(st.nextToken());
			}
			connect[i][i] = 1;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<M ; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0 ; i<N ; i++) {
			for(int j = 0 ; j<N ; j++) {
				for(int k = 0 ; k<N ; k++) {
					if(connect[j][i] == 1 && connect[i][k] == 1) connect[j][k] = 1;
				}
			}
		}
		
		int start = 0, end = 0;
		for(int i = 0 ; i<M-1 ; i++) {
			start = plan[i]-1;
			end = plan[i+1]-1;
			if(connect[start][end] == 0) {
				ans = "NO";
				break;
			}
		}
		
		sb.append(ans);
		System.out.println(sb.toString());
	}

}

/*
3
3
0 1 0
1 0 1
0 1 0
1 2 3
*/