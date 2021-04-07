package boj;

import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * N번째 큰 수
 * PriorityQueue를 내림차순으로 이용하면 쉽게 해결되는 문제
 * PriorityQueue 내림차순 : Collections.reverseOrder()
 * N-1번째 수까지는 poll()해버리고 그 다음 수인 N을 출력
 */

public class BOJ_G5_2075 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int n = 0 ; n<N*N ; n++) {
			pq.offer(sc.nextInt());
		}
		for(int n = 0 ; n<N-1 ; n++) {
			pq.poll();
		}
		System.out.println(pq.poll());
	}
}
