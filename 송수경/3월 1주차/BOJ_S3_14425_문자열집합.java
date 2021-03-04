package boj.Silver;
// S3 문자열 집합
// solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_14425_문자열집합 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] str = new String[N];
		for(int i=0; i<N; i++) {
			str[i]=br.readLine().trim();
		}
		String input;
		int cnt=0;
		for(int i=0; i<M; i++) {
			input = br.readLine().trim();
			for(String s : str) {
				if(s.equals(input)) {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}
