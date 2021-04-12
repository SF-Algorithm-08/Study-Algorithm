import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_4811 {

	static int N,T;
	static long answer;
	static long[] dp;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
            N=Integer.parseInt(br.readLine());
			if(N==0) return;
            dp = new long[N+1];
			dp();
        }		
	}
    
	private static void dp() {
		if(N<3) {
			System.out.println(N);
			return;
		}
		dp[0]=1;
		dp[1]=1;
		dp[2]=2;
		for(int i=3;i<=N;i++) {
			for(int j=0;j<i;j++) {
				dp[i] += dp[j]*dp[i-1-j];
			}
		}
		System.out.println(dp[N]);
	}

}


