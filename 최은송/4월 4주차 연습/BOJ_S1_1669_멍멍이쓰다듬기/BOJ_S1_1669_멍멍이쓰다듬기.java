package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1669_멍멍이쓰다듬기 {
	public static void main(String[] args) throws IOException{	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		long monkey = Integer.parseInt(st.nextToken());
		long dog = Integer.parseInt(st.nextToken());
		
		long diff = dog - monkey;
		if(diff == 0) {
			System.out.println(0);
		}else if(diff == 1 || diff == 2) {
			System.out.println(1);
		}else {
			long day = 3;
			long start = 3;
			long len = 1;
			long end = start + len;
			while(diff > end) {
				day++;
				if(day % 2 == 1)	len++;
				start = end + 1;
				end = start + len;
			}
			System.out.println(day);
		}
	}

}
