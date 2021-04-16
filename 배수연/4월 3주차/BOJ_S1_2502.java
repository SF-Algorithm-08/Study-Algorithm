package boj;

import java.util.Scanner;

/**
 * 떡 먹는 호랑이
 */
// Day 1 : 1A + 0B
// Day 2 : 0A + 1B
// Day 3 : 1A + 1B
// Day 4 : 1A + 2B
// Day 5 : 2A + 3B
// Day 6 : 3A + 5B

public class BOJ_S1_2502 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();
		int K = sc.nextInt();
		if(D == 3) System.out.println("1\n"+(K-1));
		
		int[] A = new int[D+1];
		int[] B = new int[D+1];
		
		A[1] = 1;
		A[2] = 0;
		B[1] = 0;
		B[2] = 1;
		for(int d = 3 ; d<=D ; d++) {
			A[d] = A[d-1]+A[d-2];
			B[d] = B[d-1]+B[d-2];
		}
		for(int i = 1, end = K/A[D]+1 ; i<end ; i++) {
			int temp = K-i*A[D];
			if(temp%B[D] == 0) {
				System.out.println(i + "\n" + temp/B[D]);
				break;
			}
		}
		
	}

}
