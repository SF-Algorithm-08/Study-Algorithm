package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_1089_스타트링크타워 {

	static char[][][] numbers = {
			{ { '#', '#', '#' }, { '#', '.', '#' }, { '#', '.', '#' }, { '#', '.', '#' }, { '#', '#', '#' } }, // 0
			{ { '.', '.', '#' }, { '.', '.', '#' }, { '.', '.', '#' }, { '.', '.', '#' }, { '.', '.', '#' } }, // 1
			{ { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#' }, { '#', '.', '.' }, { '#', '#', '#' } }, // 2
			{ { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#' } }, // 3
			{ { '#', '.', '#' }, { '#', '.', '#' }, { '#', '#', '#' }, { '.', '.', '#' }, { '.', '.', '#' } }, // 4
			{ { '#', '#', '#' }, { '#', '.', '.' }, { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#' } }, // 5
			{ { '#', '#', '#' }, { '#', '.', '.' }, { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#' } }, // 6
			{ { '#', '#', '#' }, { '.', '.', '#' }, { '.', '.', '#' }, { '.', '.', '#' }, { '.', '.', '#' } }, // 7
			{ { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#' } }, // 8
			{ { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#' } } };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] board = new char[5][4 * N - 1];

		for (int i = 0; i < 5; i++) {
			board[i] = br.readLine().toCharArray();
		}

		double ans = 0, digit = 1;
		for (int i = N - 1; i >= 0; i--) {
			int num = 0, cnt = 0;
			for (int j = 0; j < 10; j++) {
				boolean flag = false;
				for (int k = 0; k < 5; k++) {
					for (int l = 0; l < 3; l++)
						if (numbers[j][k][l] == '.' && board[k][4*i+l] == '#') flag = true;
				}
				if (!flag) {
					cnt++;
					num += j;
				}
			}
			if (cnt == 0) ans = -1;
			else {
				ans += num * digit / cnt;
				digit *= 10;
			}
		}
		System.out.println(ans);

	}
}
