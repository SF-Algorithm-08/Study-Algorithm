package boj.Gold;
//G4 BFS
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_6087_레이저통신 {
	static int W, H;
    static char[][] map;	//주어지는 map
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[][] ch;
    				//상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		//1. 지도 정보 받아오기
		map = new char[H][W];
		ch = new int[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				ch[i][j]=-1;
			}
		}
		String str;
		boolean founded = false;
		for(int i=0; i<H; i++) {
			str = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='C' && !founded) {	//C 시작위치
					start[0] = i; start[1] = j;
					founded = true;
				} else if(map[i][j]=='C') {	//C 도착위치
					end[0] = i; end[1] = j;
				}
			}
		}
		
		//2. C지점부터 4방탐색하여 다른 C찾기(BFS)
		Queue<Node> queue = new LinkedList<>();
        for(int i=0;i<4;i++) {	//시작 위치에서 4방으로 출발
        	queue.add(new Node(start[0],start[1],i,0 ));
        }
        ch[start[0]][start[1]]=0;
        
        while(!queue.isEmpty()){
            Node p = queue.poll();

            for(int i=0;i<4;i++){
                int nr = p.x + dx[i];
                int nc = p.y + dy[i];

                if(nr<0 || nr>=H || nc<0 || nc>=W) continue;
                if(map[nr][nc]=='.' || map[nr][nc]=='C'){
                    int nextMirror = p.dir!=i?p.mirror+1:p.mirror;
                    if((ch[nr][nc] == -1 ) || (ch[nr][nc] !=-1 && ch[nr][nc] >= nextMirror)){
                        ch[nr][nc] = nextMirror;
                        queue.add(new Node(nr,nc,i,nextMirror));
                    }
                }
            }
        }
        System.out.println(ch[end[0]][end[1]]);

    }

    static class Node{
        int x,y,dir,mirror;
        public Node(int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }
    }
}
