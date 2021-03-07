package boj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 버블 소트
 * 	- 몇 단계의 버블 소트가 발생했는지를 묻는 문제
 * 	- 원래의 인덱스들과 정렬 후 인덱스를 쭉 비교
 * 	- ans : 인덱스 차이 최대값+1
 */
class Bubble implements Comparable<Bubble> {
	int num;
	int idx;
	public Bubble(int num, int idx) {
		super();
		this.num = num;
		this.idx = idx;
	}
	@Override
	public int compareTo(Bubble o) {
		return num-o.num;
	}
}
public class BOJ_G3_1377 {
	static int N;
	static Bubble[] A;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new Bubble[N+1];
		for(int n = 1 ; n<=N ; n++) {
			A[n] = new Bubble(sc.nextInt(), n);
		}
//		bubble();
		Arrays.sort(A, 1, N+1);
		int max = 0;
		for (int b = 1 ; b<=N ; b++) {
			max = Math.max(max, A[b].idx-b);
		}
		System.out.println(max+1);
	}
//	private static void bubble() {
//		boolean change = false;
//		for(int i = 1 ; i<=N+1 ; i++) {
//			change = false;
//			for(int j = 1 ; j<=N-i ; j++) {
//				System.out.print(Arrays.toString(A));
//				if(A[j]>A[j+1]) {
//					change = true;
//					int temp = A[j];
//					A[j] = A[j+1];
//					A[j+1] = temp;
//				}
//				System.out.println(" "+change);
//			}
//			if(!change) {
//				System.out.println(i);
//				break;
//			}
//		}
//	}

}
/*
5
10
1
5
2
3
*/
// 3