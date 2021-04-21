package boj.Gold;
//G4 별자리 만들기 (그래프 이론,최소 스패닝 트리)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G4_4386_별자리만들기 {
	static int[] group;
	static class Line implements Comparable<Line>{	//별 사이의 간선
		int star1, star2;
		double dist;
		
		public Line(int star1, int star2, double dist) {
			this.star1 = star1;
			this.star2 = star2;
			this.dist = dist;
		}
		
		@Override
			public int compareTo(Line o) {	//오름차순
				if(o.dist<this.dist) return 1;
				return -1;
			}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine().trim());
		ArrayList<Line> list = new ArrayList<>();
		double[][] stars = new double[N][2];
		
//		1. 별의 정보 받기
		for(int i=0; i<N; i++) {	
			st = new StringTokenizer(br.readLine()," ");
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
//		2. 간선 정보 저장하기
		for(int i=0, endI=N-1; i<endI; i++) {
			for(int j=i+1; j<N; j++)
				list.add(new Line(i, j, Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2))));
		}
		
//		3. 거리가 짧은 것부터 오름차순 정렬
		Collections.sort(list);
		
//		4. 크루스칼 알고리즘 수행
		group = new int[N];
		for(int i=0; i<N; i++) group[i] = i;	//초기화
		
		double ans = 0;
		for(Line l : list) {
			if(find(l.star1) != find(l.star2)) {	//서로 다른 집합에 속해있다면
				ans += l.dist;						//그 간선을 선택한다
				union(l.star1, l.star2);
			}
		}
		
//		5. 정답 출력 (소수 둘째자리까지)
		System.out.println(String.format("%.2f", ans));
	}

	public static int find(int x) {				//이미 연결된 간선인지 확인
		if (x == group[x]) {
			return x;
		}

		return group[x] = find(group[x]);
	}

	public static void union(int x, int y) {	//간선 연결
		x = find(x);
		y = find(y);

		if (x != y) {
			group[y] = x;
		}
	}
}
