package boj;

import java.util.Scanner;

/**
 * 문자열 집합
 * for 반복 + 문자열 equals 비교
 */
public class BOJ_S3_14425 {
	static int N, M;
	static String[] S;
	static String[] sub;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		S = new String[N];
		sub = new String[M];
		for(int n = 0 ; n<N ; n++) {
			S[n] = sc.next();
		}
		for(int m = 0 ; m<M ; m++) {
			sub[m] = sc.next();
		}
		
		int cnt = 0;
		for(int n = 0 ; n<N ; n++) {
			for(int m = 0 ; m<M ; m++) {
				if(S[n].equals(sub[m])) cnt++;
			}
		}
		System.out.println(cnt);
	}

}
