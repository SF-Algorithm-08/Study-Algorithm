import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9466 {

	static int T, N, answer;
	static int[] student;
	static StringTokenizer st;
	static HashSet<Integer> teamset;
	static HashSet<Integer> tmp;
	static HashSet<Integer> failset;
	static Queue<Integer> q;

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			teamset = new HashSet<>();
			failset = new HashSet<>();
			tmp = new HashSet<>();
			student = new int[N + 1];
			q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				student[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				if (teamset.contains(i) || failset.contains(i))
					continue;
				if (teamset.contains(student[i]) || failset.contains(student[i])) {
					failset.add(i);
					continue;
				}
				q.clear();
				solve(i);
			}
			sb.append(failset.size()).append("\n");
		}
		System.out.println(sb);
	}

	private static void solve(int start) {
		tmp.clear();
		tmp.add(start);
		q.add(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			int next = student[cur];
			
			if(teamset.contains(next)||failset.contains(next)) {
				failset.addAll(tmp);
				return;
			}
			
			if(next==start) {
				teamset.addAll(tmp);
				return;
			}
			
			if(cur==next) {
				failset.add(start);
				teamset.add(cur);
				return;
			}
			
			if(tmp.contains(next)) {
				failset.add(start);
				return;
			}
			
			q.add(next);
			tmp.add(next);
		}

	}

}
