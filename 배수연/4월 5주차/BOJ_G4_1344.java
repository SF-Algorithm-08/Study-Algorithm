package boj;

import java.util.Scanner;

/**
 * 축구 - DP, 조합
 * r만큼 확률 = 18Cr * 승리^r * (1-승리)^(18-r)
 */

public class BOJ_G4_1344 {
	static int[] score = {2, 3, 5, 7, 11, 13, 17};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double A = sc.nextDouble()/100.0;
		double B = sc.nextDouble()/100.0;
		
		// 파스칼의 삼각형 공식으로 18Cr 구하기
		// nCr = n-1Cr-1 + n-1Cr
		double[][] DP = new double[19][19];
		for(int i = 0 ; i<19 ; i++) {
			for(int j = 0 ; j<=i ; j++) {
				if(i == j || j == 0) DP[i][j] = 1;
				else DP[i][j] = DP[i-1][j-1] + DP[i-1][j];
			}
		}
		
		double sumA = 0.0, sumB = 0.0;
		for(int i = 0 ; i<7 ; i++) {
			sumA += DP[18][score[i]] * Math.pow(A, score[i]) * Math.pow(1-A, 18-score[i]);
			sumB += DP[18][score[i]] * Math.pow(B, score[i]) * Math.pow(1-B, 18-score[i]);
		}
		System.out.println(sumA+sumB-sumA*sumB);
	}

}
