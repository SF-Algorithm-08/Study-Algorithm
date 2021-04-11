// 문자열 집합

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_14425 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int ans = 0;
		String[] str = new String[N];

		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}

		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				if (str[j].equals(line)) ans++;
			}
		}

		System.out.println(ans);
	}
}
