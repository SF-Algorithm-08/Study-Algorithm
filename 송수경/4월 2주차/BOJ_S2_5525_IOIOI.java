package boj.Silver;
//S2 IOIOI (문자열)

import java.util.Scanner;

public class BOJ_S2_5525_IOIOI {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	//O의 개수
		int M = sc.nextInt();	//S의 길이
		String S = sc.next();	//문자열 S
		sc.close();
		
		int idx=0;	//문자열에서 참조할 인덱스
		int ans=0;	//답
		int cnt;	//연속한 IOI개수 세는 변수
		while(idx<M-1) {	
			if(S.charAt(idx)=='I') {	
				cnt=0;
				while(idx+(cnt+1)*2<M) {	
					if(S.charAt(idx+cnt*2+1)=='O' && S.charAt(idx+(cnt+1)*2)=='I') cnt++;	
					else break;
				}
				idx+=cnt*2+1;	
				ans+=Math.max(0, cnt-N+1);	
			} else idx++;	
		}
		System.out.println(ans);
	}
}
