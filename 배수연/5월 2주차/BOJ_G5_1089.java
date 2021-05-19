package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_G5_1089 {
	static int N, maxW, maxH;
	static char[][] map;
	static final String[] light = {
			"###...#.###.###.#.#.###.###.###.###.###",
			"#.#...#...#...#.#.#.#...#.....#.#.#.#.#",
			"#.#...#.###.###.###.###.###...#.###.###",
			"#.#...#.#.....#...#...#.#.#...#.#.#...#",
			"###...#.###.###...#.###.###...#.###.###"
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxW = 5; // 입력받을 행 개수
		maxH = (3*N) + (N-1); // 입력받을 열 개수
		map = new char[maxW][maxH];
		for(int w = 0 ; w<maxW ; w++) {
			map[w] = br.readLine().toCharArray();
		}
		
		ArrayList<Integer>[] list = new ArrayList[N];
		for(int n = 0 ; n<N ; n++) {
			list[n] = new ArrayList<Integer>();
		}
		
		// 공백 포함 4칸씩 끊어가면서 그 칸에서 가능한 숫자 경우들 구해서 각 자리수 별로 리스트에 넣기
		for(int h = 0 ; h<maxH ; h+=4) {
			getNum(list[h/4], h);
		}
		
		// 번호 생성을 못하는 경우 : 리스트가 하나라도 비어있는 경우에는 계산 불가능!
		for(int n = 0 ; n<N ; n++) {
			if(list[n].size() == 0) {
				System.out.println("-1");
				return;
			}
		}
		
		// 자리수 별로 가능한 경우들이 리스트에 저장돼있으니까 그 리스트의 값 다 더하고 자리수를 10의 제곱수로 해서 자리 별로 평균 구하기
		double ans = 0.0;
		for(int n = 0 ; n<N ; n++) {
			double temp = 0.0;
			// 리스트 별로 돌면서 해당 자리에 가능한 숫자들의 합 구하기
			for(int j = 0 ; j<list[n].size() ; j++) {
				int num = list[n].get(j);
				temp += num;
			}
			temp *= Math.pow(10, N-1-n); // 자리수에 맞춰주기 : 전체 N-1에서 현재 인덱스 n 빼면 그게 제곱해야 하는 횟수
			ans += temp/list[n].size();
		}
		System.out.println(ans);
	}

	private static void getNum(ArrayList<Integer> list, int idx) {
		char[][] num = new char[5][3];
		for(int w = 0 ; w<maxW ; w++) {
			for(int j = idx ; j<idx+3 ; j++) {
				num[w][j-idx] = (map[w][j] == '#')? '#' : '.';
			}
		}
		
		for(int i = 0 ; i<light[0].length() ; i+=4) {
			char[][] tempNum = new char[5][3];
			for(int j = i ; j<i+3 ; j++) {
				for(int k = 0 ; k<5 ; k++) {
					tempNum[k][j-i] = (light[k].charAt(j) == '#')? '#' : '.';
				}
			}
			
			// 지정된 숫자에서 #인 곳이 입력에서 .이면 불가능한 숫자 => 가능한 숫자들을 리스트에 넣어주기
			boolean flag = true;
			for(int r = 0 ; r<5 ; r++) {
				for(int c = 0 ; c<3 ; c++) {
					if(num[r][c] == '#' && tempNum[r][c] == '.') flag = false;
				}
			}
			if(flag) list.add(i/4);
		}
	}
}


