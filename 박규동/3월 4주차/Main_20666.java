import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20666 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, p;
	static Monster[] monster; // 인덱스는 몬스터 번호, 값은 난이도
	static ArrayList<Guide>[] guide; // 인덱스는 아이템 번호
	static long answer;
	static boolean[] visit;

	static class Monster {
		int num;
		long level;

		public Monster(int num, long level) {
			this.num = num;
			this.level = level;
		}
	}

	static class Guide {
		int mon;
		long t;

		public Guide(int mon, long t) {
			this.mon = mon;
			this.t = t;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(answer);
	}

	private static void solve() {
		PriorityQueue<Monster> pq = new PriorityQueue<>(new Comparator<Monster>() {
			@Override
			public int compare(Monster o1, Monster o2) {
				if(o1.level >= o2.level) return 1;
				return -1;
			}
		});

		for (int i = 1; i <= N; i++) {
			pq.add(monster[i]);
		}
		
		int count = 0;
		while(count < M) {

			int cnum = pq.peek().num;
			long clevel = pq.peek().level;
//			System.out.println(cnum+","+clevel);
			pq.poll();
			if (visit[cnum])
				continue;
			visit[cnum] = true;
			answer = Math.max(answer, clevel);
			count++;

			if (p != 0) {
				int len = guide[cnum].size();
				if (len != 0) {
					for (int j = 0; j < len; j++) {
						int nnum = guide[cnum].get(j).mon;
						if(!visit[nnum]) {
							monster[nnum].level -= guide[cnum].get(j).t;
							long nlevel = monster[nnum].level;
							pq.add(new Monster(nnum, nlevel));
						}
					}
				}
			}
		}

	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		monster = new Monster[N + 1];
		visit = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int level = Integer.parseInt(st.nextToken());
			monster[i] = new Monster(i, level);
		}

		p = Integer.parseInt(br.readLine());
		if (p != 0) {
			guide = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				guide[i] = new ArrayList<>();
			}

			for (int i = 0; i < p; i++) {
				st = new StringTokenizer(br.readLine());
				int item = Integer.parseInt(st.nextToken());
				int mon = Integer.parseInt(st.nextToken());
				long t = Long.parseLong(st.nextToken());
				guide[item].add(new Guide(mon, t));
				monster[mon].level += t; // 인덱스는 몬스터 번호
			}

		}
//		for(int i=1;i<=N;i++) {
//			System.out.println(monster[i].num+","+monster[i].level);
//		}
//		System.out.println();

	}

}
