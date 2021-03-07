import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_14425 {

	static Set<String> set;
	static int N,M,answer;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		set=new HashSet<>();
		for(int i=0;i<N;i++) {
			set.add(br.readLine());
		}
		for(int i=0;i<M;i++) {
			String str = br.readLine();
			if(set.contains(str)) answer++;
		}
		System.out.println(answer);
	}
}
