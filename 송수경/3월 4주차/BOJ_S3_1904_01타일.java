package boj.Silver;

import java.util.Scanner;

public class BOJ_S3_1904_01타일 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(solve(N));
		sc.close();
	}
	
	static int solve(int n) {
		int[] result = new int[1000001];
		result[1]=1;
		result[2]=2;
		for(int i=3, end=n+1; i<end; i++) {
			result[i] = (result[i-2]+result[i-1])%15746;
		}
		return result[n];
	}
}
