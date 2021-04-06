package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_S2_5525 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int ans = 0;
		
		int idx = 0, cnt = 0;
		while(idx<M-2) {
			String temp = str.substring(idx, idx+3);
			if(temp.equals("IOI")) {
				cnt++;
				if(cnt == N) {
					cnt--;
					ans++;
				}
				idx++;
			}
			else cnt = 0;
			idx++;
		}
		
//		=============================== 아이디어 2 ===============================
//		for(int i = 0 ; i<M-2 ; ) {
////			System.out.println(i);
//			i++;
//			int temp = 0; // OI가 반복되는 횟수
//			if(str.charAt(i) == 'I') {
//				while(str.charAt(i+1) == 'O' && str.charAt(i+2) == 'I') {
////				while(str.substring(i+1, i+3).equals("OI")) {
//					System.out.println(i);
//					i += 2;
//					temp++;
//					if(temp == N) {
//						temp--;
//						ans++;
//					}
//				}
//			}
//		}
		
//		=============================== 아이디어 1 ===============================
//		String IOI = "";
//		int len = 2*N+1;
//		for(int i = 0 ; i<len ; i++) {
//			IOI += (i%2 == 0)? "I" : "O";
//		}
//		
//		for(int i = 0 ; i<M-len+1 ; i++) {
//			if(str.charAt(i) == 'I') {
//				String temp = str.substring(i, i+len);
//				if(temp.equals(IOI)) ans++;
//			}
//		}
		System.out.println(ans);
	}
}
