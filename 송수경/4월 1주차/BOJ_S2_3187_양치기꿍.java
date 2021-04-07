package boj.Silver;
// S2 양치기 꿍 (너비 우선 탐색,깊이 우선 탐색,그래프 이론,그래프 탐색)
// solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_3187_양치기꿍 {
	static char[][] map;
	static boolean[][] visited;
	static int K, V;	//양, 늑대
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
//		1. map 입력받기
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) map[i] = br.readLine().trim().toCharArray();
		
//		2. 행, 열 순서대로 탐색하며 늑대 혹은 양 찾기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++)
				if((map[i][j]=='v' || map[i][j]=='k') && !visited[i][j]) bfs(i, j);	//늑대나 양이고 방문안했으면 bfs탐색
		}
		
//		5. 정답 출력
		System.out.printf("%d %d", K, V);
	}

	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	static void bfs(int i, int j) {
		int curK=0, curV=0;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {i, j});
		visited[i][j] = true;
		
//		3. bfs
		int[] cur;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			if(map[cur[0]][cur[1]]=='k') curK++;
			else if(map[cur[0]][cur[1]]=='v') curV++;
			
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = cur[0]+dir[d][0];
				nc = cur[1]+dir[d][1];
				
				if(visited[nr][nc] || map[nr][nc]=='#') continue;
				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
		
//		4. 양, 늑대 수 갱신
		if(curK > curV) K += curK;
		else V += curV;
	}
}
