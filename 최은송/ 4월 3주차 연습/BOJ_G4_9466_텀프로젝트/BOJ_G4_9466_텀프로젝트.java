package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_9466_텀프로젝트 {
	static int N, parents[], cnt;
	static boolean[] finished, visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine());
			parents = new int[N+1];
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int i=1; i<=N; i++)
				parents[i] = Integer.parseInt(st.nextToken());
			
			cnt = 0;
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					dfs(i);
				}
			}
			System.out.println(N-cnt);
		}
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		int next = parents[cur];
		if(!visited[next]) {
			dfs(next);
		}else if(!finished[next]) {
			// 자기 자신 cur도 카운트해주기 
			cnt++;
			// 사이클 찾아주기 
			for(int v=next; v!=cur; v=parents[v]) {
				cnt++;
			}
		}
		finished[cur] = true;
	}
}
