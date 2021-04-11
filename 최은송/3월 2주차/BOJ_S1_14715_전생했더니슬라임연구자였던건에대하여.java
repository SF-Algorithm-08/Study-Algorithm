package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_14715_전생했더니슬라임연구자였던건에대하여 {
	static int min;
	static int depth;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		
		// 소인수분해 해준다
		int div = 2, cnt = 0;
		while(num != 1) {
			if(num % div == 0) {
				num /= div;
				cnt++;
			}else {
				div++;
			}
		}
		int result =  (int)Math.ceil(Math.log10(cnt) / Math.log10(2));
		System.out.printf("%d\n", cnt == 1 ? 0 : result);
	}
}
