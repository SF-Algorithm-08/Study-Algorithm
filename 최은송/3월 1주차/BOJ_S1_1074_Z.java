package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1074_Z {
	static int N;
	static int R;
	static int C;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int cnt = 0, r = 0, c = 0, n = 1 << N;
		
		while(n > 0){
			n = n >> 1;
			//2사분면 
			if(R < r+n && C < c+n) {
				cnt += n * n * 0;
			}
			// 1사분면 
			else if(R < r + n) {
				cnt += n * n;
				c += n;
			}
			// 3사분면
			else if( C < c+n) {
				cnt += n * n * 2;
				r += n;
			}
			// 4사분면
			else {
				cnt += n * n * 3;
				r += n;
				c += n;
			}	
			
			if(n == 1) {
				System.out.println(cnt);
				break;
			}
			
		}	
	}
}
