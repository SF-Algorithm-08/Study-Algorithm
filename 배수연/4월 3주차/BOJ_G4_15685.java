package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 드래곤 커브
 */
// 0세대 : 0
// 1세대 : 0 | 1
// 2세대 : 0 1 | 2 1
// 3세대 : 0 1 2 1 | 2 3 2 1
// n세대 : (n-1세대 그대로) | (n-1세대 역순으로 +1)

public class BOJ_G4_15685 {
	static boolean[][] map = new boolean[101][101];
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		for(int n = 0 ; n<N ; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			dragon(y, x, d, g);
		}
		
		int ans = 0;
		for(int i = 0 ; i<100 ; i++) {
			for(int j = 0 ; j<100 ; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) ans++;
			}
		}
		System.out.println(ans);
	}

	private static void dragon(int x, int y, int d, int g) {
		map[x][y] = true;
		List<Integer> dirList = new LinkedList<Integer>();
		dirList.add(d);
		for(int i = 0 ; i<g ; i++) {
			for(int j = dirList.size()-1 ; j>=0 ; j--) {
				int next_dir = (dirList.get(j)+1)%4;
				dirList.add(next_dir);
			}
		}
		for (Integer idx : dirList) {
			x += dr[idx];
			y += dc[idx];
			map[x][y] = true;
		}
	}

}
