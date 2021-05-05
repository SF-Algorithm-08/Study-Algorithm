import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1052 {

	static int N,K,answer;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());

		while(true) {
			int cnt=1;
			int result=0;
			int flag=N;
			while(true) {
				if(cnt>flag) break;
				if((flag & cnt)!=0) result++;
				cnt = cnt << 1;
			}
			if(result<=K) {
				break;
			}
			N++;
			answer++;
		}
		
		System.out.println(answer);
	}

}
