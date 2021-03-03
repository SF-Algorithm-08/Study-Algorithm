// Z

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1074 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int n = (int) Math.pow(2, N), row = 0, col = 0, ans = 0;
		while (n > 1) {
			n /= 2;
			if (r < row + n && c < col + n) { // 왼쪽 위
			} else if (r < row + n && c >= col + n) { // 오른쪽 위
				ans += n * n;
				col += n;
			} else if (r >= row + n && c < col + n) { // 왼쪽 아래
				ans += n * n * 2;
				row += n;
			} else { // 오른쪽 아래
				ans += n * n * 3;
				row += n;
				col += n;
			}
		}
		System.out.println(ans);

	}

}
