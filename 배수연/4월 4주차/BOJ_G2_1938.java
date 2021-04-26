package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_G2_1938 {
	static int N, center_x_end, center_y_end, end_z, ans;
	static char[][] map;
	static boolean[][][] visited; // x, y, z
	static Queue<Tree> queue;
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1}; // 상하좌우 좌상 우상 좌하 우하
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static class Tree {
		int x;
		int y;
		int z;
		int time;
		public Tree(int x, int y, int z, int time) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2];
		List<int[]> tree = new LinkedList<int[]>();
		List<int[]> end = new LinkedList<int[]>();
		queue = new LinkedList<Tree>();
		for(int i = 0 ; i<N ; i++) {
			String line = br.readLine();
			for(int j = 0 ; j<N ; j++) {
				map[i][j] = line.toCharArray()[j];
				if(map[i][j] == 'B') tree.add(new int[] {i, j});
				if(map[i][j] == 'E') end.add(new int[] {i, j});
			}
		}
		
		// 통나무, 목적지 모두 무조건 3칸 짜리이기 때문에, 각 리스트의 1번 index가 중심점이다.
		// 통나무 'B'의 중심점 위치, 놓인 모양(가로인지 세로인지)을 queue에 저장
		int center_x_tree = tree.get(1)[0];
		int center_y_tree = tree.get(1)[1];
		int tree_z = (tree.get(0)[0] == center_x_tree)? 0 : 1; // 가로면 0, 세로면 1
		queue.offer(new Tree(center_x_tree, center_y_tree, tree_z, 0));
		visited[center_x_tree][center_y_tree][tree_z] = true;
		
		// 목적지 'E'의 중심점 위치, 놓인 모양을 전역 변수로 저장
		center_x_end = end.get(1)[0];
		center_y_end = end.get(1)[1];
		end_z = (end.get(0)[0] == center_x_end)? 0 : 1;
		
		BFS();
		System.out.println(ans);
	}

	private static void BFS() {
		while(!queue.isEmpty()) {
			Tree temp = queue.poll();
			// 탐색하는 중심좌표가 목적지와 같고, 방향도 같으면 반복 종료
			if(temp.x == center_x_end && temp.y == center_y_end && temp.z == end_z) {
				ans = temp.time;
				break;
			}
			int nr = 0, nc = 0;
			// UDLR
			for(int d = 0 ; d<4 ; d++) {
				nr = temp.x+dr[d];
				nc = temp.y+dc[d];
				if(nr>-1 && nr<N && nc>-1 && nc<N && !visited[nr][nc][temp.z] && map[nr][nc] != '1') {
					// 가로면 중심 양옆이 가로 범위 안에 있는지 확인
					if(temp.z == 0 && nc-1>-1 && nc+1<N && map[nr][nc-1] != '1' && map[nr][nc+1] != '1') {
						queue.offer(new Tree(nr, nc, temp.z, temp.time+1));
						visited[nr][nc][temp.z] = true;
					}
					// 세로면 중심 위아래가 세로 범위 안에 있는지 확인
					else if(temp.z == 1 && nr-1>-1 && nr+1<N && map[nr-1][nc] != '1' && map[nr+1][nc] != '1') {
						queue.offer(new Tree(nr, nc, temp.z, temp.time+1));
						visited[nr][nc][temp.z] = true;
					}
				}
			}
			
			// T
			boolean canRotate = true;
			int rotateZ = 1-temp.z; // 0이면 1, 1이면 0으로
			if(!visited[temp.x][temp.y][rotateZ]) {
				// 주변 3*3이 비어있어야 하기 때문에 8방 탐색
				for(int d = 0 ; d<8 ; d++) {
					nr = temp.x+dr[d];
					nc = temp.y+dc[d];
					if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc] == '1') {
						canRotate = false;
						break;
					}
				}
			}
			if(canRotate && !visited[temp.x][temp.y][rotateZ]) {
				queue.offer(new Tree(temp.x, temp.y, rotateZ, temp.time+1));
				visited[temp.x][temp.y][rotateZ] = true;
			}
		}
	}
}
