package boj.Gold;

import java.util.Scanner;

public class BOJ_G4_1344_축구 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double A = sc.nextDouble()/100;
		double B = sc.nextDouble()/100;
		
		int[] arr = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
		double[] winA = new double[12];
		double[] winB = new double[12];
		
		for(int i=0; i<12; i++) {
			winA[i] = comb(arr[i]) * Math.pow(A, arr[i]) * Math.pow(1-A, 18-arr[i]);
			winB[i] = comb(arr[i]) * Math.pow(B, arr[i]) * Math.pow(1-B, 18-arr[i]);
		}
		
		double ans = 0.0;
		for(int i=0; i<12; i++) {
			for(int j=0; j<12; j++) {
				ans += winA[i]*winB[j];
			}
		}
		
		System.out.println(1-ans);
		sc.close();
	}

	static int comb(int r) {
		if( r == 0 ) return 1;
		if( 18 == r ) return 1;
		if( 9<r ) r=18-r;
		
		int nCr = 1;
		for(int i=1; i<=r; i++)
			nCr = nCr*(18-i+1)/i;
		
		return nCr;
	}
	
}
