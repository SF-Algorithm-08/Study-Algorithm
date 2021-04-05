package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2666_벽장문의이동 {
	static int[] use;
	static int L, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		//열린 문
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int open1 = Integer.parseInt(st.nextToken())-1;
		int open2 = Integer.parseInt(st.nextToken())-1;
		//벽장문 이동 입력
		L = Integer.parseInt(br.readLine().trim());
		use = new int[L];
		for(int i=0; i<L; i++) use[i] = Integer.parseInt(br.readLine().trim())-1;
		
		ans = Integer.MAX_VALUE;
		dfs(0, 0, open1, open2);	//이동 횟수, use인덱스, 현재 열린 문
		
		System.out.println(ans);
	}

	static void dfs(int move, int idx, int o1, int o2) {
		if(idx==L) {
			ans = Math.min(ans, move);
			return;
		}
		
		if(ans<=move) return;
		
		int diff1 = Math.abs(use[idx]-o1);
		int diff2 = Math.abs(use[idx]-o2);
		
		dfs(move+diff1, idx+1, use[idx], o2);
		dfs(move+diff2, idx+1, o1, use[idx]);
	}
}
