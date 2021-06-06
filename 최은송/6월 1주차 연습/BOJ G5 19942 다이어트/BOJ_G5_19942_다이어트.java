package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_19942_다이어트 {
	static int N, nutri[], arr[][], minCost;
	static StringBuilder result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		nutri = new int[4];
		arr = new int[N][5];
		result = new StringBuilder();
		minCost = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < 4; i++) 
			nutri[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 5; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());	
		}
		combi(0, new StringBuilder(), new int[5]);
		if(minCost == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			String ans = result.toString().trim();
			System.out.println(minCost);
			System.out.println(ans);
		}
	}
	static void combi(int idx, StringBuilder flag, int cost[]) {
		if(cost[4] > minCost)	return;
		
		if(idx == N) {
			boolean isOver = true;
			for(int i = 0; i < 4; i++) {
				if(cost[i] < nutri[i]) {
					isOver = false;
					break;
				}
			}
			if(isOver) {
				if(cost[4] < minCost) {
					result = flag;
					minCost = cost[4];
				}else if(cost[4] == minCost && makeString(flag).compareTo(makeString(result)) < 0 ? true : false) {
					result = flag;
					minCost = cost[4];
				}
			}
			return;
		}
		StringBuilder newFlag = new StringBuilder(flag).append((idx+1) + " ");
		//현재 idx 선택 안하는 경우 
		combi(idx+1, flag, cost);
		//현재 idx 선택 하는 경우
		for(int i = 0; i < 5; i++)	cost[i] += arr[idx][i];
		combi(idx+1, newFlag, cost);
		for(int i = 0; i < 5; i++)	cost[i] -= arr[idx][i];
	}
	
	static String makeString(StringBuilder str) {
		return str.toString().replace(" ", "");
	}
}
/*
6
100 70 90 10
30 55 10 8 100
60 10 10 2 70
10 80 50 0 50
40 30 30 8 60
60 10 70 2 120
20 70 50 4 40
*/


