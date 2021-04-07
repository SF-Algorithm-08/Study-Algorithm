package boj.Silver;
//S2 사다리타기 (구현,문자열)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S2_2469_사다리타기 {
	static char[] ePeople;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine().trim());
		int N = Integer.parseInt(br.readLine().trim());
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] sPeople = new char[K];	//초기 사람들의 상태
		for(int i=0; i<K; i++) sPeople[i] = alphabet.charAt(i);
		
		ePeople = new char[K];
		char[][] ladder = new char[N][K-1];
		
//		1. 입력 받기
		ePeople = br.readLine().trim().toCharArray();
		
		int numL=0;	//?로 표시된 사다리 번호
		for(int i=0; i<N; i++) {
			ladder[i] = br.readLine().trim().toCharArray();
			if(ladder[i][0]=='?') numL = i;
		}
		
//		2. 위부터 ?까지 사다리타기
		for(int i=0; i<numL; i++) {
			for(int j=0, end=K-1; j<end; j++) {
				if(ladder[i][j]=='-') tradeSeat(j, sPeople);
			}
		}
		
//		3. 아래부터 ?까지 사다리타기
		for(int i=N-1; i>numL; i--) {
			for(int j=0, end=K-1; j<end; j++) {
				if(ladder[i][j]=='-') tradeSeat(j, ePeople);
			}
		}
		
//		System.out.println(Arrays.toString(sPeople));
//		System.out.println(Arrays.toString(ePeople));
		
//		4. sPeople과 ePeople 비교하고 사다리 만들기
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<K; i++) {
			if(sPeople[i] == ePeople[i]) sb.append('*');
			else if(i<K-1){
				tradeSeat(i, sPeople);
				sb.append('-');
			}
		}
//		System.out.println(Arrays.toString(sPeople));
//		System.out.println(Arrays.toString(ePeople));
		
//		5. 만든 사다리가 유효한지 검사
		boolean isValid = true;
		for(int i=0; i<K; i++) {
			if(sPeople[i] != ePeople[i]) {
				isValid = false;
				break;
			}
		}
		
//		6. 정답 출력
		String wrong = "xxxxxxxxxxxxxxxxxxxxxxxxx";
		System.out.println((isValid?sb.toString():wrong).substring(0, K-1));
	}

	//사다리 탔을 때 자리바꾸기
	static void tradeSeat(int i, char[] arr) {
		char temp = arr[i];
		arr[i] = arr[i+1];
		arr[i+1] = temp;
	}
}

/*
10
5
ACGBEDJFIH
*-***-***
-*-******
?????????
-**-***-*
**-*-*-*-
==>
*-*-***-

26
3
ABCDEFGHIJKLMNOPQRSTUVWXYZ
*-***-****-***-****-***-*
?????????????????????????
*-***-****-***-****-***-*


26
30
ABCDEFGHIJKLMNOPQRSTUVWXYZ
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
?????????????????????????
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*
*-***-****-***-****-***-*


*/