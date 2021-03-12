import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074 {

	static int N,r,c,index,answer;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		recur((int)Math.pow(2, N),0,0);
		System.out.println(answer);
	}
	
	private static void recur(int n,int row,int col) {

		if(row==r&&col==c) {
			answer = index;
			return;
		}
		
		if(r>=row&&r<row+n&&c>=col&&c<col+n) {
			recur(n/2,row,col); //1사분면
			recur(n/2,row,col+n/2);	//2사분면
			recur(n/2,row+n/2,col);	//3사분면
			recur(n/2,row+n/2,col+n/2);	//4사분면
		}
		else {
			index+=n*n;
		}

	}

}
