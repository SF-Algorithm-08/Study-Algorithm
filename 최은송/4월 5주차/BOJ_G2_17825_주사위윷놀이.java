package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_17825_주사위윷놀이 {
	static int horse[], dice[], max = 0, score[], path[], corner[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		dice = new int[10];
		for(int i=0; i<10; i++)
			dice[i] = Integer.parseInt(st.nextToken());
		
		horse = new int[4];			//말들의 위치 저장 
		visited = new boolean[33];	//방문체크 
		score = new int[33];		//idx별 점수 저장 
		path = new int[33];			//말이 이동할 다음 idx 저장 
		corner = new int[33];		//10, 20, 30일 때 다음 idx 저장 
		for(int i = 0; i < 21; i++) {
			path[i] = i+1;
			score[i] = i * 2;
		}
		path[21] = 21; 
		
		for(int i = 22; i < 27; i++)
			path[i] = i+1;
		path[27] = 20; path[28] = 29; path[29] = 30; 
		path[30] = 25; path[31] = 32; path[32] = 25;
		
		score[22] = 13; score[23] = 16; score[24] = 19;
		score[25] = 25; score[26] = 30; score[27] = 35;
		score[28] = 28; score[29]= 17; score[30] = 26; 
		score[31] = 22; score[32] = 24;
		
		corner[5] = 22; corner[10] = 31; corner[15] = 28;
		dfs(0, 0);
		System.out.println(max);
	}
	

	static void dfs(int cnt, int sum) {
		if(cnt == 10) {
			max = Math.max(max, sum);
//			System.out.printf("---------------cnt == 10 -----------");
//			System.out.printf("sum:%d\n", sum);
			return;
		}
		for(int i = 0; i < 4; i++) {
			int d = dice[cnt];
//			System.out.printf("\nd: %d, horse: %d\n",d,i);
			int prev = horse[i];
			int cur = horse[i];
			// 현재 위치가 10, 20, 30이라면 꺾어준다. 
			if(corner[cur] > 0) {
				cur = corner[cur];
				d--;
			}
			// 말을 이동 
			while(d-- > 0) {
				cur = path[cur];
			}
//			System.out.printf("prev: %d, cur:%d, score: %d\n", prev, cur, score[cur]);
			// 도착지가 아닌데 이미 말이 있을 때 
			if(cur != 21 && visited[cur])	continue;
			
			visited[prev] = false;
			visited[cur] = true;
			horse[i] = cur;
			
			dfs(cnt+1, sum + score[cur]);
			visited[prev] = true;
			visited[cur] = false;
			horse[i] = prev;
		}
	}
}

