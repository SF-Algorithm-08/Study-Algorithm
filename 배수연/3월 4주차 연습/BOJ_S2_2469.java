package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_S2_2469 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		char[] start = new char[K];
		char[] end = new char[K];
		char[] ansLine = new char[K-1];
		
		// 입력
		String endstr = br.readLine();
		for(int k = 0, idx = 0 ; k<K ; k++, idx++) {
			end[k] = endstr.charAt(idx);
		}
		for(int k = 0 ; k<K ; k++) {
			start[k] = (char) (65 + k);
		}
		String ladder[] = new String[N];
		int index = 0;
		for(int n = 0 ; n<N ; n++) {
			ladder[n] = br.readLine();
			if(ladder[n].charAt(0) == '?') index = n; // ?line index 얻기
		}
		
		// 위~?line
		for(int i = 0 ; i<index ; i++) {
			for(int j = 0 ; j<K-1 ; j++) {
				if(ladder[i].charAt(j) == '-') {
					char temp = start[j];
					start[j] = start[j+1];
					start[j+1] = temp;
				}
			}
		}
		
		// 아래~?line
		for(int i = N-1 ; i>index ; i--) {
			for(int j = 0 ; j<K-1 ; j++) {
				if(ladder[i].charAt(j) == '-') {
					char temp = end[j];
					end[j] = end[j+1];
					end[j+1] = temp;
				}
			}
		}
		
		// ?line 기준 위아래 line 비교해서 ?line 구해보기
		boolean isPossible = true;
		for(int k = 0 ; k<K-1 ; k++) {
			if(start[k] == end[k]) ansLine[k] = '*';
			else if(start[k] == end[k+1] && start[k+1] == end[k]) {
				ansLine[k] = '-';
				char temp = end[k];
				end[k] = end[k+1];
				end[k+1] = temp;
			}
			else isPossible = false;
		}
		
		if(isPossible) {
			for(int k = 0 ; k<K-1 ; k++) {
				System.out.print(ansLine[k]);
			}
		}
		else {
			for(int k = 0 ; k<K-1 ; k++) {
				System.out.print("x");
			}
		}
	}

}
