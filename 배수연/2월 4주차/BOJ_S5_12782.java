package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 비트 우정 지수 */
/**
 * (비트 차이 + 전체 자리 수의 합 차이)/2
 */
public class BOJ_S5_12782 {
	static int T;
	static String N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test<=T ; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = st.nextToken();
			M = st.nextToken();
			System.out.println(check());
		}
	}
	private static int check() {
		int bitdiff = 0;
		int sum_N = 0;
		int sum_M = 0;
		for(int i = 0, len = N.length() ; i<len ; i++) {
			if(N.charAt(i) != M.charAt(i)) bitdiff++;
			sum_N += N.charAt(i);
			sum_M += M.charAt(i);
		}
		return (bitdiff+Math.abs(sum_N-sum_M))/2;
	}

}
