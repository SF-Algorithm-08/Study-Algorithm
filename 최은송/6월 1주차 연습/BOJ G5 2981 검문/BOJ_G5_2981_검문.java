package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 A = X1M + r
 B = X2M + r
 C = X3M + r
 
(X2 - X3)M = (A - B)
(X3 - X2)M = (B - C)
=> (A-B), (B-C)의 최대공약수 
 */

public class BOJ_G5_2981_검문 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int arr[] = new int[N];
		for(int i = 0; i <N; i++)
			arr[i] = Integer.parseInt(in.readLine());
		Arrays.sort(arr);
//		
//		int end = arr[1];
//		for(int m = 2; m < end; m++) {
//			int r = arr[0] % m;
//			boolean flag = true;
//			for(int i = 1; i < N; i++) {
//				if(arr[i] % m != r) {
//					flag = false;
//					break;
//				}
//			}
//			if(flag) {
//				System.out.printf("%d ",m);
//			}
//		}
		
		int val = arr[1] - arr[0];
		for(int i = 2; i < N; i++) {
			val = gcd(val, arr[i] - arr[i-1]);			
		}
		for(int i = 2; i <= val; i++) {
			if(val % i == 0) {
				System.out.printf("%d ",i);
			}
		}
	}
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
