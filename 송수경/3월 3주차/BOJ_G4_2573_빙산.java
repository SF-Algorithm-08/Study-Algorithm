package boj.Gold;
//12% 런타임에러

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2573_빙산 {
	static int day, N, M;	//일 수(정답), 행, 열
	static int[][] info;			//전체 배열
	static ArrayList<Sea> seaList;	//바다 좌표 담는 리스트
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		seaList = new ArrayList<Sea>();
		
//		1. 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		info = new int[N][M];	//빙산 정보
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
				//바다면 ArrayList에 담기
				if(info[i][j]==0) seaList.add(new Sea(i, j));
			}
		}
		
//		2. 최초 빙산 상태 체크
//		checkIce();
		if(seaList.size()==M*N) System.out.println(0);
		else checkIce();
	}
	
	static void solve() {	//재귀
		day++;
		meltIce();	//빙산 녹이기
		checkIce();
	}

	// 빙산 녹이는 함수
	static void meltIce() {
		int nr, nc;
		for(int i=0, end=seaList.size(); i<end; i++) {
			for(int j=0; j<4; j++) {
				nr = seaList.get(i).r + dir[j][0];
				nc = seaList.get(i).c + dir[j][1];
				if(nr>-1 && nr<N && nc>-1 && nc<M && info[nr][nc]>0) {
					info[nr][nc]--;
					if(info[nr][nc]==0) seaList.add(new Sea(nr,nc));
				}
			}
		}
	}
	
	// 빙산 상태(개수) 체크하는 함수
	static void checkIce() {
		int cnt1=0;	//남은 빙산 개수
		int cnt2=0;	//인접한 빙산 개수
		boolean isFirst = true;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(info[i][j]!=0) {
					cnt1++;	//현재 모든 빙산 세기
					if(isFirst) {	// 처음 발견한 빙산이면 bfs 수행
						isFirst = false;
						cnt2 = bfs(i, j);
					}
				}
			}
		}
		// 결과 (빙산 덩어리 개수)에 따른 처리
		if(cnt1==0) System.out.println(0);
		else if(cnt1==cnt2) solve();
		else System.out.println(day);
	}
	
	//인접한 빙산 세는 함수
	static Queue<Ice> queue;
	static int bfs(int r, int c) {
		Ice cur;
		int nr, nc;
		boolean[][] visited = new boolean[N][M];
		
		queue = new LinkedList<Ice>();
		queue.offer(new Ice(r, c));
		int countIce = 1;	//빙산 개수
		visited[r][c]=true;
		
		while(!queue.isEmpty()) {
			cur = queue.poll();
			for(int i=0; i<4; i++) {
				nr = cur.r + dir[i][0];	//4방탐색
				nc = cur.c + dir[i][1];
				if(nr==-1 || nr==N || nc==-1 || nc==M || visited[nr][nc] || info[nr][nc]==0) continue;
				queue.offer(new Ice(nr, nc));
				visited[nr][nc] = true;
				countIce++;
			}
		}
		return countIce;
	}
	
	static class Sea {
		int r, c;
		public Sea(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Ice {
		int r, c;
		public Ice(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}

/*
5 5
0 0 0 0 0
0 0 10 10 0
0 10 0 10 0
0 0 10 10 0
0 0 0 0 0

결과 0

40 20
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0 
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0 
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 10 10 10 10 1 10 10 10 10 10 10 10 10 10 10 10 10 10 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

결과 19
 */
