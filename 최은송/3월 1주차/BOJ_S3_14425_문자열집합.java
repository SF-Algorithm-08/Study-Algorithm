package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_S3_14425_문자열집합 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> n = new HashSet<String>();
		for(int i=0; i<N; i++) {
			n.add(in.readLine());
		}
		
		int cnt = 0; String word;
		for(int i=0; i<M; i++) {
			word = in.readLine();
			if(n.contains(word)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
