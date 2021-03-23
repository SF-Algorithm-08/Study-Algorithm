package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_G3_2533_사회망서비스 {
	static LinkedList<Integer> adjList[];
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new StringReader(input));
		
		int N = Integer.parseInt(in.readLine());
		
		adjList = new LinkedList[N+1];
		for(int i=0; i<N+1; i++)
			adjList[i] = new LinkedList<Integer>();
		dp = new int[N+1][2];
		
		StringTokenizer st = null;
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		dp(1, 0);
		System.out.printf("%d\n", Math.min(dp[1][0], dp[1][1]));
		
	}
	
	static void dp(int cur, int parent) {
		dp[cur][0] = 0;		// 현재 노드가 얼리어답터가 아님 
		dp[cur][1] = 1;		// 현재 노드가 얼리어답터임 
		
		for(Integer node : adjList[cur]) {
			if(node != parent) {
				dp(node, cur);
				// 현재 노드가 얼리어답터 => 친구가 얼리어답트 일수도, 아닐수도 
				dp[cur][1] += Math.min(dp[node][0], dp[node][1]);
				// 현재 노드가 얼리어답터가 아님 => 친구가 얼리어답터여만 함 
				dp[cur][0] += dp[node][1];
			}
		}
	}
	
	static String input = "8\n" + 
			"1 2\n" + 
			"1 3\n" + 
			"1 4\n" + 
			"2 5\n" + 
			"2 6\n" + 
			"4 7\n" + 
			"4 8";
}
