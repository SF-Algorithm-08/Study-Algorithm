import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20364 {

	static boolean[] visit;
	static int answer,N,Q,ori;
	static StringTokenizer st;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		sb=new StringBuilder();
		visit = new boolean[N+1];
		for(int i=0;i<Q;i++) {
			answer=0;
			ori = Integer.parseInt(br.readLine());
			solution(ori);
			sb.append(answer+"\n");
		}
		System.out.println(sb.toString());
	}
	private static void solution(int cur) {
		while(true) {
			if(visit[cur]) answer = cur;
			if(cur==1) {
				visit[ori]=true;
				return;
			}
			cur/=2;
		}
	}
}
