package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 인구 이동 - BFS, 시뮬레이션
 * map의 원점부터 탐색하면서 방문 안했고&연합국인지 확인
 * 연합국이 없게 되면 그때까지 몇 바퀴 돌았는지 확인
 * 
 * 연합국 판별하고 map값 조절은 BFS에서 해주기
 * 일반적으로 사용하는 queue로는 탐색을 위해서 사용
 * list에 연합국들의 좌표 정보를 넣어두기
 * 연합을 만들었으면 값 더해주고 연합국 개수로 나눠주기
 */
public class BOJ_G5_16234 {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 반복 돌면서 연합국이 존재했는지 확인
		int ans = 0;
		while(true) {
			visited = new boolean[N][N];
			boolean canMove = false;
			for(int i = 0 ; i<N ; i++) {
				for(int j = 0 ; j<N ; j++) {
					if(!visited[i][j] && isMerged(i, j)) canMove = true;
				}
			}
			// 인구이동이 있었다면 ans++, 없으면 반복 종료하고 출력
			if(canMove) ans++;
			else break;
		}
		System.out.println(ans);
	}

	private static boolean isMerged(int i, int j) {
		// BFS 반복 돌릴 queue / 한 연합임을 알려주는 merge
		// (i, j) 기준으로 상하좌우 판단하면서 연합국인지를 판단
		Queue<int[]> queue = new LinkedList<int[]>();
		List<int[]> merge = new LinkedList<int[]>();
		queue.offer(new int[] {i, j});
		merge.add(new int[] {i, j});
		visited[i][j] = true;
		int sum = map[i][j];
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int temp_r = temp[0];
			int temp_c = temp[1];
			for(int d = 0 ; d<4 ; d++) {
				int nr = temp_r+dr[d];
				int nc = temp_c+dc[d];
				if(nr>-1 && nr<N && nc>-1 && nc<N && !visited[nr][nc]) {
					int gap = Math.abs(map[temp_r][temp_c]-map[nr][nc]);
					// 좌우/상하 차이가 L 이상 R 이하인지 확인
					if(L <= gap && gap <= R) {
						// 범위 내에 있다면 연합으로 묶어주기
						queue.offer(new int[] {nr, nc});
						merge.add(new int[] {nr, nc});
						visited[nr][nc] = true;
						sum += map[nr][nc];
					}
				}
			}
		}
		
		// 연합된게 없다면 return
		if(merge.size() == 1) return false;
		
		// (연합의 인구 합)/(연합 칸 수) 해서 연합 칸들에 각각 넣어주기
		int temp = sum/merge.size();
		for (int[] pos : merge) {
			map[pos[0]][pos[1]] = temp;
		}
		return true;
	}
}
