package boj.Gold;
//G4 미친 아두이노 (구현,시뮬레이션)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G4_8972_미친아두이노 {
	static char[][] map;
	static int jr, jc;	//종수의 좌표, 아두이노의 좌표
	static int R, C;
	static ArrayList<Arduino> aPosition = new ArrayList<>();	//미친 아두이노의 좌표 저장
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
//		1. 입력받기 (보드의 상태를 입력받으며 종수 및 미친 아두이노의 위치를 저장한다.)
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		//보드 상태, 입력 방향
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j]=='I') {	//종수의 좌표 저장
					jr = i; jc = j;
				} else if(map[i][j]=='R') aPosition.add(new Arduino(i,j));	//미친 아두이노의 좌표 저장
			}
		}
		String strDir = br.readLine();	//이동 방향 나타내는 문자열
		
//		2. 입력 방향 개수만큼 보드 변화
		boolean clear = true;
		StringBuilder sb = new StringBuilder();	//출력할 StringBuilder
		for(int i=0, end=strDir.length(); i<end; i++) {
			if(moveJ(strDir.charAt(i)-'0') || moveA()) {
				clear = false; 
				sb.append("kraj ").append(i+1);	//게임 끝났으면 kraj (i+1) 출력
				break;
			}
		}
		
//		3. 게임 결과에 따른 출력
		if(clear) {	//성공한 경우 보드 상태 출력
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) sb.append(map[i][j]);
				sb.append("\n");
			}
		}//실패한 경우는 위 2번의 for문 안에서 처리(i때문)
		System.out.println(sb);
	}

	static int[][] dir = {{0,1,1,1,0,0,0,-1,-1,-1},{0,-1,0,1,-1,0,1,-1,0,1}};	//1~8인덱스 사용

	//종수 이동 (이동 후 게임 종료 여부를 boolean 타입으로 반환)
	static boolean moveJ(int d) {	//파라미터: 방향
		map[jr][jc]='.';
		jr += dir[0][d];
		jc += dir[1][d];
		
		if(map[jr][jc]=='R') return true;	//게임 종료됨
		else {
			map[jr][jc]='I';	//새 좌표로 이동
			return false;		//게임 종료되지 않음
		}
	}
	
	//미친 아두이노 이동 (이동 후 게임 종료 여부를 boolean 타입으로 반환)
	static boolean moveA() {
		int cr, cc, d;	//현재 참조하는 아두이노의 좌표, 가야할 방향
		int[][] checkBomb = new int[R][C];
		
		// 새 좌표로 설정하기(어디로 이동할건지 찜함)
		for(int i=0, end=aPosition.size(); i<end; i++) {
			cr = aPosition.get(i).r;
			cc = aPosition.get(i).c;
			map[cr][cc]='.';
			
			d=calcDist(cr, cc);	//최단거리 계산하여 가야 할 방향
			cr+=dir[0][d];		//새 좌표
			cc+=dir[1][d];
			
			if (cr==jr && cc==jc) {	//종수를 만나면
				return true;	//게임 종료
			} else {
				checkBomb[cr][cc]++;
				aPosition.set(i, new Arduino(cr,cc));
			}
		}
		
		//이동할 수 있는지 확인 (checkBomb 값이 1로 유일해야 한다. 그 이상이 되면 겹치므로 폭파)
		for(int i=aPosition.size()-1; i>-1; i--) {	
			cr=aPosition.get(i).r;
			cc=aPosition.get(i).c;
			if(checkBomb[cr][cc]>1) aPosition.remove(i);
			else if(checkBomb[cr][cc]==1) map[cr][cc]='R';
		}
		return false;	//게임 종료되지 않음
	}
	
	// 종수와의 최단거리를 계산하여 방향을 설정하여 int로 반환한다.
	static int calcDist(int r, int c) {
		if(jr==r) {
			if(jc<c) return 4;
			else return 6;
		} else if (jr<r) {
			if(jc<c) return 7;
			else if(jc==c) return 8;
			else return 9;
		} else {
			if(jc<c) return 1;
			else if(jc==c) return 2;
			else return 3;
		}
	}
	
	static class Arduino {
		int r, c;
		public Arduino(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
