package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class BOJ_S4_1026_보물 {
//	static int N;
	static int min = Integer.MAX_VALUE;
//	static int[] a;
//	static int[] b;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		Integer[] a = new Integer[N];
		Integer[] b = new Integer[N];
		StringTokenizer st1 = new StringTokenizer(in.readLine().trim(), " ");
		StringTokenizer st2 = new StringTokenizer(in.readLine().trim(), " ");
		for(int i=0; i<N; i++) {
			a[i] = Integer.parseInt(st1.nextToken());
			b[i] = Integer.parseInt(st2.nextToken());
		}
		Arrays.sort(a);
		Arrays.sort(b, Collections.reverseOrder());
//		System.out.println(Arrays.toString(b));
		//permu(0,0, N, a,b, 0);
		min = 0;
		for(int i=0; i<N; i++) {
			min += a[i] * b[i];
		}
		System.out.println(min);
	}
	
	private static void permu(int cnt, int flag, int n, Integer[] a, Integer[] b, int sum) {
		if(sum >= min)
			return;
		if(cnt == n) {
			min = Math.min(min, sum);
			return;
		}
		for(int i=0; i<n; i++) {
			if((flag & 1<<i) !=0)	continue;
			int temp = b[cnt] * a[i];
			permu(cnt+1, flag | 1<<i, n, a, b, sum+temp);
		}
	}

}