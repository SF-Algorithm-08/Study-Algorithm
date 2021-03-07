
package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_9205_맥주마시면서걸어가기 {
	static int[][] map;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		in = new BufferedReader(new StringReader(input));
		int T = Integer.parseInt(in.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine());
			
			map = new int[N+2][2];
			visited = new boolean[N+2];
			for(int i=0; i<N+2; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[i][0] = x;
				map[i][1] = y;
			}
			bfs();
			if(visited[N+1])
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		visited[0] = true;
		while(!queue.isEmpty()) {
			int curr = queue.peek();
			queue.poll();
			for(int i=1; i<N+2; i++) {
				if(check(curr, i)) {
					if(!visited[i]) {
						visited[i] = true;
						queue.offer(i);
					}
				}
			}
		}
		
	}
	
	static boolean check(int i, int j) {
		double dist = Math.abs(map[i][0] - map[j][0]) + Math.abs(map[i][1] - map[j][1]);
		if(dist/50 > 20)
			return false;
		return true;
	}
	static String input = "2\n" + 
			"2\n" + 
			"0 0\n" + 
			"1000 0\n" + 
			"1000 1000\n" + 
			"2000 1000\n" + 
			"2\n" + 
			"0 0\n" + 
			"1000 0\n" + 
			"2000 1000\n" + 
			"2000 2000";
	
//	static String input = "1\n" + 
//			"2\n" + 
//			"0 0\n" + 
//			"1000 0\n" + 
//			"2000 1000\n" + 
//			"2000 2000" ;
			

}