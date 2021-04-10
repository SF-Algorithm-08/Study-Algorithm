package boj.Gold;
//G4 드래곤 커브 (구현,시뮬레이션)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_15685_드래곤커브 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<Integer[]> points;
		boolean[][] map = new boolean[101][101];
		int[][] dir = {{1,0,-1,0},{0,-1,0,1}};
		int sr, sc, d, or, oc, nr, nc;	//시작점, 방향, 기준점 좌표, 새로운 좌표
		int G, size, flag;				//세대, points의 size, 기준점 index
		int N = Integer.parseInt(br.readLine().trim());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			nr = sr+dir[0][d];
			nc = sc+dir[1][d];
			
			points = new ArrayList<>();
			points.add(new Integer[] {sr, sc});
			points.add(new Integer[] {nr, nc});
			map[sr][sc]=true; map[nr][nc]=true;
			or=nr;	oc=nc;	flag=1;	//최초의 기준점 설정
			
			for(int g=0; g<G; g++) {	//g세대 드래곤 커브 구하기
				size = points.size();
				for(int j=size-1; j>-1; j--) {	
					if(j==flag) continue;	
					nr = or+oc-points.get(j)[1];	//현재 참조하는 점으로부터 변환된 점
					nc = oc-or+points.get(j)[0];	//즉, 기준점을 기준으로 반시계방향 회전 (좌표계 자체를 상하 뒤집어 생각한다.)
					points.add(new Integer[] {nr, nc});
					if(nr>-1 && nc>-1) map[nr][nc]=true;
					if(j==0) {	//새로운 기준점 설정
						flag = points.size()-1;
						or=nr; oc=nc;
					}
				}
			}
		}//결과: 드래곤커브에 포함되는 점들은 map에 true표시가 된다
		
		int ans=0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++)
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) ans++;
		}
		System.out.println(ans);
	}
}