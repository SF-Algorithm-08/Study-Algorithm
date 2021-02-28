package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G5_13549_숨바꼭질 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int cnt = 0, x = 0 , max = 100001;
		boolean visited[] = new boolean[max];
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {N, cnt});
		while(!q.isEmpty()) {
			x = q.peek()[0];
			cnt = q.peek()[1];
			q.poll();
			if(x == K) {
				break;
			}
			if(2*x < max && !visited[2*x]) {
				q.offer(new int[] {2 * x, cnt});
				visited[2*x] = true;
			}
			if(x-1 >= 0 && !visited[x-1]) {
				q.offer(new int[] {x-1, cnt+1});
				visited[x-1] = true;
			}
			if(x+1 < max && !visited[x+1]) {
				q.offer(new int[] {x+1, cnt+1});
				visited[x+1] = true;
			}
		}
		System.out.println(cnt);
	}

}
