package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 촌수계산
 */

public class BOJ_S2_2644 {
	static int N, relation_A, relation_B;
	static ArrayList<Integer>[] list;
	static int[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for(int n = 1 ; n<=N ; n++) {
			list[n] = new ArrayList<Integer>();
		}
		visited = new int[N+1];
		Arrays.fill(visited, -1);
		st = new StringTokenizer(br.readLine());
		relation_A = Integer.parseInt(st.nextToken());
		relation_B = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		for(int m = 0 ; m<M ; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		System.out.println(BFS());
	}

	private static int BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[relation_A] = 0;
		queue.offer(relation_A);
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			if(temp == relation_B) return visited[temp];
			for(int i = 0 ; i<list[temp].size() ; i++) {
				int curr = list[temp].get(i);
				if(visited[curr] == -1) {
					visited[curr] = visited[temp]+1;
					queue.offer(curr);
				}
			}
		}
		return -1;
	}

}
