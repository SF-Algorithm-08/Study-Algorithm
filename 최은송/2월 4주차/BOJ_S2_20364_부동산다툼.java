package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_20364_부동산다툼 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim(), " ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		boolean[] used = new boolean[N+1];
		for(int i = 0; i < Q; i++) {
			int duck = Integer.parseInt(in.readLine().trim());
			int first = 0;
			int node = duck;
			while(node > 0) {
				if(used[node])	
					first = node;
				node /= 2;
			}
			if(first != 0)
				System.out.println(first);
			else {
				System.out.println(0);
				used[duck] = true;
			}
				
		}
	}
}
