package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S2_5525_IOIOI {
	static String s;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		int M = Integer.parseInt(in.readLine());
		s = in.readLine();
		
		System.out.println(kmp());
	}
	
	static int kmp() {
		int slen = s.length();
		int cnt = 0, pn = 0;	//pn: p가 연속해서 몇개있는지, cnt: 답 
		
		for(int i=0; i<slen-2; i++) {
			String sub = s.substring(i, i+3);
			if(sub.equals("IOI")) {
				pn++;
				if(pn == N) {
					cnt++;
					pn--;
				}
				i++;
			}
			else  	pn = 0;
		}
		return cnt;
	}
}