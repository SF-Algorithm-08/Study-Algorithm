package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1043_거짓말 {

	static int N, M, ans;
	static ArrayList<Integer>[] people;
	static ArrayList<Integer>[] party;
	static boolean[] know;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 파티의 수
		people = new ArrayList[N+1];
		party = new ArrayList[M];
		know = new boolean[N+1];
		visited = new boolean[M];

		for (int i = 1; i <= N; i++) {
			people[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		int truthPeople = Integer.parseInt(st.nextToken()); // 이야기의 진실을 아는 사람의 수
		for (int i = 0; i < truthPeople; i++) {
			know[Integer.parseInt(st.nextToken())] = true; // 번호
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int partyPeople = Integer.parseInt(st.nextToken()); // 각 파티마다 오는 사람의 수
			for (int j = 0; j < partyPeople; j++) {
				int val = Integer.parseInt(st.nextToken()); // 번호
				people[val].add(i);
				party[i].add(val);
			}
		}

		bfs();

		for (int i = 0; i < M; i++) {
			if (!visited[i]) {
				ans++;
			}
		}

		System.out.println(ans);
	}

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (know[i]) { // 이야기의 진실을 아는 사람
				for (int j = 0; j < people[i].size(); j++) { // 그 사람이 참가한 (여러 개)파티 수만큼
					if (!visited[people[i].get(j)]) {
						visited[people[i].get(j)] = true; // 방문표시를 해주고
						queue.add(people[i].get(j)); // queue에 파티 번호 추가
					}
				}
			}
		}

		while (!queue.isEmpty()) {
			int partyNumber = queue.poll(); // 각 파티마다

			for (int person : party[partyNumber]) { // 파티 참여자 한명씩
				for (int partyInfo : people[person]) { // 참가한 (여러 개)파티를
					if (!visited[partyInfo]) {
						visited[partyInfo] = true; // 방문표시
						queue.add(partyInfo); // queue에 파티 번호 추가
					}
				}
			}
		}
		
	}
	
}