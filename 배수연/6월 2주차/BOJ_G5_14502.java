package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소 - 구현, BFS
 * 입력받으면서 바이러스의 위치를 저장
 * 전체 0~N*M-1개 중 3개의 점을 선택해서 벽 세우기
 * 초기 바이러스 위치 기준으로 BFS 탐색하면서 바이러스 퍼트리기
 * 
 * 안전 영역 구하기 : 
 * idx==3일 때 초기화해주고, 마지막에 map에 2 퍼트려가면서 0인 부분 카운트하기 => 어디선가 잘못됨...
 * 전체 map 크기 - 초기 벽 개수 - 3 - 바이러스 개수
 */

public class BOJ_G5_14502 {
	static int N, M, wall, ans;
	static int[][] map, tempMap;
	static boolean[][] visited;
	static List<int[]> virus;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		wall = 0;
		ans = 0;
		map = new int[N][M];
		tempMap = new int[N][M];
		virus = new LinkedList<int[]>();
		for(int n = 0 ; n<N ; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0 ; m<M ; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				tempMap[n][m] = map[n][m];
				if(map[n][m] == 1) wall++;
				if(map[n][m] == 2) virus.add(new int[] {n, m});
			}
		}
		// 재귀로 벽 세우기
		setWall(0);
		System.out.println(ans);
	}

	private static void setWall(int idx) {
		if(idx == 3) {
			// 바이러스 퍼뜨리기
			spreadVirus();
			return;
		}
		// 0~N*M 중에서 3개 고르기
		for(int i = 0 ; i<N*M ; i++) {
			if(map[i/M][i%M] == 0) {
				map[i/M][i%M] = 1;
				setWall(idx+1);
				map[i/M][i%M] = 0;
			}
		}
	}
	
	private static void spreadVirus() {
		visited = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<int[]>();
		int virusCnt = virus.size();
		for(int i = 0 ; i<virus.size() ; i++) {
			int[] temp = virus.get(i);
			queue.offer(new int[] {temp[0], temp[1]});
			while(!queue.isEmpty()) {
				int[] tempV = queue.poll();
				int temp_x = tempV[0];
				int temp_y = tempV[1];
				for(int d = 0 ; d<4 ; d++) {
					int nr = temp_x+dr[d];
					int nc = temp_y+dc[d];
					if(nr>-1 && nr<N && nc>-1 && nc<M && !visited[nr][nc] && map[nr][nc] == 0) {
						visited[nr][nc] = true;
						queue.offer(new int[] {nr, nc});
						virusCnt++;
					}
				}
			}
		}
		// 안전 영역 구하기
		ans = Math.max(ans, N*M-wall-3-virusCnt);
	}
}
