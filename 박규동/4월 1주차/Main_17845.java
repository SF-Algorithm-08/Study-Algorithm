import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17845 {

	static int N,K,answer;
	static StringTokenizer  st;
	static int[][] dp;
	static int[] lectures;
	static int[] times;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		dp = new int[K+1][N+1];
		lectures = new int[K+1];
		times = new int[K+1];
		
		for(int i=1;i<=K;i++) {
			st=new StringTokenizer(br.readLine());
			lectures[i]= Integer.parseInt(st.nextToken());
			times[i] = Integer.parseInt(st.nextToken());
		}
		
		int cw=0,value=0;
		int preVal=0;						
		int diffVal=0;	
		
		for(int item=1;item<=K;item++) {
			cw=times[item];
			value=lectures[item];
			for(int w=1;w<=N;w++) {
				preVal = dp[item-1][w];
				if(cw<=w) {
					diffVal = dp[item-1][w-cw];
					dp[item][w]=Math.max(preVal, diffVal+value);
				}
				else {
					dp[item][w]=preVal;
				}
			}
		}
		answer = dp[K][N];
		System.out.println(answer);
	}

}
