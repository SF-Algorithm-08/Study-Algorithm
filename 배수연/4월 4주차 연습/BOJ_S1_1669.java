package boj;

import java.util.Scanner;

/**
 * 키 차이		날짜 별 키 늘이기
 * -----------------------
 * 1이면 		1
 * 2이면 		1+1
 * 3이면		1+1+1
 * 4이면 		1+2+1			// 중심 기준 대칭
 * 5이면		1+2+1+1
 * 6이면 		1+2+2+1			// 개수 달라지는 시점
 * 7이면 		1+2+2+1+1	
 * 8이면 		1+2+2+2+1
 * 9이면	 	1+2+3+2+1		// 중심 기준 대칭
 * 10이면 	1+2+3+2+1+1
 * 11이면 	1+2+3+2+2+1
 * 12이면		1+2+3+3+2+1		// 개수 달라지는 시점
 * 13이면		1+2+3+3+2+1+1
 * 14이면		1+2+3+3+2+2+1
 * 15이면		1+2+3+3+3+2+1
 * 16이면		1+2+3+4+3+2+1	//중심 기준 대칭
 * 
 *	 => 중심 기준 대칭일 때는, 키 차이가 N이면 루트(N)을 기준으로 대칭됨 (9이면 3 기준 대칭)		정답 : 2*루트(N)-1
 * 		개수가 달라지는 시점 : 루트(N)+N -> 이거보다 작으면 2*루트(N) (12면 3+9)			정답 : 2*루트(N)
 * 		그 외 경우 : 2*루트(N)+1
 * 
 */

public class BOJ_S1_1669 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long X = sc.nextLong();
		long Y = sc.nextLong();
		
		if(X == Y) System.out.println("0");
		else {
			long N = Y-X;
			long R = (long) Math.sqrt(N);
			if(R*R == N) System.out.println(R*2-1);
			else if(N<=R+R*R) System.out.println(R*2);
			else System.out.println(R*2+1);
		}
	}
}
