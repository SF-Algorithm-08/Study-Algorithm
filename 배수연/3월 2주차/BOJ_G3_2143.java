package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 두 배열의 합
 * 배열 정렬
 * 각각 두 배열의 가능한 모든 경우의 수의 부분집합 합들을 구해두기
 * A배열은 작은 것부터, B배열은 큰 것부터 더해가면서 T와 같은지 판별
 */
public class BOJ_G3_2143 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int[] numA = new int[A];
		st = new StringTokenizer(br.readLine());
		for(int a = 0 ; a<A ; a++) {
			numA[a] = Integer.parseInt(st.nextToken());
		}
		int B = Integer.parseInt(br.readLine());
		int[] numB = new int[B];
		st = new StringTokenizer(br.readLine());
		for(int b = 0 ; b<B ; b++) {
			numB[b] = Integer.parseInt(st.nextToken());
		}
		
		int[] sumA = new int[A*(A+1)/2];
		int[] sumB = new int[B*(B+1)/2];
		int k1 = 0, k2 = 0;
		for(int i = 0 ; i<A ; i++) {
			int sum = 0;
			for(int j = i ; j<A ; j++) {
				sum += numA[j];
				sumA[k1++] = sum;
			}
		}
		for(int i = 0 ; i<B ; i++) {
			int sum = 0;
			for(int j = i ; j<B ; j++) {
				sum += numB[j];
				sumB[k2++] = sum;
			}
		}
		Arrays.sort(sumA);
		Arrays.sort(sumB);
		
		long ans = 0;
		int idxA = 0;
		int idxB = sumB.length-1;
		while(idxA<sumA.length && idxB>=0) {
			int a = sumA[idxA];
			int b = sumB[idxB];
			if(a+b == T) {
				long multipleA = 0, multipleB = 0; // 같은 값이 몇개인지 판별
				while(idxA<sumA.length && sumA[idxA] == a) {
					idxA++;
					multipleA++;
				}
				while(idxB>=0 && sumB[idxB] == b) {
					idxB--;
					multipleB++;
				}
//				System.out.println(a+", "+b+" / "+multipleA+", "+multipleB);
				ans += multipleA*multipleB;
			}
			else if(a+b < T) idxA++;
			else if(a+b > T) idxB--;
		}
		sb.append(ans);
		System.out.println(sb.toString());
	}
}

// 0 1 4 5 7 3 4 6 1 3 2
// 0 1 4 6 3 5 2

//input
/*
5
4
1 3 1 2
3
1 3 2
*/
