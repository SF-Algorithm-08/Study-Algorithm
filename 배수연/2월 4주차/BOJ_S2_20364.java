package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 부동산 다툼 */
public class BOJ_S2_20364 {
	static int N, Q;
	static int[] ground;
	static int[] tree;
	static int first_meet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		tree = new int[N+1];
		ground = new int[Q];
		for(int q = 0 ; q<Q ; q++) {
			ground[q] = Integer.parseInt(br.readLine());
			first_meet = 0;
			int node = ground[q];
			while(node > 0) {
				if(tree[node] == 1) first_meet = node;
				node/=2;
			}
			if(first_meet != 0) System.out.println(first_meet);
			else {
				System.out.println("0");
				tree[ground[q]] = 1;
			}
		}
	}
}
