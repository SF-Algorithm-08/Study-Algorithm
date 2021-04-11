package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_2666 {
	static int N, L;
	static int[] arr;
	static int[][][] closet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// input
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int opened_one = Integer.parseInt(st.nextToken());
		int opened_two = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(br.readLine());
		arr = new int[L];
		for(int l = 0 ; l<L ; l++) {
			arr[l] = Integer.parseInt(br.readLine());
		}
		
		// 문을 열 수 있는 최소 이동 횟수를 저장할 배열
		// 열린 문이 첫 번째, 두번째 index에 있을 때 cnt번째 벽장을 사용
		closet = new int[N+1][N+1][L+1];
		for(int i = 0 ; i<=N ; i++) {
			for(int j = 0 ; j<=N ; j++) {
				Arrays.fill(closet[i][j], Integer.MAX_VALUE);
			}
		}
		int ans = func(opened_one, opened_two, 0);
		System.out.println(ans);
	}

	// 열린 문으로부터 떨어진 거리를 구하기 => 두 문 중 최소값
	private static int func(int opened_one, int opened_two, int cnt) {
		if(cnt == L) return 0;
		int result = closet[opened_one][opened_two][cnt];
//		if(result != Integer.MAX_VALUE) return result;
		
		int door_one = func(arr[cnt], opened_two, cnt+1) + Math.abs(arr[cnt]-opened_one);
		int door_two = func(opened_one, arr[cnt], cnt+1) + Math.abs(arr[cnt]-opened_two);
		result = Math.min(door_one, door_two);
		
		return result;
	}

}
