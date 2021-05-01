package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_14391 {
	static int N, M, ans;
	static int[][] map, visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for(int n = 0 ; n<N ; n++) {
			String line = br.readLine();
			for(int m = 0, k = 0 ; m<M ; m++, k++) {
				map[n][m] = line.charAt(k)-'0';
			}
		}
		ans = 0;
		DFS(0, 0);
		System.out.println(ans);
	}

	private static void DFS(int r, int c) {
		if(r == N) { // visited에 1인 경우는 가로로 이어진다는 뜻 => 행으로 더하기 / 2면 세로 => 열로 더하기
			ans = Math.max(ans, getSum());
			return;
		}
		if(c == M) { // 다음 행으로 넘기기
			DFS(r+1, 0);
			return;
		}
		
		// 행 단위로 탐색하면서 c열을 가로로 쓸지 세로로 쓸지 결정
		visited[r][c] = 1;
		DFS(r, c+1);
		visited[r][c] = 2;
		DFS(r, c+1);
	}

	private static int getSum() {
		int sum = 0;
		int idx = 0;
		for(int n = 0 ; n<N ; n++) {
			idx = 0;
			for(int m = 0 ; m<M ; m++) {
				if(visited[n][m] == 1) idx = idx*10+map[n][m];
				else {
					sum += idx;
					idx = 0;
				}
			}
			sum += idx;
		}
		
		for(int m = 0 ; m<M ; m++) {
			idx = 0;
			for(int n = 0 ; n<N ; n++) {
				if(visited[n][m] == 2) idx = idx*10+map[n][m];
				else {
					sum += idx;
					idx = 0;
				}
			}
			sum += idx;
		}
		return sum;
	}

}
