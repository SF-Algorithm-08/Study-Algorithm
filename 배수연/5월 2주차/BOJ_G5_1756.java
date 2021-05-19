package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 피자 굽기 - 구현
 * 오븐의 크기가 작은거 뒤에 큰거가 주어진다고 해도 의미 없음 => 이전 오븐 크기와 비교해보고 작은걸로 저장하기
 * 오븐의 안쪽부터(지름이 작은 쪽부터) 피자 넣어보기
 */
public class BOJ_G5_1756 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] oven = new int[D];
		int[] pizza = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int d = 0 ; d<D ; d++) {
			oven[d] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n<N ; n++) {
			pizza[n] = Integer.parseInt(st.nextToken());
		}
		
		// 5 6 4 3 6 2 3 => 5 5 4 3 3 2 2
		for(int d = 1 ; d<D ; d++) {
			oven[d] = Math.min(oven[d], oven[d-1]);
		}
		int pizzaIdx = 0, endIdx = D-1;
		for(int d = D-1 ; d>=0 ; d--) {
			if(pizzaIdx>=N) { // 피자를 다 넣었다면
				System.out.println(endIdx+1);
				return;
			}
			if(oven[d]>=pizza[pizzaIdx]) {
				pizzaIdx++;
				endIdx = d;
			}
		}
		System.out.println("0");
	}

}

