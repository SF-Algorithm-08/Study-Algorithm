import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import javax.smartcardio.CommandAPDU;

public class Main_2616 {

	static int N,M,answer;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		dp = new int[3][N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i!=0) arr[i]+=arr[i-1]; //arr[i]-arr[i-m] -> 현재 인덱스를 마지막으로 포함하는 m개만큼 합
		}
		
		answer = dp();
		System.out.println(answer);
	}

	private static int dp() {
		
		dp[0][M-1] = arr[M-1];
		for(int i=0;i<N;i++) {
			if(i>M-1) {
				dp[0][i] = Math.max(dp[0][i-1], arr[i]-arr[i-M]);
			}
		}
		
		for(int i=0;i<N;i++) {
			if(i>=M*2-1) dp[1][i] = Math.max(dp[1][i-1], dp[0][i-M]+arr[i]-arr[i-M]);
		}
		
		for(int i=0;i<N;i++) {
			if(i>=M*3-1) dp[2][i] = Math.max(dp[2][i-1], dp[1][i-M]+arr[i]-arr[i-M]);
		}
		
		
		return dp[2][N-1];
	}

}
