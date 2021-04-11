package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 전화번호 목록
 * 사전식 정렬 -> 일관성이 없는건 바로 옆에 위치할 수밖에 없음
 */
public class BOJ_G4_5052 {
	static int T, N;
	static String[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test<=T ; test++) {
			N = Integer.parseInt(br.readLine());
			numbers = new String[N];
			String ans = "YES";
			for(int n = 0 ; n<N ; n++) {
				numbers[n] = br.readLine();
			}
			Arrays.sort(numbers);
			for(int n = 0 ; n<N-1 ; n++) {
				int temp_len = numbers[n].length();
				int next_len = numbers[n+1].length();
				if(temp_len<next_len) {
					if(numbers[n+1].substring(0, temp_len).equals(numbers[n])) {
						ans = "NO";
						break;
					}
				}
//				int idx = 0;
//				int min = Math.min(temp_len, next_len);
//				while(idx<min) {
//					if(numbers[n].charAt(idx) == numbers[n+1].charAt(idx)) {
//						ans = "NO";
//						break;
//					}
//					idx++;
//				}
//				if(numbers[n].contains(numbers[n+1]) || numbers[n+1].contains(numbers[n])) {
//					ans = "NO";
//					break;
//				}
				
			}
			System.out.println(ans);
		}
	}
}