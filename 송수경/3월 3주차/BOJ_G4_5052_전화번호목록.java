package boj.Gold;
//G4 전화번호 목록 (자료구조,정렬,문자열,트리,트라이)
//solved

/*
3
3
911
97625999
91125426
5
113
12340
123440
12345
98346
3
911
97 625 999
91 12 54 26
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_G4_5052_전화번호목록 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		int N;			//전화번호 개수
		String[] numbers;	//전화번호 목록
		
		test:
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine().trim());
//			1. 전화번호 입력받기
			numbers = new String[N];	//최대 10자리
			for(int i=0; i<N; i++) {
				numbers[i] = br.readLine().replaceAll(" ", "");
			}
			Arrays.sort(numbers);
//			for(int i=0; i<N; i++) {
//				System.out.println(numbers[i]);
//			}
			
			for(int i=1; i<N; i++) {
				if(String.valueOf(numbers[i]).startsWith(String.valueOf(numbers[i-1]))) {
					sb.append("NO\n");
					continue test;
				}
			}
			sb.append("YES\n");
		}
		System.out.print(sb.toString());
	}
}
