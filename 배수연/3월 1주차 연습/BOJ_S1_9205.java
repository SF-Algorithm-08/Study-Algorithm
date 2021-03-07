package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Loc{
	int x;
	int y;
	public Loc(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class BOJ_S1_9205 {
	static int T, N;
	static Loc[] location;
	static boolean[] visited;
	static Loc home, festival;
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test<=T ; test++) {
			N = Integer.parseInt(br.readLine());
			location = new Loc[N+2];
			visited = new boolean[N+2];
			for(int n = 0 ; n<N+2 ; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				location[n] = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			home = location[0];
			festival = location[N+1];
			
			flag = false;
			BFS();
			if(flag) System.out.println("happy");
			else System.out.println("sad");
		}
	}

	private static void BFS() {
		Queue<Loc> queue = new LinkedList<Loc>();
		queue.offer(home);
		while(!queue.isEmpty()) {
			Loc temp = queue.poll();
			int r = temp.x;
			int c = temp.y;
			if(r == festival.x && c == festival.y) {
				flag = true;
				return;
			}
			for(int n = 1 ; n<N+2 ; n++) { // 맥주 20병으로 갈 수 있는 최대 거리는 1000m
				int distance = Math.abs(r-location[n].x)+Math.abs(c-location[n].y);
				if(!visited[n] && distance<=1000) {
					queue.offer(location[n]);
					visited[n] = true;
				}
			}
		}
	}

}
