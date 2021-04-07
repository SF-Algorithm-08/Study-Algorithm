package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17845_수강과목 {

	public static void main(String[] args) throws IOException {
BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N, K;
		int[] time, impt;
		int[][] results;
		StringTokenizer	st = new StringTokenizer(in.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		time = new int[N+1];
		impt = new int[N+1];
		results = new int[N+1][K+1];
		
		for (int i = 1; i <=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			impt[i] = Integer.parseInt(st.nextToken());
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		int ct=0, point=0;					//현재 선택된 아이템의 중요도
		int preVal=0;						//이전  아이템까지 고려한 중요도
		int diffVal=0;						//(배낭무게-현재 선택된 아이템의 무게)의 이전 가치
		for (int item = 1; item <=N; item++) {
			ct = time[item];				//현 아이템의 시간
			point=impt[item];				//현 아이템의 중요도
			
			//현 아이템을 1부터 K까지의 중요도 테이블을 만들어 보기 
			for (int w = 1; w <=K; w++) {
				preVal = results[item-1][w];
				if(ct <= w) {
					diffVal= results[item-1][w-ct];
					results[item][w] = Math.max(preVal, diffVal+point);
				}else {
					results[item][w] = preVal;
				}
			}
		}
		System.out.println(results[N][K]);
	}

}
