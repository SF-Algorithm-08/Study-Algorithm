package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_G4_10993_별찍기 {
	static char star[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int height = (int)Math.pow(2, N) - 1;
		int width = 2 * height - 1;
		star = new char[height][width];

		int W = width / 2;	// 전체 열의 중간 
		int starty = N % 2 == 1 ? 0 : height-1;	// 홀수 -> 위에서부터 찍기, 짝수 -> 밑에서부터 찍기 
		solve(N, starty, W);
		
		StringBuilder sb = new StringBuilder();
		if(N % 2 == 1) {
			for(int i = 0; i < height; i++, W++) {
				for(int j = 0; j <= W; j++) {
					sb.append(star[i][j] == '*' ? '*' : ' ');
				}
				sb.append("\n");
			}
		}else {
			for(int i = 0; i < height; i++, width--) {
				for(int j = 0; j < width; j++) {
					sb.append(star[i][j] == '*' ? '*' : ' ');
				}
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	static void solve(int n, int starty, int startx) {
		if(n == 0) {
			return;
		}
		int height  = (1 << n) - 1;
		int nexth = height >> 1, nexty =0;
		//n이 홀수일 때 
		if(n % 2 == 1) {
			int y = starty;
			int end = starty + height - 1;
			int diff = Math.abs(y - end);
			for(int i = 0; y < end; y++, i++) {
				star[y][startx - i] = '*';
				star[y][startx + i] = '*';
			}
			
			// 밑에 한줄 찍기 
			Arrays.fill(star[y], startx - diff, startx + diff+1, '*');
			nexty = (starty + end) / 2 + nexth - 1;
		}
		// n이 짝수일 때 
		else {
			int y = starty;
			int end = starty - height + 1;
			int diff = Math.abs(y - end);
			for(int i = 0; y > end; y--, i++) {
				star[y][startx - i] = '*';
				star[y][startx + i] = '*';
			}
			// 위에 한줄 찍기 
			Arrays.fill(star[y], startx - diff, startx + diff+1, '*');
			nexty = (starty + end) / 2 - nexth + 1;
		}
		solve(n-1, nexty, startx);
	}
}
