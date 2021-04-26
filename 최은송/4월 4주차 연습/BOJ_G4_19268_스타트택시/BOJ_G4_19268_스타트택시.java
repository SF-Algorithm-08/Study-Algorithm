package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_19268_스타트택시 {
	static int N, M, fuel, map[][], taxi[];
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static ArrayList<Client> clients;
	static class Client implements Comparable<Client>{
		public int sr, sc, er, ec, dist, idx;	//출발지 r,c , 목적지 r,c, 택시까지 거리 
		public Client(int sr, int sc, int er, int ec) {
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
		@Override
		public int compareTo(Client o) {
			if(this.dist != o.dist) {
				return Integer.compare(this.dist, o.dist);
			}else {
				if(this.sr != o.sr) {
					return Integer.compare(this.sr, o.sr);
				}else {
					return Integer.compare(this.sc, o.sc);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<=N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		taxi = new int[2];
		st = new StringTokenizer(in.readLine());
		taxi[0] = Integer.parseInt(st.nextToken());
		taxi[1] = Integer.parseInt(st.nextToken());
		
		clients = new ArrayList<Client>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			Client c = new Client(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			clients.add(c);
			c.idx = i;
		}
		bfs();
		System.out.println(fuel);
	}
	
	static void bfs() {
		PriorityQueue<Client> pq = new PriorityQueue<Client>();
		do {
			pq.clear();
			// 1. 각 승객별로 한명씩 태운다. pq에 넣어서 poll 한명씩
			int dist = 0;
			for(int i = 0; i < M; i++) {
				Client c = clients.get(i);
				if(c.idx == -1) continue;
				dist = findDist(taxi[0], taxi[1], c.sr, c.sc);
				if(dist == -2) {	//손님까지 도달 못하는 경우 
					fuel = -1;
					return;	
				}else if(dist != -1) { // 손님위치까지 갈 때 연료가 부족하지 않음 
					c.dist = dist;
					pq.offer(c);
				}
			}
			if(pq.isEmpty()) {	//손님을 다 태웠으면 종료 
				return;
			}
			// 2. 승객 출발지까지 간다. 거리를 fuel에서 차감, 택시기사 좌표 옮겨주기
			Client c = pq.poll();
			fuel -= c.dist;
			taxi[0] = c.sr;
			taxi[1] = c.sc;
			
			// 3. 목적지까지 이동한다. 거리를 fuel에서 차감. 와중에 fuel이 0 미만이 되면 -1 츨력 return
			dist = findDist(c.sr, c.sc, c.er, c.ec);
			if(dist == -1) {
				fuel = -1;
				return;
			}else {
				// 4. 목적지 도착하면 이동거리만큼 fuel에서 차감한다. 
				fuel = fuel - dist + dist * 2;
				taxi[0] = c.er;
				taxi[1] = c.ec;
				clients.get(c.idx).idx = -1; 
			}
		}while(true);
		
	}
	
	static int findDist(int sr, int sc, int er, int ec) {
		int dist = 0,nr, nc, size;
		boolean visited[][] = new boolean[N+1][N+1];
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {sr, sc});	 
		visited[sr][sc] = true;
		
		
		while(!q.isEmpty()) {
			size = q.size();
			for(int i=0; i<size; i++) {
				int[] cur = q.poll();
				sr = cur[0];
				sc = cur[1];
				
				if(sr == er && sc == ec && dist <= fuel) { //손님 위치에 갈 수 있을 때
					return dist;
				}
				for(int d = 0; d < 4; d++) {
					nr = sr + dr[d];
					nc = sc + dc[d];
					if(nr <= 0 || nr > N || nc <= 0 || nc > N || visited[nr][nc] || map[nr][nc] == 1) continue;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
			dist++;
		}
		if(dist <= fuel) {	//도달하지 못하는 경우 
			return -2;
		}
		return -1;	// 연료 부족 
	}
}
