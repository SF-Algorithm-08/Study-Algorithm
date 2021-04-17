import java.util.*;
import java.io.*;

public class Main_2502 {

	static int[] A,B;
	static StringTokenizer st;
	static int d,k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		A=new int[31];
		B=new int[31];
		
		A[3]=A[4]=1;
		B[3]=1;
		B[4]=2;
		
		for(int i=5;i<31;i++) {
			A[i]=A[i-1]+A[i-2];
			B[i]=B[i-1]+B[i-2];
		}
		
		dp();

	}

	private static void dp() {
		int a = A[d];
		int b = B[d];
		for(int i=1;i<5001;i++) {
			int tmp = k-a*i;
			if(tmp%b==0) {
				System.out.println(i);
				System.out.println(tmp/b);
				return;
			}
		}
		
	}

}
