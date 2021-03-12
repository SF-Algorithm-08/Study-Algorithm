package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 슬라임 연구자
 * 소인수분해해서 지수승 합에 로그2씌우기
 */
public class BOJ_S1_14715 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int exp = getExp(K);
		int ans = (int) Math.ceil((Math.log10(exp)/Math.log10(2)));
		System.out.println(ans);
	}

	private static int getExp(int k) {
		int cnt = 0;
		for(int i = 2 ; i<=Math.sqrt(k) ; i++) {
			while(k%i == 0) {
				cnt++;
				k /= i;
			}
		}
		if(k != 1) cnt++;
		return cnt;
	}

}
