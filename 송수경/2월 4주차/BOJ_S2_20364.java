package boj;
//S2 부동산 다툼(트리)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_20364 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());	//땅 개수
		int Q = Integer.parseInt(st.nextToken());	//오리 수
		
		boolean[] visited = new boolean[N+1];	//이미 방문했는지 체크
		int goal;	//오리가 원하는 땅번호
		int pre;	//검사할 부모노드
		for(int i=0; i<Q; i++) {
			goal = Integer.parseInt(br.readLine().trim());
			int ans=0; pre=goal;
			while(pre!=0) {	//루트까지 검사
				if(visited[pre]) ans=pre;
				pre /= 2;
			}
			if(ans==0) visited[goal]=true;
			
			System.out.println(ans);
		}
	}
}
