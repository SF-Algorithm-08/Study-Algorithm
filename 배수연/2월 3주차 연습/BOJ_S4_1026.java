package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_S4_1026 {
	static int N;
	static int[] A;
	static int[] B;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n<N ; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n<N ; n++) {
			B[n] = Integer.parseInt(st.nextToken());
		}
		//A는 오름차순, B는 내림차순으로 정렬.. 하려고 했으나 comparator 잘 못써서 그냥 둘 다 오름차로 정렬하고 for문에서 인자 2개로 관리함..
		Arrays.sort(A);
		Arrays.sort(B);
		
		int sum = 0;
		for(int i = 0, j = N-1 ; i<N ; i++, j--) {
			sum += A[i]*B[j];
		}
		System.out.println(sum);
	}

}
