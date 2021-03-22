package boj.Silver;
//메모리 초과

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_6118_숨바꼭질 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());	//헛간 개수
		int M = Integer.parseInt(st.nextToken());	//간선 개수
		
//		1. 인접하는 헛간 입력받아 저장
		boolean[][] link = new boolean[N+1][N+1];
		int from, to;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			link[from][to] = link[to][from] = true;
		}
		
//		2. BFS
		// 헛간정보
		Queue<Node> queue = new LinkedList<Node>();	
		queue.offer(new Node(1,1));
		// 헛간별 방문 여부
		boolean[] point = new boolean[N+1];	
		point[1] = true;
		// 거리별 헛간 개수
		int[] cnt = new int[N];	
		cnt[0] = 1;
		
		Node cur;	//현재 헛간 정보
		int num=0, far=0;	//숨어야 하는 헛간 번호, 최대 거리
		
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			for(int i=1, end=N+1; i<end; i++) {
				if(link[cur.num][i] && !point[i]) {	//연결되어있고, 아직 방문하지 않았다면
					if(cnt[cur.depth]==0) {	//현재 헛간에 해당하는 거리 처음 세는 경우
						num = i;
						far = cur.depth;
					} else {
						num = Math.min(i, num);	//더 작은 헛간번호로 갱신
					}
					point[i] = true;	//방문 처리
					cnt[cur.depth]++;			//해당 거리 헛간개수+1
					queue.offer(new Node(i, cur.depth+1));
				}
			}
		}
//		System.out.println(Arrays.toString(cnt));
		System.out.printf("%d %d %d", num, far, cnt[far]);
	}
	
	static class Node {
		int num, depth;	//헛간번호, 다음까지의 거리
		public Node(int num, int depth) {
			this.num = num;
			this.depth = depth;
		}
	}
}
