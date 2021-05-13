package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class BOJ_G5_스타트링크타워 {
	static String[] digit = { 	"####.##.##.####",
								"..#..#..#..#..#",
								"###..#####..###",
								"###..####..####",
								"#.##.####..#..#",
								"####..###..####",
								"####..####.####",
								"###..#..#..#..#",
								"####.#####.####",
								"####.####..####" };

	public static void main(String[] args) throws IOException {
//		1. 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		
		String[] input = new String[5];
		for(int i=0; i<5; i++) input[i] = br.readLine().trim();
		
//		2. 가능한 숫자 찾기
		boolean[][] makeNum = new boolean[10][N];
		int[] makeNumCnt = new int[10];	//안내판 위치별로 가능한 숫자 개수
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {	//안내판
			sb.setLength(0);
			for(int j=0; j<5; j++) sb.append(input[j].substring(i*4, i*4+3));
			
			String check = sb.toString();	//안내판이 나타내는 digit
			doCheck:
			for(int d=0; d<10; d++) {	//0~9 digit 검사
				for(int j=0; j<15; j++) {
					if(check.charAt(j)=='#' && digit[d].charAt(j)=='.') continue doCheck; 
				}
				makeNum[d][i]=true;
				makeNumCnt[i]++;
			}
		}
		
//		3. 숫자 조합하기
		double sum=0;	//총합
		double total = 1;
		for(int n : makeNumCnt) if(n!=0) total*=n;
		
		for(int i=0; i<N; i++) {
			if(makeNumCnt[i]==0) continue;	//어차피 만들 수 없는 경우는 넘어간다
			long hap = 0;
			for(int j=0; j<10; j++) if(makeNum[j][i]) hap+=j*Math.pow(10, N-i-1);
			
			sum += hap*(total/makeNumCnt[i])/total;
		}
		
//		5. 정답 출력
		NumberFormat f = NumberFormat.getInstance();
		f.setGroupingUsed(false);
		System.out.println(sum==0?"-1":f.format(sum));
	}

}

/*
9
###...#.###.###.#.#.###.###.###.###
#.#...#...#...#.#.#.#...#.....#.#.#
#.#...#.###.###.###.###.###...#.###
#.#...#.#.....#...#...#.#.#...#.#.#
###...#.###.###...#.###.###...#.###

451458714.380952


9
..#.###.###.#.#.###.###.###.###.###
..#...#...#.#.#.#...#.....#.#.#.#.#
..#.###.###.###.###.###...#.###.###
..#.#.....#...#...#.#.#...#.#.#...#
..#.###.###...#.###.###...#.###.###

514587152.309524

9
..#.###.###.#.#.###.###.###.###.###
..#...#...#.#.#.#...#....##.#.#.#.#
..#.###.###.###.###.###...#.###.###
..#.#.....#...#...#.#.#...#.#.#...#
..#.###.###...#.###.###..##.###...#

-1


9
..#.###.###.#.#.###.###.###.###.###
..#...#...#.#.#.#...#.....#.#.#.#.#
..#.###.###.###.###.###...#.###.###
..#.#.....#...#...#.#.#...#.#.#...#
..#.###.###...#.###.###..##.###...#

514587112.30952


1
...
.#.
...
.#.
...

-1


9
..#...#...#...#...#...#...#...#...#
...................................
..#...#...#...#...#...#...#...#...#
...................................
..#...#...#...#...#...#...#...#...#

499999999.5

*/