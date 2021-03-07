package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

public class BOJ_S2_16113_시그널 {
	static char[][][] folds = {{{'#','#','#'},{'#','.','#'},{'#','.','#'},{'#','.','#'},{'#','#','#'}},
							{{'#','.'},{'#','.'},{'#','.'},{'#','.'},{'#','.'}},
							{{'#','#','#'},{'.','.','#'},{'#','#','#'},{'#','.','.'},{'#','#','#'}},
							{{'#','#','#'},{'.','.','#'},{'#','#','#'},{'.','.','#'},{'#','#','#'}},
							{{'#','.','#'},{'#','.','#'},{'#','#','#'},{'.','.','#'},{'.','.','#'}},
							{{'#','#','#'},{'#','.','.'},{'#','#','#'},{'.','.','#'},{'#','#','#'}},
							{{'#','#','#'},{'#','.','.'},{'#','#','#'},{'#','.','#'},{'#','#','#'}},
							{{'#','#','#'},{'.','.','#'},{'.','.','#'},{'.','.','#'},{'.','.','#'}},
							{{'#','#','#'},{'#','.','#'},{'#','#','#'},{'#','.','#'},{'#','#','#'}},
							{{'#','#','#'},{'#','.','#'},{'#','#','#'},{'.','.','#'},{'#','#','#'}}};
	
	static char[][] map;
	static int N;
	static int length;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		in = new BufferedReader(new StringReader(input));
		
		N = Integer.parseInt(in.readLine());
		length = N/5;
		map = new char[5][length];
		
		String cmd = in.readLine().trim();
		for(int i=0; i<5; i++) {
			for(int j=0; j<length; j++) {
				map[i][j] = cmd.charAt(i*length + j);
			}
		}

		StringBuilder sb = new StringBuilder();
		int c=0;
		
		outer: while(c < length) {
			for(int num=0; num<10; num++) {
				if(num != 1) {
				if(c + 2 < length) {
						if(check(c, num)) {
							c += 4;
							sb.append(num);
							continue outer;
						}
					}
				}else {
					if(check(c, num)) {
						c += 2;
						sb.append(num);
						continue outer;
					}
				}
			}
			c += 1;
		}
		System.out.println(sb.toString());
	}
	
	
	static boolean check(int c, int num) {
		if(num == 1) {
			int limit = c + 1 == length ? 1 : 2;
			for(int i = 0; i < 5; i++) {
				for(int j=0; j < limit; j++) {
					if(map[i][c + j] != folds[num][i][j]) {
						return false;
					}
				}
			}
		}
		else {
			for(int i = 0; i < 5; i++) {
				for(int j=0; j < 3; j++) {
					if(map[i][c + j] != folds[num][i][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	static String input="40\n" + 
			"###..#..#.#..#..###..#..#.#..#..###..#..";
//	static String input="90\n" + 
//			"###.....###.#..####.#.......#.#....####.....###.#....##.#.......#.#....####.....###.#....#";
}	
