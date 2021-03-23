// 전화번호 목록

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G4_5052 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] tel = new String[N];

			for (int i = 0; i < N; i++) {
				tel[i] = br.readLine();
			}

			Arrays.sort(tel);

			boolean flag = false;
			for (int i = 1; i < N; i++) {
				if (tel[i].startsWith(tel[i - 1])) {
					flag = true;
					break;
				}
			}

			if (flag) System.out.println("NO");
			else System.out.println("YES");
			
		}
	}
}
