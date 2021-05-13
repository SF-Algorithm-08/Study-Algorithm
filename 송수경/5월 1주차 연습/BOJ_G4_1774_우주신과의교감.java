package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1774_우주신과의교감 {
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
//		1. 입력받기
		int N = Integer.parseInt(st.nextToken());	//우주신들의 개수
		int M = Integer.parseInt(st.nextToken());	//이미 연결된 신들과의 통로의 개수
		
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) parent[i] = i;
		int[][] points = new int[N+1][2];
		double[][] dist = new double[N+1][N+1];	//거리, 연결 여부
		
		for(int i=1; i<=N; i++) {	//좌표들 입력받기
			st = new StringTokenizer(br.readLine()," ");
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
			
			for(int j=1; j<i; j++)	//거리 저장: dist의 왼쪽아래 삼각형 이용
				dist[i][j] = distance(points[i][0], points[j][0], points[i][1], points[j][1]);
		}
		
		for(int i=0; i<M; i++) {	//연결된 통로 입력받기
			st = new StringTokenizer(br.readLine()," ");
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
//		2. 연결 되었는지 확인하고 비용 더하기
		double ans = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<i; j++) {
				if(find(i)!=find(j)) {
					double min = Double.MAX_VALUE;
					for(int k=1; k<=N; k++) {
						if(find(j)==find(k))
							min = Math.min(min, dist[Math.max(i, k)][Math.min(i, k)]);
					}
					ans += min;
					union(i, j);
				}
			}
		}
		
//		3. 소수점 둘째자리까지 출력
		System.out.println(String.format("%.2f", ans));
	}
	
	public static double distance(int x1, int x2, int y1, int y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}

	public static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if(x<y) parent[y] = x;
			else parent[x] = y;
		}
	}
}
