package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_1976_여행가자 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[] visited;
	static int[] tour;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		in = new BufferedReader(new StringReader(input));
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		visited = new boolean[N];
		tour = new int[M];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i < M; i++)
			tour[i] = Integer.parseInt(st.nextToken()) - 1;
		
		dfs(tour[0]);
		for(int i=0; i<M; i++) {
			if(!visited[tour[i]]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static void dfs(int city) {
		visited[city] = true;
		
		for(int i=0; i<N; i++) {
			if(!visited[i] && map[city][i] == 1) {
				dfs(i);
			}
		}
	}
	
//	static String input = "3\n" + 
//			"3\n" + 
//			"0 1 0\n" + 
//			"1 0 1\n" + 
//			"0 1 0\n" + 
//			"1 2 3";
	static String input =
			"5\n" + 
			"5\n" + 
			"0 1 0 1 1\n" + 
			"1 0 1 1 0\n" + 
			"0 1 0 0 0\n" + 
			"1 1 0 0 0\n" + 
			"1 0 0 0 0\n" + 
			"5 3 2 4 1";
}
