package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_1561_놀이공원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		N -= M; // 시작할 때 M만큼은 이미 탄다.
		int[] time = new int[M];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++)
			time[i] = Integer.parseInt(st.nextToken());

		long start = 1, end = N + M, mid=0, student=0;
		while (start <= end) {

			mid = (start + end) / 2;
			student = 0;
			for (int t : time) {
				student += mid / t;
			}

			if (student < N)
				start = mid - 1;
			else
				end = mid + 1;

			if (student > N && student - N <= M)
				break;
		}
		
		for(int t=0; t<M; t++) {
			if(mid % time[t] ==0) {
				student++;
			}
			if(student==N) {
				System.out.println(mid + t + 1);
				break;
			}
		}
	}

}
