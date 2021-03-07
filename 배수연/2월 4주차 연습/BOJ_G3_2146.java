package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G3_2146 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int min;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int n = 0 ; n<N ; n++) {
			String line = br.readLine();
			for(int c = 0, k = 0 ; c<N ; c++, k+=2) {
				map[n][c] = line.charAt(k)-'0';
			}
		}
		// 다른 덩어리 섬마다 다른 숫자 붙여주기 (섬 전체에 넘버링 해줄 필요는 없고 가장자리에만 넘버링해주면 더 좋을듯)
		int island = 2;
		for(int i = 0 ; i<N ; i++) {
			for(int j = 0 ; j<N ; j++) {
				if(map[i][j] == 1) {
					diffIsland(i, j, island);
					island++;
				}
			}
		}
//		for(int i = 0 ; i<N ; i++) {
//			for(int j = 0 ; j<N ; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		// BFS로 길이 확인
		min = Integer.MAX_VALUE;
		for(int land = 2 ; land<island ; land++) {
			for(int v = 0 ; v<N ; v++) {
				Arrays.fill(visited[v], false);
			}
			min = Math.min(min, BFS(land));
		}
		System.out.println(min);
		
	}
	public static void diffIsland(int r, int c, int island) { // 상하좌우에 하나라도 1이 있으면 같은 덩어리로 취급
		map[r][c] = island;
		int nr = 0, nc = 0;
		for(int d = 0 ; d<4 ; d++) {
			nr = r+dr[d];
			nc = c+dc[d];
			if(nr>-1 && nr<N && nc>-1 && nc<N && map[nr][nc] == 1) {
				diffIsland(nr, nc, island);
			}
		}
	}
	public static int BFS(int land) {
		Queue<int[]> queue = new LinkedList<int[]>();
		int len = 0;
		for(int r = 0 ; r<N ; r++) {
			for(int c = 0 ; c<N ; c++) {
				if(map[r][c] == land && !visited[r][c]) {
					queue.offer(new int[] {r, c, 0});
//					visited[r][c] = true;
				}
			}
		}
		int x = 0, y = 0, nx = 0, ny = 0;
		while(!queue.isEmpty()) {
//			System.out.println(Arrays.toString(queue.peek()));
			int[] temp = queue.poll();
			x = temp[0];
			y = temp[1];
			len = temp[2];
			for(int d = 0 ; d<4 ; d++) {
				nx = x+dr[d];
				ny = y+dc[d];
				if(nx>-1 && nx<N && ny>-1 && ny<N && !visited[nx][ny]) {
					if(map[nx][ny] != 0 && map[nx][ny] != land) {
						return len;
					}
					if(map[nx][ny] == 0) { // 육지가 아닌 경우
						visited[nx][ny] = true;
						queue.offer(new int[] {nx, ny, len+1});
					}
				}
			}
		}
		return -1;
	}

}
/*
10
1 1 1 0 0 0 0 1 1 1
1 1 1 1 0 0 0 0 1 1
1 0 1 1 0 0 0 0 1 1
0 0 1 1 1 0 0 0 0 1
0 0 0 1 0 0 0 0 0 1
0 0 0 0 0 0 0 0 0 1
0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 1 0 0 0 0
0 0 0 0 1 1 1 0 0 0
0 0 0 0 0 0 0 0 0 0
*/
// 3
