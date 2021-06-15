package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 놀이공원 - 이분탐색
 * 예제    1 2 3 4 5
 * 0분 : 1 1 1 1 1 => 5명
 * 1분 : 1 1 1 1 1 => 5명 + 5
 * 2분 : 2 1 1 1 1 => 6명 + 5
 * 3분 : 3 1 1 1 1 => 7명 + 5
 * 4분 : 4 2 1 1 1 => 9명 + 5
 * 5분 : 5 2 1 1 1 => 10명 + 5
 * 6분 : 6 3 2 1 1 => 13명 + 5
 * 7분 : 7 3 2 1 1 => 14명 + 5
 * 8분 : 8 4 2 2 1 => 17명 + 5
 * 7->8분에서 1, 2, 4번 놀이기구에 탑승했으므로 4번이 마지막
 * 
 * 각 놀이기구에 걸리는 시간을 해당 분에서 나누고 다 더하기
 * 이분으로 탐색하면서 전체 N이 해당하는 분 확인하기
 * 그 직전 분에서 총 인원 수를 구하고, 시간이 바뀔 때 몇 번째 놀이기구에 사람이 탑승했는지 보기
 */

public class BOJ_G2_1561 {
	static long N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		int max_time = 0;
		st = new StringTokenizer(br.readLine());
		for(int m = 0 ; m<M ; m++) {
			arr[m] = Integer.parseInt(st.nextToken());
			max_time = Math.max(max_time, arr[m]);
		}
		
		if(N<=M) {
			System.out.println(N);
			return;
		}
		
		// 0, N*max_time으로 이분 탐색
		long left = 0, right = N*max_time;
		long time = 0; // N 직후의 인원이 놀이기구를 탑승하는 데 걸린 시간
		while(left<=right) {
			long mid = (left+right)/2;
			long people = M;
			for(int m = 0 ; m<M ; m++) {
				people += mid/arr[m];
			}
			if(people>=N) {
				time = mid;
				right = mid-1;
			}
			else left = mid+1;
		}
		
		// time-1일 때의 인원 배열과 인원 수, time일 때의 인원 배열 구하고 인원 차이 구하기
		// N-(time-1일 때의 인원 수)만큼 반복하면서 인원 차이가 발생하는 인덱스 얻기
		long[] prev = new long[M];
		long[] curr = new long[M];
		long prevCnt = M;
		for(int m = 0 ; m<M ; m++) {
			prev[m] = (time-1)/arr[m];
			curr[m] = time/arr[m];
			prevCnt += prev[m];
		}
		int cnt = 0;
		for(int m = 0 ; m<M ; m++) {
			if(prev[m] == curr[m]) continue;
			if(++cnt == N-prevCnt) {
				System.out.println(m+1);
				return;
			}
		}
	}
}
