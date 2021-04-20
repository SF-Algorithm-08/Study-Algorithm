package boj.Silver;
//S2 촌수계산 (너비 우선 탐색,깊이 우선 탐색,그래프 이론,그래프 탐색)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_2644_촌수계산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());	//사람 수
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n1 = Integer.parseInt(st.nextToken())-1;	//0부터 인덱스 사용하므로 입력 값들에 -1
		int n2 = Integer.parseInt(st.nextToken())-1;
		
		int M = Integer.parseInt(br.readLine().trim());	//부모자식간의 관계의 개수
		boolean[][][] link = new boolean[N][N][2];		//[][][0]: 연결 여부 , [][][1]: 방문 여부
		//직접 연결 여부를 체크
		int r, c;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			r=Integer.parseInt(st.nextToken())-1;
			c=Integer.parseInt(st.nextToken())-1;
			link[r][c][0]=link[c][r][0]=true;	//우하 대각선을 대칭으로 같은 값을 가지게 함
			link[i][i][1]=true;		//뒤에서 bfs할 때 스스로를 큐에 추가하지 않도록 미리 방문했다고 임의 체크
		}
		
		//bfs
		Queue<Integer[]> queue = new LinkedList<>();
		queue.offer(new Integer[] {n1, 0});	//시작점, 0촌
		Integer[] cur;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			if(cur[0]==n2) {	//끝점에 도착하면 몇촌인지 출력 후 종료
				System.out.println(cur[1]);
				return;
			}
			
			for(int i=0; i<N; i++) {
				if(link[cur[0]][i][0] && !link[cur[0]][i][1]) {	//연결이 되어 있고, 아직 방문하지 않았다면 큐에 추가
					queue.offer(new Integer[] {i,cur[1]+1});
					link[cur[0]][i][1]=true;	//방문 체크
				}
			}
		}
		System.out.println(-1);	//빠져나왔다면 연결 경로를 찾을 수 없었다는 것이므로 -1 출력
	}

}
