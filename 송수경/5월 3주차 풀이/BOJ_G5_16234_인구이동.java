package boj.Gold;
//G5 인구 이동 (너비 우선 탐색,그래프 이론,그래프 탐색,구현,시뮬레이션)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_16234_인구이동 {
	static int[][] people, group, cntGroup;	//인구, 연합 구분, 연합 정보(연합을 이루고 있는 지역 수, 연합을 이루고 있는 인구 수)
	static int N, L, R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		people = new int[N][N];	//입력받은 정보
		cntGroup = new int[N*N+1][2];	//영역 개수 (인덱스 1부터 사용)
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int sum = 0;	//총 인구 이동
		while(true) {
			open();	//국경선 열기
			if(move()) sum++;	//인구 이동하기, 이동할 수 있으면 횟수 더하고 아니면 반복 종료
			else break;
		};
		
		System.out.println(sum);	//인구 이동 수 출력
	}

	//국경 열기
	static void open() {
		int area = 1;
		group = new int[N][N];	//분리된 영역
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(group[i][j]==0) bfs(i, j, area++);
			}
		}
	}
	
	//연합 분류하기
	static int[][] dir = {{-1,1,0,0},{0,0,-1,1}};
	private static void bfs(int i, int j, int area) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j));
		group[i][j] = area;
		cntGroup[area][0] = 1;
		cntGroup[area][1] = people[i][j];

		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			int nr, nc;
			for(int d=0; d<4; d++) {
				nr = cur.r + dir[0][d];
				nc = cur.c + dir[1][d];
				if(nr<0 || nr>=N || nc<0 || nc>=N || group[nr][nc]>0) continue;
				int diff = Math.abs(people[cur.r][cur.c] - people[nr][nc]);
				if(diff>=L && diff<=R) {	//인접했다면 그 연합에 포함
					group[nr][nc] = area;
					cntGroup[area][0]++;	//연합을 이루고 있는 칸의 수 
					cntGroup[area][1] += people[nr][nc];	//연합의 인구 수
					queue.offer(new Point(nr, nc));
				}
			}
		}
	}

	//인구 이동
	static boolean move() {
		boolean change = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int prev = people[i][j];
				people[i][j] = cntGroup[group[i][j]][1] / cntGroup[group[i][j]][0];
				if(prev != people[i][j]) change = true;	//실제 이동이 일어났다면 true
			}
		}
		return change;
	}
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
