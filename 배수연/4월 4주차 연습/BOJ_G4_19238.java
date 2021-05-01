package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_19238 {
	static int N, M, O, taxi_x, taxi_y;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Passenger> list;
	static List<Pos> toPassengerDislist;
	
	static class Pos implements Comparable<Pos> {
		int x, y, dis;

		public Pos(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		@Override
		public int compareTo(Pos o) {
			if(this.dis != o.dis) return this.dis-o.dis;
			else {
				if(this.x == o.x) return this.y-o.y;
				else return this.x-o.x;
			}
		}
	}
	
	static class Passenger {
		int from_x, from_y, to_x, to_y;

		public Passenger(int from_x, int from_y, int to_x, int to_y) {
			super();
			this.from_x = from_x;
			this.from_y = from_y;
			this.to_x = to_x;
			this.to_y = to_y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		O = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new ArrayList<Passenger>();
		toPassengerDislist = new ArrayList<Pos>();
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = -1;
			}
		}
		st = new StringTokenizer(br.readLine());
		taxi_x = Integer.parseInt(st.nextToken())-1;
		taxi_y = Integer.parseInt(st.nextToken())-1;
		for(int m = 0 ; m<M ; m++) {
			st = new StringTokenizer(br.readLine());
			int from_x = Integer.parseInt(st.nextToken())-1;
			int from_y = Integer.parseInt(st.nextToken())-1;
			int to_x = Integer.parseInt(st.nextToken())-1;
			int to_y = Integer.parseInt(st.nextToken())-1;
			list.add(new Passenger(from_x, from_y, to_x, to_y));
			map[from_x][from_y] = m+1;
		}
		
		while(list.size()>0) {
			// 택시의 현 위치에서 각 승객까지 걸리는 거리를 계산해서 list에 저장
			getToPassengerDis();
			
			// 어느 곳도 갈 수 없는 경우
			if(toPassengerDislist.size() == 0) {
				System.out.println("-1");
				return;
			}
			
			// 거리가 가장 가까운 경우, (여러 개면 행 작은 것부터, 열 작은 것부터) 손님 있었던 곳 0으로 바꾸기, O-거리
			// 가장 가까운 손님과의 거리가 O보다 크면 -1 출력 return
			Pos selectedPassenger = toPassengerDislist.get(0);
			map[selectedPassenger.x][selectedPassenger.y] = 0;
			O -= selectedPassenger.dis;
			if(O<0) {
				System.out.println("-1");
				return;
			}
			
			// 그 손님의 위치와 목적지 사이 거리 계산, 그 거리가 O보다 크면 -1 출력 return, 아니면 택시 위치 = 손님 목적지, O-거리
			int distance = getToDestDis(selectedPassenger.x, selectedPassenger.y);
			O -= distance;
			if(O<0) {
				System.out.println("-1");
				return;
			}
			
			// 목적지에 도착하면 손님-목적지의 거리*2의 연료 더해주기
			O += distance*2;
		}
		// 마지막의 O 출력
		System.out.println(O);
	}

	// 손님 리스트에서 택시-손님 거리가 최소인 손님을 찾기 -> 여기서 손님-목적지 계산하는 BFS 호출
	private static int getToDestDis(int x, int y) {
		int distance = 0;
		for(int i = 0, size = list.size() ; i<size ; i++) {
			Passenger passenger = list.get(i);
			if(passenger.from_x == x && passenger.from_y == y) {
				distance = getDis(passenger.from_x, passenger.from_y, passenger.to_x, passenger.to_y); 
				if(distance == -1) return distance;
				taxi_x = passenger.to_x;
				taxi_y = passenger.to_y;
				list.remove(passenger);
				break;
			}
		}
		return distance;
	}

	// 손님-목적지 거리 계산하는 BFS
	private static int getDis(int from_x, int from_y, int to_x, int to_y) {
		visited = new boolean[N][N];
		
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.offer(new Pos(from_x, from_y, 0));
		while(!queue.isEmpty()) {
			Pos temp = queue.poll();
			if(temp.x == to_x && temp.y == to_y) return temp.dis;
			
			int nr = 0, nc = 0;
			for(int d = 0 ; d<4 ; d++) {
				nr = temp.x+dr[d];
				nc = temp.y+dc[d];
				if(nr>-1 && nr<N && nc>-1 && nc<N) {
					if(map[nr][nc] != -1 && !visited[nr][nc]) {
						queue.offer(new Pos(nr, nc, temp.dis+1));
						visited[nr][nc] = true;
					}
				}
			}
		}
		return -1;
	}

	// 택시-손님 사이의 거리를 계산하는 BFS - PriorityQueue를 통해서 거리를 비교해서 저장할 수 있도록!
	private static void getToPassengerDis() {
		toPassengerDislist.clear();
		visited = new boolean[N][N];
		
		PriorityQueue<Pos> queue = new PriorityQueue<Pos>();
		queue.offer(new Pos(taxi_x, taxi_y, 0));
		visited[taxi_x][taxi_y] = true;
		while(!queue.isEmpty()) {
			Pos temp = queue.poll();
			if(map[temp.x][temp.y] > 0) {
				toPassengerDislist.add(new Pos(temp.x, temp.y, temp.dis));
			}
			int nr = 0, nc = 0;
			for(int d = 0 ; d<4 ; d++) {
				nr = temp.x+dr[d];
				nc = temp.y+dc[d];
				if(nr>-1 && nr<N && nc>-1 && nc<N) {
					if(map[nr][nc] != -1 && !visited[nr][nc]) {
						queue.offer(new Pos(nr, nc, temp.dis+1));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

}
