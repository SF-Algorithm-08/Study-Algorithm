package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G2_1938_통나무옮기기 {
	static char[][] map;
	static int N, sr, sc, sf, er, ec, ef;	//크기, 시작점, 최종점, 최종점 가로(1) 혹은 세로(2) 상태
	static Queue<Tree> q = new LinkedList<Tree>();
	
	static class Tree {
		int r, c, d, f;	//위치, 움직임 횟수, 보는 방향(가로 1, 세로 2)
		
		public Tree(int r, int c, int d, int f) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.f = f;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());	//평지의 한 변의 길이
		map = new char[N][N];
		
//		1. 입력받기(최초 위치, 최종 위치)
		for(int i=0, start=0, end=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				if(map[i][j]=='B') {	//B를 두번째 만났을 때 가로/세로 판별
					if(start==0) { sr=i; sc=j; start++;}
					else if(start++==1) {
						sf = i-sr+1; sr=i; sc=j;
						q.offer(new Tree(sr, sc, 0, sf));
					}
				} else if(map[i][j]=='E') {
					if(end==0) { er=i; ec=j; end++;}
					else if(end++==1) {	//E를 두번째 만났을 때 가로/세로 판별
						er = i; ec = j;
						ef = i-er+1;
					}
				}
			}
		}
		
//		2. bfs
		System.out.println(bfs());
	}
	
	static int bfs() {
		Tree cur;
		int[][] dirVisited = new int[N][N];		//가로 지났으면 1, 세로 지났으면 2, 둘다 했으면 3
		dirVisited[sr][sc] = sf;	
		int cr, cc, cf, nr, nc;	//현재 좌표, 현재 방향, 새 좌표
		int[][] dir = {{-1,1,0,0},{0,0,-1,1}};	//상하좌우
		
		while(!q.isEmpty()) {
			cur = q.poll();
			cr = cur.r; cc = cur.c; cf = cur.f;
			
			if(cr==er && cc==ec && cf==ef) {	//통나무 도착
				return cur.d;
			}
			
			//상하좌우 이동
			for(int i=0; i<4; i++) {	
				if(!checkMove(cur, i)) continue;	//이동이 불가능하면 pass!
				nr = cr + dir[0][i];
				nc = cc + dir[1][i];
				
				//		가로세로 모두				cur의 방향
				if(dirVisited[nr][nc]==3 || dirVisited[nr][nc]==cf) continue;
				dirVisited[nr][nc] += cf;
				q.offer(new Tree(nr, nc, cur.d+1, cf));
			}
			//회전
			if(dirVisited[cr][cc]==cf && checkMove(cur, 4)) {
				cf = 3-cf;
				q.offer(new Tree(cr, cc, cur.d+1, cf));	//회전
				dirVisited[cr][cc]=3;
			}
		}
		return 0;
	}
	
	//경우에 따라 이동 가능한지 체크한다. (n: 5개의 기본동작)
	static boolean checkMove(Tree t, int n) {
		int r = t.r, c = t.c;
		
		switch(n) {
		case 0:	//상
			if(t.f==1) {	//가로 방향인 경우
				return r>0 && map[r-1][c-1]!='1' && map[r-1][c]!='1' && map[r-1][c+1]!='1';
			} else {	//세로 방향인 경우
				return r>1 && map[r-2][c]!='1';
			}
		case 1:	//하
			if(t.f==1) {	//가로 방향인 경우
				return r<N-1 && map[r+1][c-1]!='1' && map[r+1][c]!='1' && map[r+1][c+1]!='1';
			} else {	//세로 방향인 경우
				return r<N-2 && map[r+2][c]!='1';
			}
		case 2:	//좌
			if(t.f==1) {	//가로 방향인 경우
				return c>1 && map[r][c-2]!='1';
			} else {	//세로 방향인 경우
				return c>0 && map[r-1][c-1]!='1' && map[r][c-1]!='1' && map[r+1][c-1]!='1';
			}
		case 3:	//우
			if(t.f==1) {	//가로 방향인 경우
				return c<N-2 && map[r][c+2]!='1';
			} else {	//세로 방향인 경우
				return c<N-1 && map[r-1][c+1]!='1' && map[r][c+1]!='1' && map[r+1][c+1]!='1';
			}
		default: //회전
			if(r==0 || r==N-1 || c==0 || c==N-1) return false;
			return map[r-1][c-1]!='1' && map[r-1][c]!='1' && map[r-1][c+1]!='1'
					&& map[r][c-1]!='1' && map[r][c+1]!='1'
					&& map[r+1][c-1]!='1' && map[r+1][c]!='1' && map[r+1][c+1]!='1';
		}
	}

}
