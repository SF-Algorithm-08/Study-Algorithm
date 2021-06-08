package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 행성 X3 - 비트연산자
 * 7 : 0111
 * 3 : 0011
 * 5 : 0101
 * => 친밀도
 * 0110+0010+0100 = 6+2+4
 * 
 * 규칙 : (자리가 나타내는 수)*(그 자리 0의 개수)*(그 자리 1의 개수)
 * 1*0*3 + 2*1*2 + 4*1*2 = 12
 */

public class BOJ_G4_2830 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] zero = new int[20]; // 0개수 저장해둘 배열, 2^20이 1000000 바로 위
		int[] one = new int[20]; // 1개수 저장해둘 배열, 2^20이 1000000 바로 위
		for(int n = 0 ; n<N ; n++) {
			int num = Integer.parseInt(br.readLine());
			for(int i = 0 ; i<20 ; i++) {
				if((num&1<<i) == 0) zero[i]++;
				else one[i]++;
			}
		}
		
		long ans = 0;
		for(int i = 0 ; i<20 ; i++) {
			ans += (1<<i) * zero[i] * one[i];
		}
		System.out.println(ans);
	}
}
// ############################## 통과한 cpp 코드 ##############################
//#include <cstdio>
//using namespace std;
//typedef long long ll;
//
//int zero[20];
//int one[20];
//
//int main() {
//  int N, num;
//  scanf("%d", &N);
//  for(int i = 0 ; i<N ; i++) {
//    scanf("%d", &num);
//    for(int j = 0 ; j<20 ; j++)
//      if((num&(1<<j)) == 0) zero[j]++;
//      else one[j]++;
//  }
//
//  ll ans = 0;
//  for(int i = 0; i<20; i++)
//    ans += ll (1<<i)*(zero[i])*(one[i]);
//
//  printf("%lld\n", ans);
//  return 0;
//}
