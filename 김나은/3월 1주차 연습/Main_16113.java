// 시그널

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16113 {

	static String zero 	= "######...######";
	static String one 	= "#####";
	static String two 	= "#.####.#.####.#";
	static String three = "#.#.##.#.######";
	static String four 	= "###....#..#####";
	static String five 	= "###.##.#.##.###";
	static String six 	= "######.#.##.###";
	static String seven = "#....#....#####";
	static String eight = "######.#.######";
	static String nine 	= "###.##.#.######";

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[5][N/5];
		char[] signal = br.readLine().toCharArray();
		
		int idx = 0;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < N/5; col++) {
				map[row][col] = signal[idx++];
			}
		}
		
		String str = "";
		for (int col = 0; col < N/5; col++) {
			if(map[0][col]=='.') { // 맨 윗줄이 공백인 경우
				if(str.equals("###..")) { // 4일 때
					System.out.print(4);
					str="";
					col++;
					continue;
				}
				
				else if(str.equals(zero)) System.out.print(0);
				else if(str.equals(one)) System.out.print(1);
				else if(str.equals(two)) System.out.print(2);
				else if(str.equals(three)) System.out.print(3);
//				else if(str.equals(four)) System.out.print(4);
				else if(str.equals(five)) System.out.print(5);
				else if(str.equals(six)) System.out.print(6);
				else if(str.equals(seven)) System.out.print(7);
				else if(str.equals(eight)) System.out.print(8);
				else if(str.equals(nine)) System.out.print(9);
				
				str="";
				continue;
			}
			for (int row = 0; row < 5; row++) {
				str += map[row][col];
			}
		}
		
		// 마지막 줄에 숫자가 있는 경우
		if(str.equals(zero)) System.out.print(0);
		else if(str.equals(one)) System.out.print(1);
		else if(str.equals(two)) System.out.print(2);
		else if(str.equals(three)) System.out.print(3);
		else if(str.equals(four)) System.out.print(4);
		else if(str.equals(five)) System.out.print(5);
		else if(str.equals(six)) System.out.print(6);
		else if(str.equals(seven)) System.out.print(7);
		else if(str.equals(eight)) System.out.print(8);
		else if(str.equals(nine)) System.out.print(9);
		
	}
}
