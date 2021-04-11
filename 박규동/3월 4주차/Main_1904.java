import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1904 {

	static int[] dp;
	static int N,answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		dp = new int[N+1];
		answer = dp();
		System.out.println(answer);
	}

	private static int dp() {
		if(N<3) return N;
		
		dp[1]=1;
		dp[2]=2;
		for(int i=3;i<=N;i++) {
			dp[i]=(dp[i-1]+dp[i-2])%15746;
		}
		
		return dp[N];
	}

}
