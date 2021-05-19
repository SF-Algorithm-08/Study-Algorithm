package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문자 해독 - 문자열
 * A : 65, z : 122 => 배열 크기 58로 설정(중간에 특수문자도 포함돼있지만 무관)
 * S에서 0~wLen까지의 문자열에 대해서 개수를 먼저 세주고, 그 이후의 문자열에 대해서 for문을 통해 맨 뒤 개수 더하고 맨 앞 개수 빼고 해주면 이상하게 88% 정도에서 틀리다고 나온다..
 * 그래서 for문 하나로 돌면서 탐색하는 문자열의 길이가 wLen과 같은 경우에만 검사하도록 해줌.
 */
public class BOJ_G4_1593 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int wLen = Integer.parseInt(st.nextToken());
		int sLen = Integer.parseInt(st.nextToken());
		String W = br.readLine();
		String S = br.readLine();
		int[] cntW = new int[58]; // 정답 문자열에 사용되는 알파벳 수를 세기 위한 배열
		int[] cntS = new int[58];
		for(int w = 0 ; w<wLen ; w++) {
			cntW[W.charAt(w)-65]++;
		}
		
		int len = 0, idx = 0, ans = 0;
		for(int i = 0 ; i<sLen ; i++) {
			cntS[S.charAt(i)-65]++; // i번째 인덱스 문자 cnt--
			len++;
			if(len == wLen) { // 길이가 같을 때만 연산하기
				if(Arrays.equals(cntW, cntS)) ans++;
				cntS[S.charAt(idx++)-65]--; // 맨 앞에부터 확인하고 지나간 문자 cnt--
				len--;
			}
		}
		System.out.println(ans);
	}
}

