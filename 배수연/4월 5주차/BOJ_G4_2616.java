package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 소형 기관차 - DP
 */

public class BOJ_G4_2616 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int bigTrain = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[bigTrain+1];
		// 입력받을 때 앞에꺼를 쭉 더하면서 입력 => 구하고싶은 구간은 빼기로 구하면 됨
		for(int i = 1 ; i<=bigTrain ; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		int littleTrain = Integer.parseInt(br.readLine());
		
		int[][] DP = new int[4][bigTrain+1];
		for(int i = 1 ; i<=3 ; i++) {
			// i번째 소형기관차는 무조건 i*(소형기관차 길이)부터만 탐색 가능
			for(int j = i*littleTrain ; j<=bigTrain ; j++) { 
				// j기준 이전 M칸의 인원 : arr[j]-arr[j-littleTrain]
				//					현재 열차 미포함		j-소형 ~ j번째 기차 + 이전 소형기관차가 끄는 최대
				DP[i][j] = Math.max(DP[i][j-1], (arr[j]-arr[j-littleTrain])+DP[i-1][j-littleTrain]);
			}
		}
		System.out.println(DP[3][bigTrain]);
	}

}
