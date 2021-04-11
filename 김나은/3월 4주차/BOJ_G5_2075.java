// N번째 큰 수

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_2075 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { // 첫 줄은 미리 넣어두기
			pq.offer(Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num > pq.peek()) {
					pq.poll();
					pq.offer(num);
				}
			}
		}
		System.out.println(pq.peek());
	}
}

/*
// 첫번째 풀이 : pq에 모든 데이터를 내림차순으로 정렬한 뒤, N번째 큰 수 뽑기
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1); // 내림차순으로 정렬
			}
		});
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 0; i < N - 1; i++) {
			pq.poll();
		}
		System.out.println(pq.poll());
	}
}
*/
