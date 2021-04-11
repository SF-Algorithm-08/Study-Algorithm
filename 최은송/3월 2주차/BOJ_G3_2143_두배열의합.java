package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G3_2143_두배열의합 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new StringReader(input));
		int T = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		
		// A 배열의 부분집합의 합을 전부 구해준다 
		List<Integer> sumA = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = i; j < N; j++) {
				sum += A[j];
				sumA.add(sum);
			}
		}
		// 정렬 
		Collections.sort(sumA);

		
		int M = Integer.parseInt(in.readLine());
		int[] B = new int[M];
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i < M; i++)
			B[i] = Integer.parseInt(st.nextToken());
		
		// B배열의 부분집합의 합을 전부 구해준다 
		List<Integer> sumB = new ArrayList<Integer>();
		for(int i = 0; i < M; i++) {
			int sum = 0;
			for(int j=i; j<M; j++) {
				sum += B[j];
				sumB.add(sum);
			}
		}
		// 정렬 
		Collections.sort(sumB);

		
		long cnt = 0;
		for(int i=0; i<sumA.size(); i++) {
			int low = lowerBound(sumB, T - sumA.get(i));
			int high = upperBound(sumB, T - sumA.get(i));
			cnt += high - low;
		}
		
		System.out.println(cnt);
	}
	
	static int upperBound(List<Integer> arr, int value) {
		int low = 0;
		int high = arr.size();
		while(low < high) {
			int mid = (low + high) / 2;
			if(value >= arr.get(mid))
				low = mid + 1;
			else
				high = mid;
		}
		return high;
	}
	
	static int lowerBound(List<Integer> arr, int value) {
		int low = 0;
		int high = arr.size();
		while(low < high) {
			int mid = (low + high) / 2;
			if(value <= arr.get(mid))
				high = mid;
			else
				low = mid + 1;
		}
		return high;
	}
	
	static String input = "5\n" + 
			"4\n" + 
			"1 3 1 2\n" + 
			"3\n" + 
			"1 3 2";
}
