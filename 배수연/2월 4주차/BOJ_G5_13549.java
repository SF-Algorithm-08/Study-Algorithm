package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/** 숨바꼭질3 */
public class BOJ_G5_13549 {
	static int N, K;
	static int[] visited;
	static int temp, ans;
	static int[] dr = {-1, 1, 2};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new int[100001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		ans = 0;
		BFS();
		System.out.println(ans);
	}
	public static void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N);
		visited[N] = 0;
		while(!queue.isEmpty()) {
			temp = queue.poll();
			if(temp == K) ans = visited[temp];
			for(int d = 0 ; d<3 ; d++) {
				int nr = 0;
				if(d != 2) {
					nr = temp+dr[d];
					if(nr>-1 && nr<100001 && visited[nr]>visited[temp]+1) {
						visited[nr] = visited[temp]+1;
						queue.offer(nr);
					}
				}
				else {
					nr = temp*dr[d];
					if(nr>-1 && nr<100001 && visited[nr]>visited[temp]) {
						visited[nr] = visited[temp];
						queue.offer(nr);
					}
				}
			}
		}
	}
}
