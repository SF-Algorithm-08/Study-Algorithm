// 01타일

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_1904 {

	static int[] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N+1];

		if (N <= 2)	System.out.println(N);
		else System.out.println(fiboMemo(N));
	}

	private static int fiboMemo(int N) {
		memo[1] = 1;
		memo[2] = 2;
		for (int i = 3; i <= N; i++) {
			memo[i] = (memo[i-1] + memo[i-2]) % 15746;
		}
		return memo[N];
	}
}
