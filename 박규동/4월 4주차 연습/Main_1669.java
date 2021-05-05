import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1669 {

	static int X,Y,answer;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		X=Integer.parseInt(st.nextToken());
		Y=Integer.parseInt(st.nextToken());
		
		int diff = Y-X;
		int rootVal = (int)Math.sqrt(diff);
		if(diff==0)
			answer=0;
		else if(rootVal*rootVal == diff) {
			answer = 2*rootVal-1;
		}
		else {
			int left = rootVal*rootVal;
			int end = (rootVal+1)*(rootVal+1);
			double middiff = (double)(end-left) / (double) 2;
			if(middiff>Math.sqrt((double)diff)) answer = 2*rootVal;
			else answer = 2*rootVal+1;
		}

		System.out.println(answer);
	}	
}
