package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 케빈 케이컨의 6단계 법칙 - 그래프, 플로이드와샬
 * 전체 map을 max값인 99로 초기화
 * 한 번에 연결된다고 입력받으면 map값을 1로 바꿔주기
 * 3중 포문으로 플로이드 구현하면서 각 노드에 도달할 수 있는 최소값으로 갱신
 * 마지막에 이중 포문 돌면서 각 노드에서의 케빈 베이컨 수를 구하고, 그게 최소일 때의 사람을 갱신해가며 구함
 */

public class BOJ_S1_1389 {
	static final int INF = 99;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][N+1];
		for(int i = 1 ; i<=N ; i++) {
			Arrays.fill(map[i], INF);
		}
		for(int m = 0 ; m<M ; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			map[A][B] = 1;
			map[B][A] = 1;
		}
		for(int k = 1 ; k<=N ; k++) {
			for(int i = 1 ; i<=N ; i++) {
				for(int j = 1 ; j<=N ; j++) {
					if((k == i) || (i == j)) continue;
					if(map[i][j]>map[i][k]+map[k][j]) map[i][j] = map[i][k]+map[k][j];
				}
			}
		}
		
		int min = INF, sum = 0, ans = 0;
		for(int i = 1 ; i<=N ; i++) {
			sum = 0;
			for(int j = 1 ; j<=N ; j++) {
				if(map[i][j] != INF) sum += map[i][j];
			}
			if(sum<min) {
				min = sum;
				ans = i;
			}
		}
		System.out.println(ans);
	}
}

