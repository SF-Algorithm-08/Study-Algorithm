import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12782 {

	static int answer,T;
	static String N,M;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			answer=0;
			st = new StringTokenizer(br.readLine());
			N=new String(st.nextToken());
			M=new String(st.nextToken());
			solution();
			System.out.println(answer);
		}
	}
	
	private static void solution() {
		int countdiff=0; //숫자 차이 개수
		int locdiff=0;	//위치 차이 개수
		int total = N.length();
		for(int i=0;i<total;i++) {
			if(N.charAt(i)=='0') countdiff++;
			if(M.charAt(i)=='0') countdiff--;
			if(N.charAt(i)!=M.charAt(i)) locdiff++;
		}
		countdiff = Math.abs(countdiff);
		answer+=countdiff; //일단 숫자 개수부터 맞추고
		locdiff-=countdiff; //위치 차이에서 그만큼을 빼고
		answer+=locdiff/2;	//나머지는 위치를 바꾼다.	
	}

}
