package boj;

import java.util.Scanner;

/**
 * Z
 */
public class BOJ_S1_1074 {
	static int N, R, C, idx;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		idx = 0;
		z((int) Math.pow(2, N), 0, 0);
	}
	private static void z(int n, int r, int c) {
		if(r == R && c == C) { // 검색하고자 하는 좌표에 도달하는 경우
			System.out.println(idx);
			return;
		}
		if(n == 1) { // 크기가 1이지만 찾고자 하는 좌표가 아닌 경우
			idx++;
			return;
		}
		int i;
		if(!((r<=R && R<r+n) && (c<=C && C<c+n))) { // 찾고자 하는 R, C가 현재 검색 중인 사분면인지 확인
			idx += n*n;
			return;
		}
		
		// 분할정복
		int halfN = n/2;
		z(halfN, r, c);
		z(halfN, r, c+halfN);
		z(halfN, r+halfN, c);
		z(halfN, r+halfN, c+halfN);
	}
}
