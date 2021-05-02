package boj.Gold;
//G4 소형기관차 (다이나믹 프로그래밍,누적 합)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_2616_소형기관차 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int sum[] = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int length = Integer.parseInt(br.readLine().trim());	//소형 기관차가 최대로 끌 수 있는 객차의 수
		int dp[][] = new int[4][N+1];
		
		for(int i=1; i<=N; i++)	//입력받은 순서대로 누적합 저장 (인덱스: 1부터 사용)
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		
		
		for(int i=1; i<4; i++) {
			for(int j=i*length; j<=N; j++) {
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-length] + (sum[j] - sum[j-length]));
			}
		}
		
		System.out.println(dp[3][N]);
	}
}
