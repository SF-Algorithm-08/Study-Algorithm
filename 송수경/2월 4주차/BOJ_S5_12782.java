package boj;
// S5 비트 우정지수(그리디 알고리즘,수학)
// solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S5_12782 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());	//테스트케이스 수
		StringTokenizer st;
		String num1, num2;	//비교할 두 수
		int diff;	//다른 숫자 개수
		int one, zero;	//num1 기준
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			num1 = st.nextToken();
			num2 = st.nextToken();
			one=0; zero=0;
			for(int i=0; i<num1.length(); i++) {
				if(num1.charAt(i)==num2.charAt(i)) continue;
				if(num1.charAt(i)=='1') one++;
				else zero++;
			}
			
			System.out.println(Math.max(one, zero));
		}
	}

}
