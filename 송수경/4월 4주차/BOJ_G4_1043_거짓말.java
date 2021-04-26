package boj.Gold;
//G4 거짓말 (자료 구조,분리 집합,그래프 이론,그래프 탐색)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1043_거짓말 {
	static int head[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());	//사람의 수
		int M = Integer.parseInt(st.nextToken());	//파티의 수
		
		head = new int[N+1];			//집합 (인덱스 1부터 사용)
		for(int i=1; i<=N; i++) head[i] = i;
		boolean know[] = new boolean[N+1];	//진실을 아는지 여부
		int party[][] = new int[M][50];		//파티 정보
		
//		1. 진실을 아는 사람의 수와 번호 입력받기
		st = new StringTokenizer(br.readLine()," ");
		int knows = Integer.parseInt(st.nextToken());	//진실을 아는 사람 수
		for(int i=1, end=knows+1; i<end; i++) know[Integer.parseInt(st.nextToken())] = true;
		
//		2. 각 파티마다 오는 사람들 그룹 만들기
		for(int i=0; i<M; i++) {	//파티 수만큼
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0, end=Integer.parseInt(st.nextToken()); j<end; j++) {	//사람 수만큼
				party[i][j] = Integer.parseInt(st.nextToken());
				union(party[i][0], party[i][j]);
			}
		}
		
//		3. 진실을 아는 사람과 같은 집합에 속하는 사람 체크하기
		for(int i=1, endI=N+1; i<endI; i++) {
			if(!know[i]) continue;
			for(int j=1, end=N+1; j<end; j++)
				if(find(i)==find(j))
					know[j] = true; 
		}
		
//		4. 파티를 탐색하며 진실을 모르는 사람으로만 이루어진 행 개수 세기
		int cnt=0;
		search:
		for(int i=0; i<M; i++) {
			for(int j=0; party[i][j]!=0; j++) {
				if(know[party[i][j]]) continue search;
			}
			cnt++;
		}
		
//		5. 정답 출력
		System.out.println(cnt);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a < b) head[b] = a;
        else head[a] = b;
	}
	
	static int find(int a) {
		if(a==head[a]) return a;
		return head[a] = find(head[a]);
	}
}
