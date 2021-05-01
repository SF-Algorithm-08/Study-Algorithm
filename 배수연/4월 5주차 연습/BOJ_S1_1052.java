package boj;

import java.util.Scanner;

/**
 * 물병
 * ##### IDEA1 #####
 * N에 몇을 더해야 2의 제곱인 수가 되는가
 * (예시1 N)			19 = 0001 0011
 * (가까운 2의 제곱수)	32 = 0010 0000
 * => 더해야 하는 수		13 = 0000 1101
 * 
 * (예시2 N)			 7 = 0000 0111
 * (가까운 2의 제곱수)	 8 = 0000 1000
 * => 더해야 하는 수		 1 = 0000 0001
 * 
 * (예시3 N)			18 = 0001 0010
 * (가까운 2의 제곱수)	32 = 0010 0000
 * => 더해야 하는 수	14 = 0000 1110
 * => K=2라면 답은 0 (다른 방법 찾아보자)
 * 
 * ##### IDEA2 #####
 * 단순히 N에 1씩 더해가면서 2로 쭉 나눠보고 나머지가 생겼던 횟수가 K보다 작은 경우 구하기
 */

public class BOJ_S1_1052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int ans = -1;
		for(int i = 0 ; i<=10000000 ; i++) {
			int temp = N+i;
			int num = 0;
			while(temp>0 && num<=K) {
				if(temp%2 == 1) num++;
				temp/=2;
			}
			if(num<=K) {
				ans = i;
				break;
			}
		}
		// N과 가장 가까운 2의 제곱수를 구한다.
//		long largeN = N-1;
//		for(int i = 0 ; i<=2*N ; i++) {
//			int num = 1<<i;
//			if(num>=N) {
//				largeN = num;
//				break;
//			}
//		}
//		System.out.println(largeN);
//		// 그 수와 N의 차이 구하기
//		ans = (int) (largeN-N);
		System.out.println(ans);
	}
}
