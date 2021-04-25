package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_1043_거짓말 {
	static List<Integer> party[], adj[];
	static boolean[] visited, truth;
	static int N, M, cnt = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		party = new LinkedList[M];
		for(int i=0; i<M; i++) 
			party[i] = new LinkedList<Integer>();
		adj = new LinkedList[N+1];
		for(int i=0; i<N+1; i++) 
			adj[i]  = new LinkedList<Integer>();
			
		visited = new boolean[N+1];
		truth = new boolean[N+1];
		
		st = new StringTokenizer(in.readLine());
		int num = Integer.parseInt(st.nextToken());
		for(int i=0; i<num; i++) {
			truth[Integer.parseInt(st.nextToken())] = true;
		}
		
		for(int i = 0; i <M; i++) {
			st = new StringTokenizer(in.readLine());
			num = Integer.parseInt(st.nextToken());
			// 파티 케이스 저장 
			for(int j=0; j<num; j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
			// 인접한 정점들 전부 추가 
			for(int j=0; j<num; j++) {
				int cur = party[i].get(j);
				for(int k=0; k<num; k++) {
					if(j != k) {
						adj[cur].add(party[i].get(k));
					}
				}
			}
		}
		// 진실을 아는 정점들 구하기 
		for(int i=1; i<=N; i++) {
			if(truth[i] && !visited[i]) {
				dfs(i);
			}
		}
//		System.out.println(Arrays.toString(truth));
		
		for(int i=0; i<M; i++) {
			boolean flag = false;
			for(Integer v : party[i]) {
				if(truth[v]) {
					flag = true;
					break;
				}
			}
			if(!flag) cnt++;
		}
		System.out.println(cnt);
	}
	static void dfs(int v) {
		visited[v] = true;
		for(Integer next : adj[v]) {
			if(!visited[next]) {
				truth[next] = true;
				dfs(next);
			}
		}
	}
}
