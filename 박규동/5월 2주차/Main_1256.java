import java.util.Arrays;
import java.util.Scanner;

public class Main_1256 {

	static int N, M;
	static long K;
	static long[][] dp;
	static char[] answer;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb=new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextLong();
		sc.close();
		dp = new long[N + 1][M + 1];
		dp();
		
		if(dp[N][M]<K) {
			System.out.println(-1);
			return;
		}
		
		makeString(N,M);
		System.out.println(sb.toString());
	}

	private static void makeString(int n,int m) {
		if(n==0) {
			for(int i=m;i>0;i--) sb.append("z");
			return;
		}
		
		if(m==0) {
			for(int i=n;i>0;i--) sb.append("a");
			return;
		}
		
		if(K>dp[n-1][m]) {
			K=K-dp[n-1][m];
			sb.append("z");
			makeString(n, m-1);
		}else {
			sb.append("a");
			makeString(n-1, m);
		}
		
		
	}

	private static void dp() {
		Arrays.fill(dp[0], 1);
		for(int i=0;i<N+1;i++) dp[i][0]=1;
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				if(dp[i][j]>1000000001) dp[i][j]=1000000001;
			}
		}
		
//		for(int i=0;i<N+1;i++) {
//			System.out.println();
//			for(int j=0;j<M+1;j++) System.out.print(" "+dp[i][j]);
//		}
//		System.out.println();
	}

}
