package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S5_12782_비트우정지수 {

	public static void main(String[] args) throws NumberFormatException, IOException {	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		String a, b;
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			a = st.nextToken();
			b = st.nextToken();
			int diff = 0, a_one = 0, b_one = 0;
			for(int i = 0; i < a.length(); i++) {
				if(a.charAt(i) != b.charAt(i))	diff++;		// a,b가 다르면 diff++
				if(a.charAt(i)-'0' == 1)	a_one++;			// a에 1이 몇갠지 카운트 
				if(b.charAt(i)-'0' == 1)	b_one++;			// b에 1이 몇갠지 카운트 
			}
//			System.out.printf("diff: %d, a: %d, b:%d\n",diff,a_one,b_one);
			int change = Math.abs(a_one - b_one);
			int result = change + (diff - change) / 2;
//			System.out.printf("change: %d, result: %d\n",change,result);
			System.out.println(result);
		}
	}

}
