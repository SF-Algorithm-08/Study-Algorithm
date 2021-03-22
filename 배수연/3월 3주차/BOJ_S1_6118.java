package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_6118 {
	static int N, M, from, to;
	static ArrayList<Integer>[] road;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		road = new ArrayList[N+1];
		for(int i = 0 ; i<=N ; i++) {
			road[i] = new ArrayList<>();
		}
		
		// 노드 간 연결 정보를 저장해야 하므로 from에는 to, to에는 from 정보를 저장해준다.
		for(int m = 0 ; m<M ; m++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			road[from].add(to);
			road[to].add(from);
		}
		
		// 1번 헛간부터 판별
		dijkstra(1);
	}
	private static void dijkstra(int v) {
		int[] dist = new int[N+1]; // 각 노드를 몇 번째로 방문할 수 있는지 저장
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// BFS
		Queue<Integer> queue = new LinkedList<>();
		dist[v] = 0;
		visited[v] = true;
		queue.offer(v); // 첫 번째 노드부터 탐색 시작
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			for(int i = 0 ; i<road[temp].size() ; i++) {
				int next = road[temp].get(i); // 연결된 노드를 판별
				if(!visited[next] && dist[next]>dist[temp]+1) { // 다음 노드에 방문하는 데 걸린 횟수가 최소가 되도록 반복
					dist[next] = dist[temp]+1;
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
		
		// 최댓값 찾기
		int max = 0, maxPos = 0, maxNum = 0;
		for(int i = 1 ; i<=N ; i++) {
			if(max<dist[i]) { // 방문할 수 있는 최대 거리의 노드라면
				max = dist[i];
				maxPos = i;
				maxNum = 1; // 거리가 가장 먼 노드의 개수를 1로 초기화
			}
			else if(max == dist[i]) maxNum++; // 거리가 같으면 개수를 늘려줌
		}
		System.out.println(maxPos + " " + max + " " + maxNum);
	}
}