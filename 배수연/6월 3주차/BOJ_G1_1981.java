package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 배열에서의 이동 - BFS, 투포인터
 * 구글링해서 해결
 * 탐색 가능 여부를 통해 최소값~최대값 범위를 조정
 */

public class BOJ_G1_1981 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> numbers;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		numbers = new ArrayList<Integer>();
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(!numbers.contains(map[i][j])) numbers.add(map[i][j]);
			}
		}
		
		Collections.sort(numbers);
		
		int min = 0, max = 0, ans = Integer.MAX_VALUE;
		while(min<numbers.size() && max<numbers.size()) {
			if(BFS(numbers.get(min), numbers.get(max))) {
				int gap = numbers.get(max) - numbers.get(min);
				ans = Math.min(ans, gap);
				min++;
			} else max++;
		}
		System.out.println(ans);
	}

	private static boolean BFS(int min, int max) {
		if(map[0][0]<min || map[0][0]>max) return false;
		
		Queue<int[]> queue = new LinkedList<int[]>();
		visited = new boolean[N][N];
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			if(temp[0] == N-1 && temp[1] == N-1) return true;
			for(int d = 0 ; d<4 ; d++) {
				int nr = temp[0]+dr[d];
				int nc = temp[1]+dc[d];
				if(nr>-1 && nr<N && nc>-1 && nc<N && !visited[nr][nc]) {
					if(map[nr][nc]>=min && map[nr][nc]<=max) {
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
		}
		return false;
	}
}
