package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_G4_2830_행성X3 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int arr[] = new int[N];
		for(int i = 0; i < N; i++) 
			arr[i] = Integer.parseInt(in.readLine());
		Arrays.sort(arr);
		
		int len = (int) (Math.log(arr[N-1]) / Math.log(2));
		int zero = 0;
		long ans = 0;
		
		for(int i = 0; i <= len; i++) {
			zero = 0;
			for(int j = 0; j < N; j++) {
				if((arr[j] & (1 << i)) == 0)	zero++;
			}
			ans += (long)zero * (long)(N-zero) * (long)(1 << i);
		}
		System.out.println(ans);
	}
}
