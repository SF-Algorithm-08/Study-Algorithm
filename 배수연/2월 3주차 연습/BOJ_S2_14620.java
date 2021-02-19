package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 입력받기
 * 조합으로 N*NC3를 구한다.
 * 좌표로 나타내기 -> i=value/N, j = value%N
 * (i, j)의 근처 12칸에는 다른 씨앗이 존재하면 안된다
 * 이런식으로 가능한 조합에서의 값들의 합을 구하고, 마지막으로 최소값 구해주기
 */
public class BOJ_S2_14620 {
	static int N, cost;
	static int[][] map;
	static int[][] checked;
	static int[] numbers;
//	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1, -2, 2, 0, 0}; // 상하좌우 좌상우상우하좌하 상2하2좌2우2
//	static int[] dc = {0, 0, -1, 1, -1, 1, 1, -1, 0, 0, -2, 2};
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	static int min;
	static int tc = 0;
	static int one_x, one_y, two_x, two_y, three_x, three_y;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		checked = new int[N][N];
		numbers = new int[3];
		min = Integer.MAX_VALUE;
		for(int i = 0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 1);
		System.out.println(min);
		
	}
	public static void comb(int idx, int start) {
		if(idx == 3) {
			min = Math.min(min, make(numbers));
			return;
		}
		checked = new int[N][N];
		for(int i = start ; i<N-1 ; i++) {
			for(int j = 1 ; j<N-1 ; j++) {
				if(UDLRC(i, j)) {
					for(int d = 0 ; d<4 ; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						checked[nr][nc] = 1;
					}
					comb(idx+1, i+1);
//					Arrays.fill(checked, 0);
				}
			}
		}
	}
	public static int make(int[] arr) {
		int r, c;
		int sum = 0;
		int cnt = 0;
//		int nr_one, nc_one, nr_two, nc_two, nr_three, nc_three;
//		checked = new int[N][N];
//		one_x = arr[0]/N;
//		one_y = arr[0]%N;
//		two_x = arr[1]/N;
//		two_y = arr[1]%N;
//		three_x = arr[2]/N;
//		three_y = arr[2]%N;
//		
//		for(int i = 0 ; i<12 ; i++) {
//			nr_one = one_x+dr[i];
//			nc_one = one_y+dc[i];
//			nr_two = two_x+dr[i];
//			nc_two = two_y+dc[i];
//			nr_three = three_x+dr[i];
//			nc_three = three_y+dc[i];
//			if(nr_one >-1 && nr_one <N && nr_two >-1 && nr_two <N && nr_three >-1 && nr_three <N &&
//					nc_one >-1 && nc_one <N && nc_two >-1 && nc_two <N && nc_three >-1 && nc_three <N) {
//				if(nr_one != two_x && nr_one != three_x &&
//						nr_two != one_x && nr_one != three_x &&
//						nr_three != two_x && nr_one != one_x &&
//						nc_one != two_y && nc_one != three_y &&
//						nc_two != one_y && nc_one != three_y &&
//						nc_three != two_y && nc_one != one_y) {
//					System.out.printf("%d\n%d, %d\n%d, %d\n%d, %d", cnt++, nr_one, nc_one, nr_two, nc_two, nr_three, nc_three);
//					sum += map[nr_one][nc_one] + map[nr_two][nc_two]+map[nr_three][nc_three];
//				}
//			}
//		}
		for(int i = 0 ; i<N ; i++) {
			for(int j = 0 ; j<N ; j++) {
				if(UDLRC(i, j)) sum += map[i][j];
			}
		}
		return sum;
	}
	public static boolean UDLRC(int r, int c) {
		int nr, nc;
		cost = map[r][c];
		for(int d = 0 ; d<4 ; d++) {
			nr = r+dr[d];
			nc = c+dc[d];
			if(nr>-1 && nr<N && nc>-1 && nc<N && checked[nr][nc] == 1) {
				return false;
			}
		}
		return true;
	}

}
/*
6
1 0 2 3 3 4
1 1 1 1 1 1
0 0 1 1 1 1
3 9 9 0 1 99
9 11 3 1 0 3
12 3 0 0 0 1
*/
