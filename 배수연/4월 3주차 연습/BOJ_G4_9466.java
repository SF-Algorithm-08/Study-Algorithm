package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 텀 프로젝트 - DFS
 */

public class BOJ_G4_9466 {
	static int N, first, grouped;
	static int[] students;
	static boolean[] visited, connected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test<=T ; test++) {
			N = Integer.parseInt(br.readLine());
			grouped = 0;
			students = new int[N+1];
			visited = new boolean[N+1];		// 0 : 방문안함, 1 : 방문은 했음, 2 : group에 들어감 => 이방법 안됨
			connected = new boolean[N+1]; 	// 그냥 새로운 배열 만들어서 그룹에 속했는지 판단
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int n = 1 ; n<=N ; n++) {
				students[n] = Integer.parseInt(st.nextToken());
				if(n == students[n]) { // 자기자신을 가리키는 경우는 무조건 혼자 그룹
					visited[n] = true;
					connected[n] = true;
					grouped++;
				}
			}
			
			for(int n = 1 ; n<=N ; n++) {
				if(!visited[n]) {
//					first = n;
					DFS(n);
				}
			}
			
			System.out.println(N-grouped);
		}
	}

	private static void DFS(int num) {
		visited[num] = true;
		int next = students[num];
		if(!visited[next]) DFS(next);
		else if(visited[next] && !connected[next]) { // 같은 그룹 선택의 시작과 만남
			// 그 그룹의 인원 세기
			grouped++; // 첫번째 더해주기
			for(int i = next ; i != num ; i = students[i]) {
				grouped++;
			}
		}
		connected[num] = true;
	}

}
