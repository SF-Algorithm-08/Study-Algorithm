package boj.Silver;
// S1 전생했더니 슬라임 연구자였던 건에 대하여(Easy)(수학)
// solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_14715_전생했더니슬라임연구자였던건에대하여 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine().trim());
		int ans=0;
		int cnt=0;	//소인수분해 결과 (곱을 이루는 소수의 개수)
//		1. 소인수분해
		for(int i=2, j=K; i<K; i++) {
			while(j%i==0) {
				cnt++;
				j/=i;
			}
			if(j==1) break;	//소인수분해 완료
		}
		
//		2. 최소 depth구하기
		int flag=0;	//이진트리 가장 아래 단계 노드 개수
		while(cnt>flag) {
			ans++;
			flag=(int)Math.pow(2, ans);
		}
		
		System.out.println(ans);
	}
}
